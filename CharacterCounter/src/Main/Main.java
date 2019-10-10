package Main;

import MyUtils.CCDot;
import MyUtils.CharCounter;
import MyUtils.FileHandler;
import MyUtils.ThisSession;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static void processFile(File file) {
        System.out.println("Start process file " + file);

        FileHandler fileHandler = new FileHandler(file);
        CharCounter charCounter;

        try {
            charCounter = fileHandler.run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Map<Character, Integer> map = charCounter.getMap();
        ArrayList<CCDot> dots = new ArrayList<>();

        for (char c : map.keySet()) {
            dots.add(new CCDot(c, map.get(c)));
        }

        dots.sort(Collections.reverseOrder(Comparator.comparingInt(CCDot::getCount)));

        fileHandler.out(dots, charCounter.getWordsCount());
        System.out.println("File " + file + " was processed.");
    }

    public static void main(String[] args) {
        System.out.println("Start program");
        ThisSession session = ThisSession.getInstance(
                "IN",
                "OUT",
                "txt",
                "Windows-1251"
        );

        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(
                "All files must have " + session.extension + " extension", session.extension);

        List<File> fileList = new ArrayList<>();
        try {
            fileList = Files.list(session.inputFolder)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(fileNameExtensionFilter::accept)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileList.size() == 0) {
            System.out.println("There is no " + session.extension + " files.");
        } else {
            for (File file : fileList) {
                processFile(file);
            }
        }

        WatchService watchService = null;
        try {
            watchService = session.inputFolder.getFileSystem().newWatchService();
            session.inputFolder.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println("All available " + session.extension + " files were processed. Waiting for changes...");
        for (; ; ) {
            WatchKey key = null;
            try {
                key = Objects.requireNonNull(watchService).take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (WatchEvent event : Objects.requireNonNull(key).pollEvents()) {
                File file = new File(session.inputFolder + "/" + event.context().toString());

                if (!Files.isRegularFile(file.toPath()) || !fileNameExtensionFilter.accept(file)) {
                    continue;
                }

                switch (event.kind().name()) {
                    case "OVERFLOW":
                        break;
                    case "ENTRY_CREATE":
                        System.out.println("File " + event.context() + " is created!");
                        break;
                    case "ENTRY_MODIFY":
                        System.out.println("File " + event.context() + " is modified!");

                        processFile(file);
                        break;
                    case "ENTRY_DELETE":
                        System.out.println("File " + event.context() + " is deleted!");
                        break;
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}

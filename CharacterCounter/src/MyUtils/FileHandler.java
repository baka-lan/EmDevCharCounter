package MyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FileHandler {
    private File file;

    public FileHandler(File file) {
        this.file = file;
    }

    public CharCounter run() throws FileNotFoundException {
        CharCounter charCounter = new CharCounter();

        try (Scanner scanner = new Scanner(new FileInputStream(file), ThisSession.getInstance().charsetName)) {
            while (scanner.hasNext()) {
                charCounter.eatString(scanner.nextLine());
            }
        }
        return charCounter;
    }

    public void out(List<CCDot> dots, int wordsCount) {
        String outputName = ThisSession.getInstance().outputFolder + "/result_for_" + file.getName();

        try (PrintWriter writer = new PrintWriter(outputName)) {
            writer.println("Words count: " + wordsCount);

            StringBuilder sb = new StringBuilder();
            for (CCDot dot : dots) {
                sb.append(dot.getLetter());
            }
            writer.println("Unique characters: \"" + sb.toString() + "\"" + ", count: " + dots.size());

            for (CCDot dot : dots) {
                writer.println(dot);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

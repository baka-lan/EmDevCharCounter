package Main;

import Dot.CharCountDot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //initialize scanner & get text
        String inputName = "IN/input.txt";
        Scanner scanner;
        try {
            scanner = new Scanner(new FileInputStream(inputName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        //initialize Counters
        Map<Character, Integer> map = new HashMap<>();
        int wordsCounter = 0;

        //process text
        while (scanner.hasNext()){
            //get String
            String string = scanner.nextLine();

            //split String to words
            StringTokenizer st = new StringTokenizer(string, " ,.-?!;:");

            //process words to character-count Map, count words
            while (st.hasMoreTokens()) {
                String currentString = st.nextToken().toLowerCase();

                for (char c : currentString.toCharArray()) {
                    map.put(c, (map.containsKey(c)) ? map.get(c) + 1 : 1);
                }
                wordsCounter++;
            }
        }

        //unsorted Map to ArrayList & sort
        ArrayList<CharCountDot> dots = new ArrayList<>();

        for (char c : map.keySet()) {
            dots.add(new CharCountDot(c, map.get(c)));
        }

        dots.sort(Collections.reverseOrder(Comparator.comparingInt(CharCountDot::getCount)));

        //out
        String outputName = "OUT/output.txt";

        try (PrintWriter writer = new PrintWriter(outputName)) {
            writer.println("Words count: " + wordsCounter);
            StringBuilder sb = new StringBuilder();
            for (CharCountDot dot : dots) {
                sb.append(dot.getLetter());
            }
            writer.println("Unique characters: \"" + sb.toString() + "\"" + ", count: " + dots.size());
            for (CharCountDot dot : dots) {
                writer.println("'" + dot.getLetter() + "' " + dot.getCount());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            System.out.println("Words count: " + wordsCounter);
            StringBuilder sb = new StringBuilder();
            for (CharCountDot dot : dots) {
                sb.append(dot.getLetter());
            }
            System.out.println("Unique characters: \"" + sb.toString() + "\"" + ", count: " + dots.size());
            for (CharCountDot dot : dots) {
                System.out.println("'" + dot.getLetter() + "' " + dot.getCount());
            }
        }
    }
}

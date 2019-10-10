package MyUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CharCounter {
    private Map<Character, Integer> map = new HashMap<>();
    private int wordsCount = 0;

    public Map<Character, Integer> getMap() {
        return map;
    }

    public Integer getWordsCount() {
        return wordsCount;
    }

    void eatString(String string) {
        StringTokenizer st = new StringTokenizer(string, " ,.-?!;:'\"/<>\\");

        while (st.hasMoreTokens()) {
            String currentString = st.nextToken().toLowerCase();

            for (char c : currentString.toCharArray()) {
                map.put(c, (map.containsKey(c)) ? map.get(c) + 1 : 1);
            }
            wordsCount++;
        }
    }
}

package Dot;

public class CharCountDot {
    private char letter;
    private int count;

    public CharCountDot(char letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    public char getLetter() {
        return letter;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Dot.CharCountDot{" +
                "letter=" + letter +
                ", count=" + count +
                '}';
    }
}

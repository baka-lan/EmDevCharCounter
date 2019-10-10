package MyUtils;

public class CCDot {
    private char letter;
    private int count;

    public CCDot(char letter, int count) {
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
        return "letter = " + letter +
                ", count = " + count;
    }
}

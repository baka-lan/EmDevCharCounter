package MyUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ThisSession {
    private static ThisSession ourInstance;
    public final Path inputFolder;
    public final Path outputFolder;
    public final String extension;
    public final String charsetName;

    static ThisSession getInstance() {
        if (ourInstance == null) {
            throw new NullPointerException();
        }
        return ourInstance;
    }

    public static ThisSession getInstance(String inputFolder, String outputFolder, String extension, String charsetName) {
        if (ourInstance == null) {
            ourInstance = new ThisSession(inputFolder, outputFolder, extension, charsetName);
        }
        return ourInstance;
    }

    private ThisSession(String inputFolder, String outputFolder, String extension, String charsetName) {
        this.inputFolder = Paths.get(inputFolder);
        this.outputFolder = Paths.get(outputFolder);
        this.extension = extension;
        this.charsetName = charsetName;
    }
}

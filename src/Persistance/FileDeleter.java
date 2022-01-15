package Persistance;

import java.io.File;

public class FileDeleter {
    private File file;

    public boolean deleteCSVFile() {
        file = new File("files/statusGame.csv");
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    public boolean deleteJsonFile() {
        file = new File("files/statusGame.json");
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }
}

package Persistance;

import java.io.File;

public class FileDeleter {
    private File file;

    public boolean deleteCSVFile() {
        file = new File("files/statusGame.csv");
        return file.delete();
    }

    public boolean deleteJsonFile() {
        file = new File("files/statusGame.json");
        return file.delete();
    }
}

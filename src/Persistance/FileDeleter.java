package Persistance;

import java.io.File;

/**
 * Class created to delete game status files for csv and json
 */
public class FileDeleter {
    private File file;

    /**
     * Method created to delete the statusGame csv
     * @return true for deleted
     */
    public boolean deleteCSVFile() {
        file = new File("files/statusGame.csv");
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /**
     * Method created to delete the statusGame json
     * @return true for deleted
     */
    public boolean deleteJsonFile() {
        file = new File("files/statusGame.json");
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }
}

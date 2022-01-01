package Persistance;

import Business.Edition;
import Business.Trial;
import Persistance.Json.JsonReader;
import Persistance.Json.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class JsonController {

    public ArrayList<Trial> readTrials() throws FileNotFoundException {
        return new JsonReader().readFilesTrials();
    }

    public ArrayList<Edition> readEditions() throws FileNotFoundException {
        return new JsonReader().readFilesEditions();
    }

    public void writeTrialsToFiles(ArrayList<Trial> trials,ArrayList<Edition> editions) throws IOException {
        new JsonWriter(trials,editions).writeFiles();
    }

}

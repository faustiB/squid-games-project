package Persistance;

import Business.Trial;
import Persistance.Json.JsonReader;
import Persistance.Json.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class JsonController {

    public ArrayList<Trial> readTrials() throws FileNotFoundException {
        return new JsonReader().readFiles();
    }

    public void writeTrialsToFiles(ArrayList<Trial> trials) throws IOException {
        new JsonWriter(trials).writeFiles();
    }

}

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


    public void writeTrials(ArrayList<Trial> trials) throws IOException {
        JsonWriter jw = new JsonWriter();

        jw.writeTrials(trials);
    }

    public void writeEditions(ArrayList<Edition> editions) throws IOException {
        JsonWriter jw = new JsonWriter();

        jw.writeEditions(editions);
    }

}

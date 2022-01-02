package Persistance;

import Business.Trial;
import Persistance.Csv.CsvWriter;

import java.io.IOException;
import java.util.ArrayList;

public class CsvController {

    public void writeTrialsToFiles(ArrayList<Trial> trials) throws IOException {
        new CsvWriter(trials).writeFiles();
    }

}

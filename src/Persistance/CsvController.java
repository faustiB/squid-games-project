package Persistance;

import Business.Edition;
import Business.Trial;
import Persistance.Csv.CsvReader;
import Persistance.Csv.CsvWriter;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;

public class CsvController {

    public void writeTrialsToFiles(ArrayList<Trial> trials,ArrayList<Edition> editions) throws IOException {
        new CsvWriter(trials, editions).writeFiles();
    }

    public ArrayList<Trial> readTrials() throws IOException, CsvException {
        return new CsvReader().readTrials();
    }
}

package Persistance;

import Business.Edition;
import Business.Trial;
import Persistance.Csv.CsvReader;
import Persistance.Csv.CsvWriter;
import Persistance.Json.JsonWriter;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;

public class CsvController {


    public ArrayList<Trial> readTrials() throws IOException, CsvException {
        return new CsvReader().readTrials();
    }

    public ArrayList<Edition> readEditions() throws IOException, CsvException {
        return new CsvReader().readEditions();
    }

    public void writeTrials(ArrayList<Trial> trials) throws IOException {
        CsvWriter cw = new CsvWriter();

        cw.writeTrials(trials);
    }

    public void writeEditions(ArrayList<Edition> editions) throws IOException {
        CsvWriter cw = new CsvWriter();

        cw.writeFullEditionsFiles(editions);
    }
}

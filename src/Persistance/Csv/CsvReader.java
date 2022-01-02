package Persistance.Csv;

import Business.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {


    ArrayList<Trial> trials = new ArrayList<>();
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Master> masters = new ArrayList<>();
    ArrayList<Thesis> theses = new ArrayList<>();
    ArrayList<Budget> budgets = new ArrayList<>();

    ArrayList<Edition> editions = new ArrayList<>();


    public ArrayList<Trial> readTrials() throws IOException, CsvException {

        FileReader fileReaderA = new FileReader("files/articles.csv");
        CSVReader readerA = new CSVReader(fileReaderA);

        String[] data;
        while ((data = readerA.readNext()) != null) {


            Article a = new Article(data[0],1,data[1],data[2],Integer.parseInt(data[3]),Integer.parseInt(data[4]),Integer.parseInt(data[5]));
            articles.add(a);

        }

        return trials;
    }


}

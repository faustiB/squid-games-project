package Persistance.Csv;

import Business.*;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvWriter {
    ArrayList<Trial> trials;
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Master> masters = new ArrayList<>();
    ArrayList<Thesis> theses = new ArrayList<>();
    ArrayList<Budget> budgets = new ArrayList<>();

    public CsvWriter(ArrayList<Trial> trials) {
        this.trials = trials;
    }

    private void separateArraylist() {
        for (Trial trial : trials) {
            if (trial instanceof Article) {
                articles.add((Article) trial);
            } else if (trial instanceof Master) {
                masters.add((Master) trial);
            } else if (trial instanceof Thesis) {
                theses.add((Thesis) trial);
            } else {
                budgets.add((Budget) trial);
            }
        }
    }

    //TODO: No chuta esto, he usado una librería de csv pero no funciona...
    /*
    public void writeFiles() throws IOException {
        separateArraylist();

        CSVWriter writerA = new CSVWriter(new FileWriter("files/articles.json"));
        for (Article a : articles) {
            System.out.println(a.toString());
            writerA.writeNext(new String[]{a.toString()});
        }
        //writerA.writeNext(articles.toArray(new String[0]));
        writerA.close();

        CSVWriter writerM = new CSVWriter(new FileWriter("files/masters.json"));
        for (Master m : masters) {
            System.out.println(m.toString());
            writerM.writeNext(new String[]{m.toString()});
        }
        writerM.writeNext(masters.toArray(new String[0]));
        writerM.close();

        /*CSVWriter writerT = new CSVWriter(new FileWriter("files/theses.json"));
        writerT.writeNext(theses.toArray(new String[0]));
        writerT.close();

        CSVWriter writerB = new CSVWriter(new FileWriter("files/budgets.json"));
        writerB.writeNext(budgets.toArray(new String[0]));
        writerB.close();
    }*/
}

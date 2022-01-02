package Persistance.Csv;

import Business.*;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CsvWriter {
    private ArrayList<Trial> trials;
    private ArrayList<Article> articles = new ArrayList<>();
    private ArrayList<Master> masters = new ArrayList<>();
    private ArrayList<Thesis> theses = new ArrayList<>();
    private ArrayList<Budget> budgets = new ArrayList<>();

    private ArrayList<Edition> editions;

    public CsvWriter(ArrayList<Trial> trials, ArrayList<Edition> editions) {
        this.trials = trials;
        this.editions = editions;
    }

    private void separateArraylist() {
        for (Trial trial : trials) {
            if (trial instanceof Article) {
                articles.add((Article) trial);
            } else if (trial instanceof Master) {
                masters.add((Master) trial);
            } else if (trial instanceof Thesis) {
                theses.add((Thesis) trial);
            } else if (trial instanceof Budget) {
                budgets.add((Budget) trial);
            }
        }
    }


    public void writeFiles() throws IOException {
        separateArraylist();

        writeArticles();
        writeMasters();
        writeTheses();
        writeBudgets();

        writeEditions();

        writeTrialsOfEditions();


    }

    private void writeTrialsOfEditions() throws IOException {
        FileWriter outputFileEdT = new FileWriter("files/editionsTrials.csv");
        CSVWriter writerEdT = new CSVWriter(outputFileEdT);

        for (Edition e : editions) {
            HashMap<Integer, String> trials = e.getNamesOfTrials();

            for (String value : trials.values()) {

                String[] arr = value.split("-");
                String[] data = {String.valueOf(e.getYear()), arr[1], arr[0]};

                writerEdT.writeNext(data);
            }

        }
        writerEdT.close();
    }

    private void writeEditions() throws IOException {
        FileWriter outputFileEd = new FileWriter("files/editions.csv");
        CSVWriter writerEd = new CSVWriter(outputFileEd);

        for (Edition e : editions) {
            writerEd.writeNext(e.getArrayDescription());
        }
        writerEd.close();
    }

    private void writeBudgets() throws IOException {
        FileWriter outputFileB = new FileWriter("files/budgets.csv");
        CSVWriter writerB = new CSVWriter(outputFileB);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Budget b : budgets) {
            writerB.writeNext(b.getArrayDescription());
        }
        writerB.close();
    }

    private void writeTheses() throws IOException {
        FileWriter outputFileT = new FileWriter("files/theses.csv");
        CSVWriter writerT = new CSVWriter(outputFileT);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Thesis t : theses) {
            writerT.writeNext(t.getArrayDescription());
        }
        writerT.close();
    }

    private void writeMasters() throws IOException {
        FileWriter outputFileM = new FileWriter("files/masters.csv");
        CSVWriter writerM = new CSVWriter(outputFileM);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Master m : masters) {
            writerM.writeNext(m.getArrayDescription());
        }
        writerM.close();
    }

    private void writeArticles() throws IOException {
        FileWriter outputFileA = new FileWriter("files/articles.csv");
        CSVWriter writerA = new CSVWriter(outputFileA);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Article a : articles) {
            writerA.writeNext(a.getArrayDescription());
        }
        writerA.close();
    }
}

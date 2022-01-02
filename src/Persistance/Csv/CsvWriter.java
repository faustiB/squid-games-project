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

    ArrayList<Edition> editions;

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
            } else if (trial instanceof Budget){
                budgets.add((Budget) trial);
            }
        }
    }



    public void writeFiles() throws IOException {
        separateArraylist();

        FileWriter outputFileA =  new FileWriter("files/articles.csv");
        CSVWriter writerA = new CSVWriter(outputFileA);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Article a : articles) {
            writerA.writeNext(a.getArrayDescription());
        }
        writerA.close();


        FileWriter outputFileM =  new FileWriter("files/masters.csv");
        CSVWriter writerM= new CSVWriter(outputFileM);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Master m : masters) {
            writerM.writeNext(m.getArrayDescription());
        }
        writerM.close();

        FileWriter outputFileT =  new FileWriter("files/theses.csv");
        CSVWriter writerT = new CSVWriter(outputFileT);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Thesis t : theses) {
            writerT.writeNext(t.getArrayDescription());
        }
        writerT.close();

        FileWriter outputFileB =  new FileWriter("files/budgets.csv");
        CSVWriter writerB = new CSVWriter(outputFileB);

        /*String[] headerA = {"NameTrial","NameArticle","MagazineQuartile","AcceptProbability","RevisionProbability","DenyProbability"};
        writerA.writeNext(headerA);*/

        for (Budget b : budgets) {
            writerB.writeNext(b.getArrayDescription());
        }
        writerB.close();


    }
}

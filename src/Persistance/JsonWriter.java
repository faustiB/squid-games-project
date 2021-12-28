package Persistance;

import Business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriter {
    ArrayList<Trial> trials;
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<Master> masters = new ArrayList<>();
    ArrayList<Thesis> theses = new ArrayList<>();
    ArrayList<Budget> budgets = new ArrayList<>();

    public JsonWriter(ArrayList<Trial> trials) {
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

    public void writeFiles() throws IOException {
        separateArraylist();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String article = gson.toJson(articles);
        String master = gson.toJson(masters);
        String thesis = gson.toJson(theses);
        String budget = gson.toJson(budgets);

        FileWriter writerA = new FileWriter("files/articles.json");
        writerA.write(article);
        writerA.close();

        FileWriter writerM = new FileWriter("files/masters.json");
        writerM.write(master);
        writerM.close();

        FileWriter writerT = new FileWriter("files/theses.json");
        writerT.write(thesis);
        writerT.close();

        FileWriter writerB = new FileWriter("files/budgets.json");
        writerB.write(budget);
        writerB.close();
    }


}

package Persistance.Json;

import Business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriter {
    private ArrayList<Edition> editions;
    private ArrayList<Trial> trials;
    private ArrayList<Article> articles = new ArrayList<>();
    private ArrayList<Master> masters = new ArrayList<>();
    private ArrayList<Thesis> theses = new ArrayList<>();
    private ArrayList<Budget> budgets = new ArrayList<>();

    public JsonWriter(ArrayList<Trial> trials, ArrayList<Edition> editions) {
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

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String articleJson = gson.toJson(articles);
        String masterJson = gson.toJson(masters);
        String thesisJson = gson.toJson(theses);
        String budgetJson = gson.toJson(budgets);

        String editionJson = gson.toJson(editions);


        FileWriter writerA = new FileWriter("files/articles.json");
        writerA.write(articleJson);
        writerA.close();

        FileWriter writerM = new FileWriter("files/masters.json");
        writerM.write(masterJson);
        writerM.close();

        FileWriter writerT = new FileWriter("files/theses.json");
        writerT.write(thesisJson);
        writerT.close();

        FileWriter writerB = new FileWriter("files/budgets.json");
        writerB.write(budgetJson);
        writerB.close();

        FileWriter writerE = new FileWriter("files/editions.json");
        writerE.write(editionJson);
        writerE.close();
    }


}

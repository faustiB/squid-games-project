package Persistance.Json;

import Business.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class JsonReader  {
    Gson gson = new Gson();

    private ArrayList<Article> processArticles() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/articles.json"), new TypeToken<ArrayList<Article>>() {}.getType());
    }

    private ArrayList<Master> processMasters() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/masters.json"), new TypeToken<ArrayList<Master>>() {}.getType());
    }

    private ArrayList<Thesis> processTheses() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/theses.json"), new TypeToken<ArrayList<Thesis>>() {}.getType());
    }

    private ArrayList<Budget> processBugdets() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/budgets.json"), new TypeToken<ArrayList<Budget>>() {}.getType());
    }

    public ArrayList<Trial> readFilesTrials() throws FileNotFoundException {
        ArrayList<Article> articles = processArticles();
        ArrayList<Master> masters = processMasters();
        ArrayList<Thesis> theses = processTheses();
        ArrayList<Budget> budgets = processBugdets();

        ArrayList<Trial> trials = new ArrayList<>();
        trials.addAll(articles);
        trials.addAll(masters);
        trials.addAll(theses);
        trials.addAll(budgets);

        return trials;
    }


    public ArrayList<Edition> readFilesEditions() throws FileNotFoundException{
        return gson.fromJson(new FileReader("files/editions.json"), new TypeToken<ArrayList<Edition>>() {}.getType());
    }



}

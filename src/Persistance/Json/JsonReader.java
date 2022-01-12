package Persistance.Json;

import Business.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Class created in order to implement methods that help the program read information
 * using JSON persistence.
 */
public class JsonReader {
    private Gson gson = new Gson();

    /**
     * Method used to process the different articles.
     * @return arraylist of articles.
     * @throws FileNotFoundException: if the file is not found...
     */
    private ArrayList<Article> processArticles() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/articles.json"), new TypeToken<ArrayList<Article>>() {
        }.getType());
    }

    /**
     * Method used to process the different masters.
     * @return arraylist of masters.
     * @throws FileNotFoundException: if the file is not found...
     */
    private ArrayList<Master> processMasters() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/masters.json"), new TypeToken<ArrayList<Master>>() {
        }.getType());
    }

    /**
     * Method used to process the different theses.
     * @return arraylist of theses.
     * @throws FileNotFoundException: if the file is not found...
     */
    private ArrayList<Thesis> processTheses() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/theses.json"), new TypeToken<ArrayList<Thesis>>() {
        }.getType());
    }

    /**
     * Method used to process the different budgets.
     * @return arraylist of budgets.
     * @throws FileNotFoundException: if the file is not found...
     */
    private ArrayList<Budget> processBudgets() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/budgets.json"), new TypeToken<ArrayList<Budget>>() {
        }.getType());
    }

    /**
     * Method used to coordinate the processing of the files into arraylists
     * @return arraylist of trials.
     * @throws FileNotFoundException: if the files are not found...
     */
    public ArrayList<Trial> readFilesTrials() throws FileNotFoundException {
        ArrayList<Article> articles = processArticles();
        ArrayList<Master> masters = processMasters();
        ArrayList<Thesis> theses = processTheses();
        ArrayList<Budget> budgets = processBudgets();

        ArrayList<Trial> trials = new ArrayList<>();
        trials.addAll(articles);
        trials.addAll(masters);
        trials.addAll(theses);
        trials.addAll(budgets);

        return trials;
    }

    /**
     * Method used to read the different editions.
     * @return arraylist of editions
     * @throws FileNotFoundException: if the file is not found...
     */
    public ArrayList<Edition> readFilesEditions() throws FileNotFoundException {
        return gson.fromJson(new FileReader("files/editions.json"), new TypeToken<ArrayList<Edition>>() {
        }.getType());
    }
}

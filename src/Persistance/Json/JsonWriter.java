package Persistance.Json;

import Business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class created in order to implement methods that help the program apply
 * a persistence using JSON.
 */
public class JsonWriter {

    private ArrayList<Article> articles = new ArrayList<>();
    private ArrayList<Master> masters = new ArrayList<>();
    private ArrayList<Thesis> theses = new ArrayList<>();
    private ArrayList<Budget> budgets = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Method used in order to separate the trials' arraylist on different arraylists.
     * @param trials: arraylist of trials.
     */
    private void separateArraylist(ArrayList<Trial> trials) {
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

    /**
     * Method used to write the trials to the different files.
     * @param trials: arraylist of trials.
     * @throws IOException: input output exception.
     */
    public void writeTrials(ArrayList<Trial> trials) throws IOException {
        separateArraylist(trials);

        String articleJson = gson.toJson(articles);
        String masterJson = gson.toJson(masters);
        String thesisJson = gson.toJson(theses);
        String budgetJson = gson.toJson(budgets);


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
    }

    /**
     * Method used to write the different editions to the files.
     * @param editions: arraylist of editions
     * @throws IOException: input output exception.
     */
    public void writeEditions(ArrayList<Edition> editions) throws IOException {
        String editionJson = gson.toJson(editions);

        FileWriter writerE = new FileWriter("files/editions.json");
        writerE.write(editionJson);
        writerE.close();
    }


    /**
     * MEthod to write the current status of the game.
     * @param players players that are playing the game
     * @throws IOException IOException
     */
    public void writeStatusGame(ArrayList<Player> players) throws IOException {
        String gameToJson = gson.toJson(players);

        FileWriter writerG = new FileWriter("files/statusGame.json");
        writerG.write(gameToJson);

        writerG.close();
    }
}

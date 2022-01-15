package Persistance.Csv;

import Business.*;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class created in order to implement methods that help the program apply
 * a persistence using CSV.
 */
public class CsvWriter {

    private ArrayList<Article> articles = new ArrayList<>();
    private ArrayList<Master> masters = new ArrayList<>();
    private ArrayList<Thesis> theses = new ArrayList<>();
    private ArrayList<Budget> budgets = new ArrayList<>();

    /**
     * Method created to separate the arraylist of trials into the different types.
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
     * Method used to coordinate the writing of editions.
     * @param editions: arraylist of editions.
     * @throws IOException input output exception...
     */
    public void writeFullEditionsFiles(ArrayList<Edition> editions) throws IOException {
        writeEditions(editions);
        writeTrialsOfEditions(editions);
    }

    /**
     * Method created to write the trials of the different editions to the file.
     * @param editions: arraylist of editions.
     * @throws IOException: input output exception...
     */
    private void writeTrialsOfEditions(ArrayList<Edition> editions) throws IOException {
        FileWriter outputFileEdT = new FileWriter("files/editionsTrials.csv");
        CSVWriter writerEdT = new CSVWriter(outputFileEdT);

        for (Edition e : editions) {
            HashMap<Integer, String> trials = e.getNamesOfTrialsForDescription();
            for (String value : trials.values()) {

                String[] arr = value.split("-");
                String[] data = {String.valueOf(e.getYear()), arr[1], arr[0]};

                writerEdT.writeNext(data);
            }
        }
        writerEdT.close();
    }

    /**
     * Method used to write the editions to the different files.
     * @param editions: arraylist of editions.
     * @throws IOException: input output exception.
     */
    private void writeEditions(ArrayList<Edition> editions) throws IOException {
        FileWriter outputFileEd = new FileWriter("files/editions.csv");
        CSVWriter writerEd = new CSVWriter(outputFileEd);

        for (Edition e : editions) {
            writerEd.writeNext(e.getArrayDescription());
        }
        writerEd.close();
    }

    /**
     * Method used to write the budgets to the file.
     * @throws IOException: input output exception.
     */
    private void writeBudgets() throws IOException {
        FileWriter outputFileB = new FileWriter("files/budgets.csv");
        CSVWriter writerB = new CSVWriter(outputFileB);

        for (Budget b : budgets) {
            writerB.writeNext(b.getArrayDescription());
        }
        writerB.close();
    }

    /**
     * Method used to write the theses to the file.
     * @throws IOException: input output exception.
     */
    private void writeTheses() throws IOException {
        FileWriter outputFileT = new FileWriter("files/theses.csv");
        CSVWriter writerT = new CSVWriter(outputFileT);

        for (Thesis t : theses) {
            writerT.writeNext(t.getArrayDescription());
        }
        writerT.close();
    }

    /**
     * Method used to write the masters to the file.
     * @throws IOException: input output exception.
     */
    private void writeMasters() throws IOException {
        FileWriter outputFileM = new FileWriter("files/masters.csv");
        CSVWriter writerM = new CSVWriter(outputFileM);

        for (Master m : masters) {
            writerM.writeNext(m.getArrayDescription());
        }
        writerM.close();
    }

    /**
     * Method used to write the articles to the file.
     * @throws IOException: input output exception.
     */
    private void writeArticles() throws IOException {
        FileWriter outputFileA = new FileWriter("files/articles.csv");
        CSVWriter writerA = new CSVWriter(outputFileA);

        for (Article a : articles) {
            writerA.writeNext(a.getArrayDescription());
        }
        writerA.close();
    }

    /**
     * Method used to coordinate the writing of the arraylists to the files.
     * @param trials: arraylist of trials
     * @throws IOException input output exception.
     */
    public void writeTrials(ArrayList<Trial> trials) throws IOException {
        separateArraylist(trials);

        writeArticles();
        writeMasters();
        writeTheses();
        writeBudgets();
    }


    /**
     * MEthod to write the current status of the game.
     * @param players players that are playing the game
     * @throws IOException IOException
     */
    public void writeStatusGame(ArrayList<Player> players) throws IOException {
        FileWriter outputFileEd = new FileWriter("files/statusGame.csv");
        CSVWriter writerEd = new CSVWriter(outputFileEd);

        for (Player player : players) {
            writerEd.writeNext(player.getArrayDescription());
        }
        writerEd.close();
    }
}

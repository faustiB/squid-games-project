package Persistance.Csv;

import Business.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class created in order to implement methods that help the program read information
 * using CSV persistence.
 */
public class CsvReader {

    private ArrayList<Master> masters = new ArrayList<>();
    private ArrayList<Thesis> theses = new ArrayList<>();
    private ArrayList<Budget> budgets = new ArrayList<>();
    private ArrayList<Article> articles = new ArrayList<>();

    /**
     * Method used to read the trials from the different files.
     * @throws CsvValidationException if the csv format is not valid...
     * @throws IOException input output exception.
     */
    public void readTrialsFromFile() throws CsvValidationException, IOException {
        articles = readArticles();
        masters = readMasters();
        theses = readTheses();
        budgets = readBudgets();
    }

    /**
     * Method used to read the different trials.
     * @return arraylist of trials.
     * @throws IOException input output exception.
     * @throws CsvException if the csv format is not valid...
     */
    public ArrayList<Trial> readTrials() throws IOException, CsvException {
        ArrayList<Trial> trials = new ArrayList<>();

        readTrialsFromFile();
        trials.addAll(articles);
        trials.addAll(masters);
        trials.addAll(theses);
        trials.addAll(budgets);

        return trials;
    }

    /**
     * Method used to read the different editions.
     * @return arraylist of editions.
     * @throws CsvException if the csv format is not valid...
     * @throws IOException input output exception.
     */
    public ArrayList<Edition> readEditions() throws IOException, CsvException {

        ArrayList<Edition> editions = new ArrayList<>();
        ArrayList<String[]> trialsEditions = readTrialsEditions();

        FileReader fileReaderE = new FileReader("files/editions.csv");
        CSVReader readerA = new CSVReader(fileReaderE);

        readTrialsFromFile();

        String[] data;
        while ((data = readerA.readNext()) != null) {

            ArrayList<Trial> trials = new ArrayList<>();
            for (String[] line : trialsEditions) {

                if (data[0].equals(line[0])) {
                    switch (line[2]) {
                        case "Paper Publication"-> {
                            Article a = getArticleFromFile(line[1]);
                            trials.add(a);
                        }
                        case "Doctoral thesis defense"-> {
                            Thesis a =  getThesesFromFile(line[1]);
                            trials.add(a);
                        }
                        case "Master studies"-> {
                            Master a =  getMasterFromFile(line[1]);
                            trials.add(a);
                        }
                        case "Budget request"-> {
                            Budget a =  getBudgetFromFile(line[1]);
                            trials.add(a);
                        }
                    }
                }

            }
            Edition e = new Edition(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]),trials);
            editions.add(e);
        }

        return editions;
    }

    /**
     * Method created to retrieve the Budget
     * @param name: string with the name.
     * @return Budget object
     */
    private Budget getBudgetFromFile(String name) {
        Budget ret = null;
        for (Budget a : budgets) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

    /**
     * Method created to retrieve the Master
     * @param name: string with the name.
     * @return Master object
     */
    private Master getMasterFromFile(String name) {
        Master ret = null;
        for (Master a : masters) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

    /**
     * Method created to retrieve the Thesis
     * @param name: string with the name.
     * @return Thesis object
     */
    private Thesis getThesesFromFile(String name) {
        Thesis ret = null;
        for (Thesis a : theses) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

    /**
     * Method created to retrieve the Article
     * @param name: string with the name.
     * @return Article object
     */
    private Article getArticleFromFile(String name) {
        Article ret = null;
        for (Article a : articles) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

    /**
     * Method created to read the trials of the different editions.
     * @return arraylist of strings.
     * @throws CsvValidationException: if the csv format is not valid...
     * @throws IOException: input output exception.
     */
    private ArrayList<String[]> readTrialsEditions() throws IOException, CsvException {
        FileReader fileReaderA = new FileReader("files/editionsTrials.csv");
        CSVReader readerA = new CSVReader(fileReaderA);

        ArrayList<String[]> trialsEditions = new ArrayList<>();
        String[] data;
        while ((data = readerA.readNext()) != null) {
            trialsEditions.add(data);
        }

        return trialsEditions;
    }

    /**
     * Method used to read the different budgets from files.
     * @return arraylist of budgets.
     * @throws CsvValidationException: if the csv format is not valid...
     * @throws IOException: input output exception.
     */
    private ArrayList<Budget> readBudgets() throws CsvValidationException, IOException {
        ArrayList<Budget> budgets = new ArrayList<>();
        FileReader fileReaderA = new FileReader("files/budgets.csv");
        CSVReader readerA = new CSVReader(fileReaderA);

        String[] data;
        while ((data = readerA.readNext()) != null) {
            Budget b = new Budget(data[0], 4, data[1], Integer.parseInt(data[2]));
            budgets.add(b);
        }

        return budgets;
    }

    /**
     * Method used to read the different theses from files.
     * @return arraylist of theses.
     * @throws CsvValidationException: if the csv format is not valid...
     * @throws IOException: input output exception.
     */
    private ArrayList<Thesis> readTheses() throws CsvValidationException, IOException {
        ArrayList<Thesis> theses = new ArrayList<>();
        FileReader fileReaderA = new FileReader("files/theses.csv");
        CSVReader readerA = new CSVReader(fileReaderA);

        String[] data;
        while ((data = readerA.readNext()) != null) {
            Thesis t = new Thesis(data[0], 3, data[1], Integer.parseInt(data[2]));
            theses.add(t);
        }

        return theses;
    }

    /**
     * Method used to read the different masters from files.
     * @return arraylist of masters.
     * @throws CsvValidationException: if the csv format is not valid...
     * @throws IOException: input output exception.
     */
    private ArrayList<Master> readMasters() throws IOException, CsvValidationException {
        ArrayList<Master> masters = new ArrayList<>();
        FileReader fileReaderA = new FileReader("files/masters.csv");
        CSVReader readerA = new CSVReader(fileReaderA);

        String[] data;
        while ((data = readerA.readNext()) != null) {
            Master m = new Master(data[0], 2, data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            masters.add(m);
        }

        return masters;

    }

    /**
     * Method used to read the different articles from files.
     * @return arraylist of articles.
     * @throws CsvValidationException: if the csv format is not valid...
     * @throws IOException: input output exception.
     */
    private ArrayList<Article> readArticles() throws CsvValidationException, IOException {
        ArrayList<Article> articles = new ArrayList<>();

        FileReader fileReaderA = new FileReader("files/articles.csv");
        CSVReader readerA = new CSVReader(fileReaderA);

        String[] data;
        while ((data = readerA.readNext()) != null) {
            Article a = new Article(data[0], 1, data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
            articles.add(a);
        }

        return articles;
    }

    /**
     * Method used to read the status of a past game.
     * @return arraylist of players with the last trial they played.
     * @throws IOException IOException
     * @throws CsvException exception with csv read
     */
    public ArrayList<Player> readStatusGame() throws IOException, CsvException {

        ArrayList<Player> players = new ArrayList<>();
        String[] data;

        FileReader fileReaderE = new FileReader("files/statusGame.csv");
        CSVReader readerA = new CSVReader(fileReaderE);


        while ((data = readerA.readNext()) != null) {

            Trial t = new Trial(data[4]);

            Player p = new Player(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]),Boolean.parseBoolean(data[3]),t);
            players.add(p);
        }

        return players;
    }

}

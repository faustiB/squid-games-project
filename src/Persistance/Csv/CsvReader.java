package Persistance.Csv;

import Business.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {

    private ArrayList<Master> masters = new ArrayList<>();
    private ArrayList<Thesis> theses = new ArrayList<>();
    private ArrayList<Budget> budgets = new ArrayList<>();
    private ArrayList<Article> articles = new ArrayList<>();

    public void readTrialsFromFile() throws CsvValidationException, IOException {
        articles = readArticles();
        masters = readMasters();
        theses = readTheses();
        budgets = readBudgets();
    }


    public ArrayList<Trial> readTrials() throws IOException, CsvException {

        ArrayList<Trial> trials = new ArrayList<>();

        readTrialsFromFile();
        trials.addAll(articles);
        trials.addAll(masters);
        trials.addAll(theses);
        trials.addAll(budgets);

        return trials;
    }

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

    private Budget getBudgetFromFile(String name) {
        Budget ret = null;
        for (Budget a : budgets) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

    private Master getMasterFromFile(String name) {
        Master ret = null;
        for (Master a : masters) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

    private Thesis getThesesFromFile(String name) {
        Thesis ret = null;
        for (Thesis a : theses) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

    private Article getArticleFromFile(String name) {
        Article ret = null;
        for (Article a : articles) {
            if (a.checkName(name)) {
                ret = a;
            }
        }
        return ret;
    }

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


}

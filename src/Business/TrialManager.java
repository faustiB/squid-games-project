package Business;

import Persistance.Csv.*;
import Persistance.Json.*;
import Presentation.Menu;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class created to manage the different trials
 */
public class TrialManager {

    private static final int ARTICLE = 1;
    private static final int MASTER = 2;
    private static final int THESIS = 3;
    private static final int BUDGET = 4;

    private final Menu menu = new Menu();
    private ArrayList<Trial> trials;

    /**
     * Constructor of the class.
     * @param choice: true for csv, false for json
     */
    public TrialManager(Boolean choice) {
        if (choice) {
            try {
                this.trials = readTrials_CSV();
            } catch (FileNotFoundException e) {
                this.trials = new ArrayList<>();
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.trials = readTrials_JSON();
            } catch (FileNotFoundException e) {
                this.trials = new ArrayList<>();
            }
        }
    }

    /**
     * Getter for the trials
     * @return arraylist of trials
     */
    public ArrayList<Trial> getTrials() {
        return trials;
    }

    /**
     * Getter of a trial via index
     * @param index: index to return
     * @return trial selected
     */
    public Trial getSpecificTrial(int index) {
        return trials.get(index);
    }

    /**
     * Getter for the size of the trials array
     * @return size of arrays
     */
    public int getTrialsSize() {
        return trials.size();
    }

    /**
     * Method to get the trial type
     * @param type: name of the type
     * @return type of trial
     */
    private int getTrialType(String type) {
        return switch (type) {
            case "Article" -> 1;
            case "Master" -> 2;
            case "Thesis" -> 3;
            default -> 4;
        };
    }

    /**
     * Checks if the length of trials is not zero
     * @return true for not zero
     */
    public boolean checkLengthOfTrialsIsNotZero() {
        return getTrialsSize() != 0;
    }

    /**
     * Creates a new trial
     * @param trialType: type of the trial
     */
    public void createTrial(int trialType) {
        menu.spacing();
        switch (trialType) {
            case ARTICLE -> createArticleTrial();
            case MASTER -> createMasterTrial();
            case THESIS -> createThesisTrial();
            case BUDGET -> createBudgetTrial();
        }
        menu.spacing();
        menu.showMessage("The trial was created successfully!");
    }

    /**
     * Creation of article trial
     */
    private void createArticleTrial() {
        int acceptProbability = 0, revisionProbability = 0, denyProbability = 0, sum = 0;

        String name = menu.askForString("Enter the trial’s name: ");
        String journalName = menu.askForString("Enter the journal’s name: ");
        String magazineQuartile = menu.askForQuartile("Enter the journal’s quartile: ");

        while (sum != 100) {
            acceptProbability = menu.askForIntegerBetweenDelimiters
                    ("Enter the acceptance probability: ", 0, 100);
            revisionProbability = menu.askForIntegerBetweenDelimiters
                    ("Enter the revision probability: ", 0, 100 - acceptProbability);
            denyProbability = menu.askForIntegerBetweenDelimiters
                    ("Enter the rejection probability: ", 0, 100 - (acceptProbability + revisionProbability));

            sum = acceptProbability + revisionProbability + denyProbability;

            if (sum != 100) {
                menu.spacing();
                menu.showMessage("Error: the percentages entered must sum 100.");
                menu.spacing();
            }
        }

        Article article = new Article(name, getTrialType("Article"), journalName, magazineQuartile, acceptProbability, revisionProbability, denyProbability);

        trials.add(article);
    }

    /**
     * Creation of master trial
     */
    private void createMasterTrial() {

        String name = menu.askForString("Enter the trial’s name: ");
        String masterName = menu.askForString("Enter the master’s name: ");

        int ects = menu.askForIntegerBetweenDelimiters("Enter the master’s ECTS number: ", 60, 120);
        int creditPass = menu.askForIntegerBetweenDelimiters("Enter the credit pass probability: ", 0, 100);

        Master master = new Master(name, getTrialType("Master"), masterName, ects, creditPass);

        trials.add(master);
    }

    /**
     * Creation of Thesis trial
     */
    private void createThesisTrial() {

        String name = menu.askForString("Enter the trial’s name: ");
        String thesisName = menu.askForString("Enter the thesis' name: ");

        int difficulty = menu.askForIntegerBetweenDelimiters("Enter the defense difficulty: ", 1, 10);

        Thesis thesis = new Thesis(name, getTrialType("Thesis"), thesisName, difficulty);

        trials.add(thesis);
    }

    /**
     * Creation of budget trial
     */
    private void createBudgetTrial() {

        String name = menu.askForString("Enter the trial’s name: ");
        String budgetName = menu.askForString("Enter the entity’s name: ");

        int amount = menu.askForIntegerBetweenDelimiters("Enter the budget amount: ", 1000, 2000000000);

        Budget budget = new Budget(name, getTrialType("Budget"), budgetName, amount);

        trials.add(budget);
    }

    /**
     * List of desired trial
     */
    public void listTrials() {
        int option;
        if (getTrialsSize() == 0) {
            menu.showMessage("\nNo trials created yet...\n");
        } else {
            menu.showMessage("\nHere are the current trials, do you want to see more details or go back ?\n");

            option = getTrialSelectedInput();

            if (option != getTrialsSize()) {
                if (menu.checkBetweenNumbersType(option + 1, 1, getTrialsSize())) {
                    if (trials.get(option) instanceof Article) {
                        menu.showDetailsArticle((Article) trials.get(option));
                    } else if (trials.get(option) instanceof Thesis) {
                        menu.showDetailsThesis((Thesis) trials.get(option));
                    } else if (trials.get(option) instanceof Master) {
                        menu.showDetailsMaster((Master) trials.get(option));
                    } else if (trials.get(option) instanceof Budget) {
                        menu.showDetailsBudget((Budget) trials.get(option));
                    }
                }
            } else {
                menu.showMessage("\nGoing back to the previous menu...\n");
            }
        }
    }

    /**
     * Showing of available trials via screen.
     */
    public void showingOfTrials() {
        menu.showTrials(trials);
    }

    /**
     * Get trial selected by screen of listed trials.
     * @return input selected by screen
     */
    public int getTrialSelectedInput() {
        int option = -1;
        boolean check = false;

        if (checkLengthOfTrialsIsNotZero()) {
            showingOfTrials();
            menu.spacing();
            menu.showMessage(getTrialsSize() + 1 + ") Back");

            do {
                option = menu.askForInteger("Enter an option: ");
                if (menu.checkBetweenNumbersType(option, 1, getTrialsSize() + 1)) {
                    check = true;
                }
            } while (!check);

            option--;
        }

        return option;
    }

    /**
     * Deletion of trials.
     */
    public void deleteTrial() {

        int option;

        if (getTrialsSize() == 0) {
            menu.showMessage("\nNo trials created yet...\n");
        } else {
            menu.showMessage("\nWhich trial do you want to delete ?\n");

            option = getTrialSelectedInput();
            if (menu.checkBetweenNumbersType(option + 1, 1, getTrialsSize())) {

                String trialName = menu.askForString("\nEnter the trial's name for confimation: ");
                if (trials.get(option).checkName(trialName)) {
                    trials.remove(option);
                    menu.showMessage("\nThe trial was successfully deleted.\n");
                } else {
                    menu.showMessage("\nConfirmation failed, trial was not deleted. \n");
                }
            }
        }
    }

    /**
     * This method generates an arraylist of trials and returns it from csv
     * @return ArrayList of trials
     * @throws IOException input output exception
     * @throws CsvException csv exception
     */
    public ArrayList<Trial> readTrials_CSV() throws IOException, CsvException {
        return new CsvReader().readTrials();
    }

    /**
     * This method generates an arraylist of trials and returns it from json
     * @return ArrayList of trials
     * @throws FileNotFoundException file not found
     */
    public ArrayList<Trial> readTrials_JSON() throws FileNotFoundException {
        return new JsonReader().readFilesTrials();
    }

    /**
     * This method calls the methods that write the trials to the files
     * @param choice: true for csv, false for json
     * @throws IOException input output exception
     */
    public void writeTrialsToFiles(Boolean choice) throws IOException {
        if (choice) {
            new CsvWriter().writeTrials(this.trials);
        } else {
            new JsonWriter().writeTrials(this.trials);
        }
    }

    /**
     * Method create to return a the arraylist of trials via an arraylist of names
     * @param names: arraylist of names
     * @return arraylist of trials
     */
    public ArrayList<Trial> getSpecificTrialsByNames(ArrayList<String> names) {
        ArrayList<Trial> trialsToReturn = new ArrayList<>();

        for (String name : names) {
            for (Trial t : this.trials) {
                if (t.getName().equalsIgnoreCase(name)){
                    trialsToReturn.add(t);
                }
            }
        }

        return trialsToReturn;
    }
}

package Business;

import Presentation.Menu;

import java.util.ArrayList;

public class TrialManager {

    private static final int ARTICLE = 1;
    private static final int MASTER = 2;
    private static final int THESIS = 3;
    private static final int BUDGET = 4;
    Menu menu = new Menu();
    ArrayList<Trial> trials = new ArrayList<>();

    public Trial getSpecificTrial(int index) {
        return trials.get(index);
    }

    public boolean checkLengthOfTrialsIsNotZero() {
        return getTrialsSize() != 0;
    }

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

    //TODO: Guillem -> yo leeria todo el fichero y crearia los diferentes arrays. Y dentro de ahi, ir añadiendo trials.

    /**
     * Creation of article trial
     */
    private void createArticleTrial() {

        String name = menu.askForString("Enter the trial’s name: ");
        String journalName = menu.askForString("Enter the journal’s name: ");
        String magazineQuartile = menu.askForQuartile("Enter the journal’s quartile: ");

        int acceptProbability = menu.askForIntegerBetweenDelimeters("Enter the acceptance probability: ", 0, 100);
        int revisionProbability = menu.askForIntegerBetweenDelimeters("Enter the revision probability: ", 0, 100 - acceptProbability);
        int denyProbability = menu.askForIntegerBetweenDelimeters("Enter the rejection probability: ", 0, 100 - (acceptProbability + revisionProbability));

        Article article = new Article(name, journalName, magazineQuartile, acceptProbability, revisionProbability, denyProbability);

        trials.add(article);
    }

    /**
     * Creation of master trial
     */
    private void createMasterTrial() {

        String name = menu.askForString("Enter the trial’s name: ");
        String masterName = menu.askForString("Enter the master’s name: ");

        int ects = menu.askForIntegerBetweenDelimeters("Enter the master’s ECTS number: ", 60, 120);
        int creditPass = menu.askForIntegerBetweenDelimeters("Enter the credit pass probability: ", 0, 100);

        Master master = new Master(name, masterName, ects, creditPass);

        trials.add(master);
    }

    /**
     * Creation of Thesis trial
     */
    private void createThesisTrial() {

        String name = menu.askForString("Enter the trial’s name: ");
        String thesisName = menu.askForString("Enter the thesis' name: ");

        int difficulty = menu.askForIntegerBetweenDelimeters("Enter the defense difficulty: ", 1, 10);

        Thesis thesis = new Thesis(name, thesisName, difficulty);

        trials.add(thesis);
    }

    /**
     * Creation of budget trial
     */
    private void createBudgetTrial() {

        String name = menu.askForString("Enter the trial’s name: ");
        String budgetName = menu.askForString("Enter the entity’s name: ");

        int amount = menu.askForIntegerBetweenDelimeters("Enter the budget amount: ", 1000, 2000000000);

        Budget budget = new Budget(name, budgetName, amount);

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
                    //if (option == trials.size()) {
                       // menu.showMessage("\nGoing Back to previous menu...\n");
                    //} else {
                        if (trials.get(option) instanceof Article) {
                            menu.showDetailsArticle((Article) trials.get(option));

                        } else if (trials.get(option) instanceof Thesis) {
                            menu.showDetailsThesis((Thesis) trials.get(option));

                        } else if (trials.get(option) instanceof Master) {
                            menu.showDetailsMaster((Master) trials.get(option));

                        } else if (trials.get(option) instanceof Budget) {
                            menu.showDetailsBudget((Budget) trials.get(option));

                        }
                    //}
                }

            } else {
                menu.showMessage("\nGoing back to the previous menu...\n");
            }
        }


    }

    /**
     * showing of available trials via screen.
     */
    public void showingOfTrials() {
        menu.showTrials(trials);
    }

    /**
     * Get trial selected by scren of listed trials.
     *
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
                if (menu.checkBetweenNumbersType(option ,1,getTrialsSize() + 1)){
                    check = true;
                }
            } while(!check);

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

                String trialName = menu.askForString("\nEnter the trial's name for confimation :");
                if (trials.get(option).checkName(trialName)) {
                    trials.remove(option);
                    menu.showMessage("\nThe trial was successfully deleted.\n");
                } else {
                    menu.showMessage("\nConfirmation failed, trial was not deleted. \n");
                }
            }
        }


    }

    public int getTrialsSize() {
        return trials.size();
    }
}

package Business;

import Presentation.Menu;

import java.util.ArrayList;
import java.util.LinkedList;

public class TrialManager {

    Menu menu = new Menu();
    ArrayList<Trial> trials = new ArrayList<>();

    private static final int ARTICLE = 1;
    private static final int MASTER = 2;
    private static final int THESIS = 3;
    private static final int BUDGET = 4;

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
    private void createArticleTrial() {
        String name = menu.askForString("Enter the trial’s name: ");
        String journalName = menu.askForString("Enter the journal’s name: ");
        String magazineQuartile = menu.askForQuartile("Enter the journal’s quartile: ");
        int acceptProbability = menu.askForIntegerBetweenDelimeters("Enter the acceptance probability: ",0,100);
        int revisionProbability = menu.askForIntegerBetweenDelimeters("Enter the revision probability: ",0,100);
        //check2SumOverLimit(limit,num1,num2); meter numeros en array y pasarlo como parametro,
        int denyProbability = menu.askForIntegerBetweenDelimeters("Enter the rejection probability: ",0,100);
        //check3SumOverLimit(limit,num1,num2,num3);
        Article article = new Article(name, journalName, magazineQuartile, acceptProbability, revisionProbability, denyProbability);
        trials.add(article);
    }

    private void createMasterTrial() {
        String name = menu.askForString("Enter the trial’s name: ");
        String masterName = menu.askForString("Enter the master’s name: ");
        int ects = menu.askForInteger("Enter the master’s ECTS number: ");
        int creditPass = menu.askForInteger("Enter the credit pass probability: ");

        Master master = new Master(name, masterName, ects, creditPass);
        trials.add(master);
    }

    private void createThesisTrial() {
        String name = menu.askForString("Enter the trial’s name: ");
        String thesisName = menu.askForString("Enter the master’s name: ");
        int difficulty = menu.askForInteger("Enter the defense difficulty: ");

        Thesis thesis = new Thesis(name, thesisName, difficulty);
        trials.add(thesis);
    }

    private void createBudgetTrial() {
        String name = menu.askForString("Enter the trial’s name: ");
        String budgetName = menu.askForString("Enter the entity’s name: ");
        int amount = menu.askForInteger("Enter the budget amount: ");

        Budget budget = new Budget(name, budgetName, amount);
        trials.add(budget);
    }

    public void listTrials() {
        int option, i = 0;

        option = getTrialSelectedInput();
        if (option == -1) {
            menu.showMessage("No trials created yet...");
        } else {
            if (menu.checkBetweenNumbersType(option, 0, trials.size() - 1)) {

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
        }

    }

    private int getTrialSelectedInput() {
        int option = -1, i;
        if (trials.size() != 0) {
            for (i = 0; i < trials.size(); i++) {
                menu.showMessage(i + 1 + ") " + trials.get(i).name);
            }
            menu.spacing();
            menu.showMessage(i + 1 + ") Back");
            option = menu.askForInteger("Enter an option: ");
            option--;
        }


        return option;
    }

    //TODO: Implementar metodo y posibles cambios en el controler, puesto para compilar.
    public void deleteTrial() {
    }
}

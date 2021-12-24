package Business;

import Presentation.Menu;

import java.util.LinkedList;

public class TrialManager {

    Menu m = new Menu();
    LinkedList<Trial> trials = new LinkedList<>();

    private static final int ARTICLE = 1;
    private static final int MASTER = 2;
    private static final int THESIS = 3;
    private static final int BUDGET = 4;

    public void createTrial(int trialType) {
        m.spacing();
        switch (trialType){
            case ARTICLE -> createArticleTrial();
            case MASTER -> createMasterTrial();
            case THESIS -> createThesisTrial();
            case BUDGET -> createBudgetTrial();
        }
        m.spacing();
        m.showMessage("The trial was created successfully!");
    }

    //TODO: Guillem -> yo leeria todo el fichero y crearia los diferentes arrays. Y dentro de ahi, ir añadiendo trials.
    private void createArticleTrial() {
        String name = m.askForString("Enter the trial’s name: ");
        String journalName = m.askForString("Enter the journal’s name: ");
        String magazineQuartile = m.askForString("Enter the journal’s quartile: ");
        int acceptProbability =  m.askForInteger("Enter the acceptance probability: ");
        int revisionProbability = m.askForInteger("Enter the revision probability: ");
        int denyProbability = m.askForInteger("Enter the rejection probability: ");

        Article article = new Article(name, journalName, magazineQuartile, acceptProbability, revisionProbability, denyProbability);
        trials.add(article);
    }

    private void createMasterTrial() {
        String name = m.askForString("Enter the trial’s name: ");
        String masterName = m.askForString("Enter the master’s name: ");
        int ects = m.askForInteger("Enter the master’s ECTS number: ");
        int creditPass = m.askForInteger("Enter the credit pass probability: ");

        Master master = new Master(name, masterName, ects, creditPass);
        trials.add(master);
    }

    private void createThesisTrial() {
        String name = m.askForString("Enter the trial’s name: ");
        String thesisName = m.askForString("Enter the master’s name: ");
        int difficulty = m.askForInteger("Enter the defense difficulty: ");

        Thesis thesis = new Thesis(name, thesisName, difficulty);
        trials.add(thesis);
    }

    private void createBudgetTrial() {
        String name = m.askForString("Enter the trial’s name: ");
        String budgetName = m.askForString("Enter the entity’s name: ");
        int amount = m.askForInteger("Enter the budget amount: ");

        Budget budget = new Budget(name, budgetName, amount);
        trials.add(budget);
    }

    public void listTrials() {
        int option, i = 0;

        if (trials.size() != 0) {
            for (i = 0; i < trials.size(); i++) {
                m.showMessage(i+1 + ") "+ trials.get(i).name);
            }
            m.spacing();
            m.showMessage(i+1 + ") Back");
        } else m.showMessage("No trials created yet...");

        option = m.askForInteger("Enter an option: ");
        option--;

        //TODO: no se si la logica esta bien hecha, revisar porque hace cosas raras...
        while (option < 1 && option != i+1) {
            if (option < i+1) {
                trials.get(option).printInformation();
                break;
            } else {
                m.showMessage("There is no trial with that number... ");
                option = m.askForInteger("Enter an option: ");
            }
        }
    }
    //TODO: Implementar metodo y posibles cambios en el controler, puesto para compilar.
    public void deleteTrial() {
    }
}

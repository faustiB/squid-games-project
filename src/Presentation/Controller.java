package Presentation;

import Business.TrialManager;
import Presentation.Menu;

/**
 * Class created in order to control the flow of the program and work with the different
 * parts of the project with GRASP.
 */
public class Controller {

    private static final int MANAGE_TRIALS = 1;
    private static final int MANAGE_EDITIONS = 2;
    private static final int EXIT_COMPOSER_MENU = 3;

    private static final String CREATE_TRIAL = "a";
    private static final String LIST_TRIALS = "b";
    private static final String DELETE_TRIAL = "c";
    private static final String EXIT_TRIAL_MENU = "d";


    private final Menu menu;

    /**
     * Constructor to generate a Presentation.Controller
     *
     * @param menu: object of the menu class.
     */
    public Controller(Menu menu) {
        this.menu = menu;
    }

    /**
     * Method used to control of the program correctly.
     */
    public void run() {

        int option;
        boolean exit = false;

        //true for I, false for II
        if (menu.checkPersistanceInput()) {
            menu.showMessage("Loading data from CSV files...");
        } else {
            menu.showMessage("Loading data from JSON files...");
        }

        //true for A, false for B
        menu.showBanner();
        if (menu.checkRoleInput()) {
            menu.showMessage("\nEntering management mode...\n");

            do {
                menu.showComposerMenu();
                option = menu.getOptionComposerMenu();


                switch (option) {

                    case MANAGE_TRIALS -> manageTrials();
                    //TODO: Cambiar los exits por las opciones que tocan.
                    case MANAGE_EDITIONS -> exit = true;
                    case EXIT_COMPOSER_MENU -> exit = true;
                }

            } while (!exit);

        } else {
            menu.showMessage("\nEntering execution mode...\n");
        }

        exitMenu();

    }

    /**
     * Method to manage the trials.
     */

    private void manageTrials() {
        boolean exit = false;
        TrialManager tm = new TrialManager();
        do {
            menu.showTrialMenu();
            String optionTrial = menu.getTrialOption();


            switch (optionTrial) {
                case CREATE_TRIAL -> {
                    menu.showTrialTypes();
                    int trialType = menu.getTrialType();

                    tm.createTrial(trialType);
                }

                case LIST_TRIALS -> tm.listTrials();
                case DELETE_TRIAL -> tm.deleteTrial();
                case EXIT_TRIAL_MENU -> exit = true;
            }
        } while (!exit);
    }

    /**
     * Option that closes the threads, shows the exit message exits the program.
     */
    private void exitMenu() {
        menu.spacing();
        menu.showMessage("Shutting down...");
        menu.closeScanner();
    }
}
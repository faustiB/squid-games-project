package Presentation;

import Business.EditionManager;
import Business.TrialManager;
import Persistance.JsonController;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    private static final String CREATE_EDITION = "a";
    private static final String LIST_EDITIONS = "b";
    private static final String DUPLICATE_EDITIONS = "c";
    private static final String DELETE_EDITIONS = "d";
    private static final String EXIT_EDITION_MENU = "e";

    private final Menu menu;
    private final JsonController jc = new JsonController();
    private TrialManager tm ;
    private EditionManager em;

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
            try {
                tm = new TrialManager(jc.readTrials());
            } catch (FileNotFoundException e) {
                tm = new TrialManager();
            }
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
                    case MANAGE_EDITIONS -> manageEditions();
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
        menu.showMessage("\nGoing Back to previous menu...\n");
    }

    /**
     *
     */
    private void manageEditions() {
        boolean exit = false;

        do {
            menu.showEditionMenu();
            String optionTrial = menu.getEditionOption();

            switch (optionTrial) {
                case CREATE_EDITION -> em.createEdition(tm);
                case LIST_EDITIONS -> em.listEditions(tm);
                /*case DUPLICATE_EDITIONS -> em.duplicateEditions();
                case DELETE_EDITIONS -> em.deleteEditions();*/
                case EXIT_EDITION_MENU -> exit = true;
            }
        } while (!exit);
        menu.showMessage("\nGoing Back to previous menu...\n");
    }

    private void exportFiles() {
        try {
            jc.writeTrialsToFiles(tm.getTrials());
        } catch (IOException e) {
            menu.showMessage("Could not export the trials to Json...");
        }
    }

    /**
     * Option that closes the threads, shows the exit message exits the program.
     */
    private void exitMenu(){
        menu.spacing();
        menu.showMessage("Shutting down...");
        menu.closeScanner();

        exportFiles();
    }
}
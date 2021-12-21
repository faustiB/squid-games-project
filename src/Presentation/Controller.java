package Presentation;

import Presentation.Menu;

/**
 * Class created in order to control the flow of the program and work with the different
 * parts of the project with GRASP.
 */
public class Controller {

    private static final int SHOW_PROGRESS = 1;
    private static final int EXIT_MENU = 2;

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
     *
     */
    public void run() {

        int option;

        //true for I, false for II
        if (menu.checkPersistanceInput()) {
            menu.showMessage("Loading data from CSV files...");
        } else {
            menu.showMessage("Loading data from JSON files...");
        }

        //true for A, false for B
        if (menu.checkRoleInput()) {
            menu.showMessage("Entering management mode...");
        } else {
            menu.showMessage("Entering execution mode...");
        }

        menu.showBanner();
        do {
            option = menu.askForInteger("Enter an option: ");

            runOption(option);
        } while (option != EXIT_MENU);
    }

    /**
     * Method used to invoke the functions desired by the user.
     *
     * @param option: option chosen by the user.
     */
    private void runOption(int option){
        switch (option) {
            case SHOW_PROGRESS -> menu.showMessage("");
            case EXIT_MENU -> exitMenu();
            default -> menu.showMessage("Wrong option. Enter a number from 1 to 2, both included");
        }
    }

    /**
     * Option that closes the threads, shows the exit message exits the program.
     */
    private void exitMenu() {
        menu.spacing();
        menu.showMessage("");
    }
}
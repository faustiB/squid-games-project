package Presentation;

import Business.*;

import java.util.*;

/**
 * Class created in order to implement methods that are useful for the program
 * such as showing the menu and user input control.
 */
public class Menu {

    private final Scanner scanner;

    /**
     * Constructor of the menu class implementing a scanner for user input.
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * This functions prints the menu with the appropriate format.
     */
    public void showTrialMenu() {
        spacing();
        System.out.println("\ta) Create Trial");
        System.out.println("\tb) List Trials");
        System.out.println("\tc) Delete Trial");
        spacing();
        System.out.println("\td) Back");
    }

    /**
     * This functions prints the trial types .
     */
    public void showTrialTypes() {
        spacing();
        System.out.println("\t\t--- Trial Types ---");
        spacing();
        System.out.println("\t\t1) Paper Publication");
        System.out.println("\t\t2) Master studies");
        System.out.println("\t\t3) Doctoral thesis defense");
        System.out.println("\t\t4) Budget request");
        spacing();
    }

    /**
     * This functions prints the menu with the appropriate format.
     */
    public void showEditionMenu() {
        spacing();
        System.out.println("\ta) Create Edition");
        System.out.println("\tb) List Editions");
        System.out.println("\tc) Duplicate Edition");
        System.out.println("\td) Delete Edition");
        spacing();
        System.out.println("\te) Back");
    }

    /**
     * This functions prints the menu with the appropriate format.
     */
    public void showComposerMenu() {
        spacing();
        System.out.println("1) Manage Trials");
        System.out.println("2) Manage Editions ");
        spacing();
        System.out.println("3) Exit");
    }

    /**
     * This functions prints The Trials banner.
     */
    public void showBanner() {
        System.out.println("""
                 _____ _            _____     _       _    \s
                |_   _| |__   ___  |_   _| __(_) __ _| |___\s
                  | | | '_ \\ / _ \\   | || '__| |/ _` | / __|
                  | | | | | |  __/   | || |  | | (_| | \\__ \\
                  |_| |_| |_|\\___|   |_||_|  |_|\\__,_|_|___/
                                                           \s""".indent(1));
    }

    /**
     * Method used to print the persistence message.
     */
    public void showPersistenceChoice() {
        System.out.println("""
                The IEEE needs to know where your allegiance lies.
                                
                \tI) People’s Front of Engineering (CSV)
                \tII) Engineering People’s Front (JSON)
                """);
    }

    /**
     * Method used to check if the persistence entered is correct.
     * @return true for I, false for II
     */
    public boolean checkPersistenceInput() {
        String choice;

        showPersistenceChoice();
        choice = askForString("Pick a faction: ");

        while (!choice.equals("I") && !choice.equals("II")) {
            showMessage("Wrong input, enter one of the desired options.");
            showPersistenceChoice();
            choice = askForString("Pick a faction: ");
        }

        return choice.equalsIgnoreCase("I");
    }

    /**
     * Method used to print the role choice message.
     */
    public void showRoleChoice() {
        System.out.println("""
                Welcome to The Trials. Who are you?
                                
                \tA) The Composer
                \tB) This year’s Conductor
                """);
    }

    /**
     * Method used to check the role input
     * @return true for A, false for B.
     */
    public boolean checkRoleInput() {
        String choice;

        showRoleChoice();
        choice = askForString("Enter a role: ");

        while (!choice.equalsIgnoreCase("A") && !choice.equalsIgnoreCase("B")) {
            showRoleChoice();
            choice = askForString("Enter a role: ");
        }

        return choice.equalsIgnoreCase("A");
    }

    /**
     * This function applies format to the message printed.
     *
     * @param message: desired string to print.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method to processes a String entered by the user.
     *
     * @param message: string written by the user
     * @return String processed by the system.
     */
    public String askForString(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please enter the one of the desired options. ");
            }
        }
    }

    /**
     * Method used to get the correct quartile input.
     * @param message: Message input to print
     * @return correct quartile with format
     */
    public String askForQuartile(String message) {
        String quartile;
        while (true) {
            try {
                System.out.print(message);
                quartile = scanner.nextLine();
                if (checkQuartile(quartile)) {
                    return quartile;
                } else {
                    showMessage("\nPlease introduce a valid quartile (Q1, Q2, Q3, Q4)\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please enter the one of the desired options. ");
            }
        }
    }

    /**
     * Method created to check if a quartile format is correct.
     * @param quartile: string entered by the user
     * @return true for correct quartile.
     */
    public boolean checkQuartile(String quartile) {
        return quartile.equals("Q1") ||
                quartile.equals("Q2") ||
                quartile.equals("Q3") ||
                quartile.equals("Q4");
    }

    /**
     * Method that processes and checks that an integer has been entered by the user.
     *
     * @param message: user input.
     * @return processed integer.
     */
    public int askForInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid integer, try again!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    /**
     * Ask for integer between a delimiter
     *
     * @param message message to be shown
     * @param num1    low delimiter
     * @param num2    high delimiter
     * @return number entered.
     */
    public int askForIntegerBetweenDelimiters(String message, int num1, int num2) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = scanner.nextInt();
                if (checkBetweenNumbersType(number, num1, num2)) {
                    return number;
                }
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid integer, try again!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    /**
     * Closing of Scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Method that shows a message with a tabulated output.
     *
     * @param message: Desired String for format.
     */
    public void showTabulatedMessage(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Method used to apply desired format to the prints.
     */
    public void spacing() {
        System.out.println();
    }

    /**
     * Getter of the option selected by screen
     *
     * @return integer introduced by the screen
     */
    public int getOptionComposerMenu() {
        boolean check = false;
        int opt = 0;
        do {
            System.out.print("\nEnter an option: ");
            try {
                opt = scanner.nextInt();
                if (checkBetweenNumbersType(opt, 1, 3)) {
                    check = true;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("\nEnter a number please.\n");
            } finally {
                scanner.nextLine();
            }
        } while (!check);

        return opt;
    }

    /**
     * Getter of the option selected by screen
     *
     * @return integer introduced by the screen
     */
    public String getTrialOption() {
        boolean check = false;
        String opt = "";
        do {
            System.out.print("\nEnter an option: ");
            try {
                opt = scanner.next();
                if (checkOptTrialMenu(opt)) {
                    check = true;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("\nEnter a String please.\n");
            } finally {
                scanner.nextLine();
            }
        } while (!check);

        return opt;
    }

    /**
     * Method to check if option in trial menu is valid.
     *
     * @param opt option input
     * @return boolean that determines if input is valid.
     */
    public boolean checkOptTrialMenu(String opt) {
        if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("b") || opt.equalsIgnoreCase("c") || opt.equalsIgnoreCase("d")) {
            return true;
        } else {
            System.out.println("\nWrong option entered, only valid: 'a', 'b' , 'c' or 'd'.\n");
            return false;
        }
    }

    /**
     * Getter of the option selected by screen
     * @return integer introduced by the screen
     */
    public String getEditionOption() {
        boolean check = false;
        String opt = "";
        do {
            System.out.print("\nEnter an option: ");
            try {
                opt = scanner.next();
                if (checkOptEditionMenu(opt)) {
                    check = true;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("\nEnter a String please.\n");
            } finally {
                scanner.nextLine();
            }
        } while (!check);

        return opt;
    }

    /**
     * Method to check if option in trial menu is valid.
     *
     * @param opt option input
     * @return boolean that determines if input is valid.
     */
    public boolean checkOptEditionMenu(String opt) {
        if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("b") || opt.equalsIgnoreCase("c") || opt.equalsIgnoreCase("d") || opt.equalsIgnoreCase("e")) {
            return true;
        } else {
            System.out.println("\nWrong option entered, only valid: 'a', 'b' , 'c', 'd' or 'e'.\n");
            return false;
        }
    }

    /**
     * Method to get the trial type number
     * @return trial type.
     */
    public int getTrialType() {
        boolean check = false;
        int trialType;
        do {
            trialType = askForInteger("Enter the trial's type: ");
            if (checkBetweenNumbersType(trialType, 1, 4)) {
                check = true;
            }
        } while (!check);

        return trialType;
    }

    /**
     * Method to check if an option is between two numbers
     *
     * @param option: value to be checked
     * @param num1: first delimiter
     * @param num2: second delimiter
     * @return boolean that determines if option is between the delimiters.
     */
    public boolean checkBetweenNumbersType(int option, int num1, int num2) {
        if (option == num1 || option == num2 || (option > num1 && option < num2)) {
            return true;
        } else if (num1 == num2) {
            System.out.println("\nIf you want to run it, You can only enter the number " + num1 + ".\n");
            return false;
        } else {
            System.out.println("\nEnter a number between " + num1 + " and " + num2 + " included.\n");
            return false;
        }
    }

    /**
     * Showing of details of an article
     * @param article: Article input.
     */
    public void showDetailsArticle(Article article) {

        HashMap<String, String> map = article.getDetails();
        spacing();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!entry.getKey().equals("% acceptance") && !entry.getKey().equals("revision") && !entry.getKey().equals("% rejection")) {
                showMessage(entry.getKey() + " : " + entry.getValue());
            }
        }

        showMessage("Chances: " + map.get("% acceptance") + "% acceptance. "
                + map.get("revision") + "% revision, " + map.get("% rejection") + "% rejection");
    }

    /**
     * Showing details of thesis
     * @param thesis Thesis input
     */
    public void showDetailsThesis(Thesis thesis) {

        HashMap<String, String> map = thesis.getDetails();
        spacing();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            showMessage(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Showing details of Master
     * @param master Master input
     */
    public void showDetailsMaster(Master master) {

        HashMap<String, String> map = master.getDetails();
        spacing();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!entry.getKey().equals("ECTS") && !entry.getKey().equals("% chance")) {
                showMessage(entry.getKey() + " : " + entry.getValue());
            }
        }

        showMessage("ECTS: " + map.get("ECTS") + " with a  " + map.get("% chance") + "% chance to pass each one \n");

    }

    /**
     * Showing of budget details.
     * @param budget Budget Input.
     */
    public void showDetailsBudget(Budget budget) {
        HashMap<String, String> map = budget.getDetails();
        spacing();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("Budget")) {
                showMessage(entry.getKey() + " : " + entry.getValue() + " €");
            } else {
                showMessage(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    /**
     * Method used to show the trials with correct format.
     * @param trials: arraylist of trials
     */
    public void showTrials(ArrayList<Trial> trials) {
        for (int i = 0; i < trials.size(); i++) {
            showMessage(i + 1 + ") " + trials.get(i).getName());
        }
    }

    /**
     * Method used to show the editions with correct format.
     * @param editions: arraylist of editions.
     */
    public void showEditions(ArrayList<Edition> editions) {
        for (int i = 0; i < editions.size(); i++) {
            showTabulatedMessage(i + 1 + ") The trials " + (editions.get(i).getYear()));
        }
    }

    /**
     * Method used to ask for the different trials.
     * @param numberOfTrials: total number of trials.
     * @param tm: trial manager object.
     * @return arraylist of trials.
     */
    public ArrayList<Trial> askForTrials(int numberOfTrials, TrialManager tm) {
        ArrayList<Trial> trials = new ArrayList<>();
        int input;
        for (int i = 1; i <= numberOfTrials; i++) {
            spacing();
            input = askForIntegerBetweenDelimiters("Pick a trial (" + i + "/" + numberOfTrials + ") : ",
                    1, tm.getTrialsSize());
            trials.add(tm.getSpecificTrial(input - 1));
        }
        spacing();

        return trials;
    }

    /**
     * Method used to show a concrete edition with correct format.
     * @param edition: an edition
     */
    public void showEdition(Edition edition) {
        spacing();
        showMessage("Year: " + edition.getYear());
        showMessage("Players: " + edition.getNumberOfPlayers());
        showMessage("Trials: ");
        showTrialsOfEdition(edition.getNamesOfTrialsForDescription());
    }

    /**
     * Method used to show the different trials of an edition
     * @param names: names of the trials.
     */
    private void showTrialsOfEdition(HashMap<Integer,String> names) {
        String[] arr;
        for (Map.Entry<Integer, String> entry: names.entrySet()) {
            arr = entry.getValue().split("-");
            showTabulatedMessage(entry.getKey() + "- " +arr[1]+ " ("+arr[0]+")");
        }
    }

    /**
     * Asks for to continue message and returns the correctly entered string
     * @return string with the correct input.
     */
    public String askForContinue() {
        String opt;
        boolean userInputCorrect;

        opt = askForString("Continue the execution? [yes/no]: ");
        userInputCorrect = opt.equalsIgnoreCase("yes") || opt.equalsIgnoreCase("no");

        while (!userInputCorrect) {
            System.out.println("\nWrong option entered, only valid: 'yes' or 'no'\n");
            opt = askForString("Continue the execution? [yes/no]: ");
            userInputCorrect = opt.equalsIgnoreCase("yes") || opt.equalsIgnoreCase("no");
        }

        return opt;
    }

    /**
     * This method prints the end message for the conductor execution
     * @param alive: true for some players alive, false for no.
     * @param currentYear: year of the trials.
     */
    public void showEndMessage(boolean alive, int currentYear) {
        if (alive) {
            showMessage("--- THE TRIALS "+currentYear+" HAVE ENDED - PLAYERS WON ---");
        } else {
            showMessage("--- THE TRIALS "+currentYear+" HAVE ENDED - PLAYERS LOST ---");
        }
    }
}
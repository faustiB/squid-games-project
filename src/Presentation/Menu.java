package Presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class created in order to implement methods that are useful for the program
 * such as showing the menu and user input control.
 */
public class Menu {

    private final Scanner scanner;

    /**
     * Constructor of the menu class implementing an scanner for user input.
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * This functions prints the menu with the appropiate format.
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
     * This functions prints the menu with the appropiate format.
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

    public void showPersistanceChoice() {
        System.out.println("""
                The IEEE needs to know where your allegiance lies.
                                
                \tI) People’s Front of Engineering (CSV)
                \tII) Engineering People’s Front (JSON)
                """);
    }

    public boolean checkPersistanceInput() {
        String choice;

        showPersistanceChoice();
        choice = askForString("Pick a faction: ");

        while (!choice.equals("I") && !choice.equals("II")) {
            showMessage("Wrong input, enter one of the desired options.");
            showPersistanceChoice();
            choice = askForString("Pick a faction: ");
        }

        return choice.equalsIgnoreCase("I");
    }

    public void showRoleChoice() {
        System.out.println("""
                Welcome to The Trials. Who are you?
                                
                \tA) The Composer
                \tB) This year’s Conductor
                """);
    }

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
     * Method to processes an String entered by the user.
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
                if (checkBetweenNumbersType(opt,1,3)) {
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
     * Checking that the option selected is an available one.
     *
     * @param opt option
     * @return true if it is okay , false if it is not okay
     */
    public boolean checkOptComposerMenu(int opt) {
        if (opt > 0 && opt < 4) {
            return true;
        } else {
            System.out.println("\nEnter a number between 1 and 3 included.\n");
            return false;
        }
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
        if (opt.equals("a") || opt.equals("b") || opt.equals("c") || opt.equals("d")) {
            return true;
        } else {
            System.out.println("\nWorng option entered, only valid: 'a', 'b' , 'c' or 'd'.\n");
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
            if (checkBetweenNumbersType(trialType, 1,4)) {
                check = true;
            }
        } while (!check);

        return trialType;

    }

    /**
     * Method to check if an option is between two numbers
     * @param option value to be checked
     * @param num1 first delimiter
     * @param num2 second delimiter
     * @return boolean that determines if option is between the delimiters.
     */
    public boolean checkBetweenNumbersType(int option, int num1, int num2){
        if (option == num1 || option == num2 || (option > num1 && option < num2)) {
            return true;
        } else {
            System.out.println("\nEnter a number between "+ num1 +" and "+ num2 + " included.\n");
            return false;
        }
    }


}
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
    public void showMenu() {
        System.out.println();
        /*System.out.println("--- The Factory ---");
        System.out.println();
        System.out.println("1. Show progress");
        System.out.println("2. Exit ");*/
        System.out.println();
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

        while (!choice.equalsIgnoreCase("I") && !choice.equalsIgnoreCase("II")) {
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

        return choice.equals("A");
    }

    /**
     * This function applies format to the message printed.
     * @param message: desired string to print.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method to processes an String entered by the user.
     * @param message: string written by the user
     * @return String processed by the system.
     */
    public String askForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Method that processes and checks that an integer has been entered by the user.
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
     * Method that shows a message with a tabulated output.
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
}
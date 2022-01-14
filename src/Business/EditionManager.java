package Business;

import Persistance.Csv.CsvReader;
import Persistance.Csv.CsvWriter;
import Persistance.Json.JsonReader;
import Persistance.Json.JsonWriter;
import Presentation.Menu;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EditionManager {

    private ArrayList<Edition> editions;
    private Menu menu = new Menu();

    public EditionManager(Boolean choice) {
        if (choice) {
            try {
                this.editions = readEditions_CSV();
            } catch (FileNotFoundException e) {
                this.editions = new ArrayList<>();
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.editions = readEditions_JSON();
            } catch (FileNotFoundException e) {
                this.editions = new ArrayList<>();
            }
        }
    }

    public void writeEditionsToFiles(Boolean choice) throws IOException {
        if (choice) {
            writeEditions_CSV(this.editions);
        } else {
            writeEditions_JSON(this.editions);
        }
    }

    /**
     * Creation of editions
     */
    public void createEdition(TrialManager tm) {
        boolean check = false;
        if (tm.checkLengthOfTrialsIsNotZero()) {
            menu.spacing();
            int yearOfEdition = menu.askForInteger("Enter the edition's year: ");

            if (editions.size() > 0) {
                do {
                    if (checkIfYearIsInEditions(yearOfEdition)) {
                        yearOfEdition = menu.askForInteger("Edition already created, please a new edition's year: ");
                    } else {
                        check = true;
                    }
                } while (!check);
            }

            int numberOfPlayers = menu.askForIntegerBetweenDelimiters("Enter the Initial number of players: ", 1, 5);
            int numberOfTrials = menu.askForIntegerBetweenDelimiters("Enter the number of trials: ", 3, 12);

            menu.showMessage("\n\t--- Trials ---\n");
            tm.showingOfTrials();

            ArrayList<Trial> trials = menu.askForTrials(numberOfTrials, tm);

            Edition edition = new Edition(yearOfEdition, numberOfPlayers, numberOfTrials, trials);
            editions.add(edition);

            menu.showMessage("\nThe edition was created successfully\n");
        } else {
            menu.showMessage("\nNo trials yet created, please first create trials.\n");
        }

    }

    /**
     * Cheking if year introduced , is already created.
     *
     * @param input year introduced
     * @return boolean that states if year is already created.
     */
    public boolean checkIfYearIsInEditions(int input) {

        for (Edition e : editions) {
            if (e.checkYear(input)) {
                return true;
            }
        }
        return false;
    }

    public void listEditions() {
        int option;
        if (editions.size() == 0) {
            menu.showMessage("\nNo editions created yet...\n");
        } else {
            option = getEditionSelectedInput();
            if (option != editions.size()) {
                if (menu.checkBetweenNumbersType(option + 1, 1, getEditionsSize())) {
                    menu.showEdition(editions.get(option));
                }
            } else {
                menu.showMessage("\nGoing back to previous menu...\n");
            }
        }
    }


    public void duplicateEditions() {
        int option;
        boolean check = false;
        if (editions.size() == 0) {
            menu.showMessage("\nNo editions created yet...\n");
        } else {
            menu.showMessage("\nWhich edition do you want to clone ?\n");
            option = getEditionSelectedInput();
            if (option != editions.size()) {
                if (menu.checkBetweenNumbersType(option + 1, 1, getEditionsSize())) {
                    menu.spacing();
                    int newYear = menu.askForInteger("Enter the new edition's year: ");
                    do {
                        if (checkIfYearIsInEditions(newYear)) {
                            newYear = menu.askForInteger("Edition already created, please an other new edition's year: ");
                        } else {
                            check = true;
                        }
                    } while (!check);
                    int newNumberOfPlayers = menu.askForIntegerBetweenDelimiters("Enter the  new initial number of players: ", 1, 5);

                    Edition newEdition = new Edition(newYear, newNumberOfPlayers, editions.get(option).getSizeOfTrials());
                    newEdition.duplicateTrials(editions.get(option));

                    editions.add(newEdition);
                    menu.showMessage("\nThe edition was successfully cloned.\n");
                }
            }
        }
    }

    public void deleteEdition() {
        int option;
        if (editions.size() == 0) {
            menu.showMessage("\nNo editions created yet...\n");
        } else {
            menu.showMessage("\nWhich edition do you want to delete ?\n");
            option = getEditionSelectedInput();
            if (option != editions.size()) {
                if (menu.checkBetweenNumbersType(option + 1, 1, getEditionsSize())) {
                    menu.spacing();
                    int editionYear = menu.askForInteger("\nEnter the edition's year for confirmation : ");
                    if (editions.get(option).checkYear(editionYear)) {
                        editions.remove(option);
                        menu.showMessage("\nThe edition was successfully deleted.\n");
                    } else {
                        menu.showMessage("\nConfirmation failed, edition was not deleted. \n");
                    }
                }
            }
        }
    }


    public int getEditionSelectedInput() {
        int option = -1;
        boolean check = false;

        if (checkLengthOfEditionsIsNotZero()) {
            showingOfEditions();
            menu.spacing();
            menu.showTabulatedMessage(editions.size() + 1 + ") Back");

            do {
                option = menu.askForInteger("Enter an option: ");
                if (menu.checkBetweenNumbersType(option, 1, getEditionsSize() + 1)) {
                    check = true;
                }
            } while (!check);

            option--;
        }

        return option;
    }

    public ArrayList<Player> addPlayers( int numOfPlayers) {
        String playerName;
        ArrayList<Player> players = new ArrayList<>();
        menu.spacing();
        for (int i = 0; i < numOfPlayers; i++) {
            playerName = menu.askForString("Enter the player's name (" + (i + 1) + "/" + numOfPlayers + "): ");
            Player p = new Player(playerName);
            players.add(p);
        }
        return players;

    }

    public Edition getEditionByYear(int year) {
        for (Edition e : editions) {
            if (e.checkYear(year)) return e;
        }
        return null;
    }

    public void startTrials(int currentYear, TrialManager tm) {
        Edition e = getEditionByYear(currentYear);

        ArrayList<Player> players = addPlayers(e.getNumberOfPlayers());

        e.executeTrials(players, tm);




    }


    private int getEditionsSize() {
        return editions.size();
    }

    public boolean checkLengthOfEditionsIsNotZero() {
        return getEditionsSize() != 0;
    }

    public void showingOfEditions() {
        menu.showEditions(editions);
    }

    public ArrayList<Edition> readEditions_CSV() throws IOException, CsvException {
        return new CsvReader().readEditions();
    }

    public void writeEditions_CSV(ArrayList<Edition> editions) throws IOException {
        new CsvWriter().writeFullEditionsFiles(editions);
    }

    public ArrayList<Edition> readEditions_JSON() throws FileNotFoundException {
        return new JsonReader().readFilesEditions();
    }

    public void writeEditions_JSON(ArrayList<Edition> editions) throws IOException {
        new JsonWriter().writeEditions(editions);
    }

    public boolean checkEditionIsCreated(int currentYear) {
        for (Edition e : editions) {
            if (e.checkYear(currentYear)) return true;
        }
        return false;
    }
}

package Business;

import Persistance.Csv.CsvReader;
import Persistance.Csv.CsvWriter;
import Persistance.FileDeleter;
import Persistance.Json.JsonReader;
import Persistance.Json.JsonWriter;
import Presentation.Menu;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EditionManager {

    private ArrayList<Edition> editions;
    private final Menu menu = new Menu();

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

    public void startTrials(int currentYear, TrialManager tm, boolean formatChoice)
            throws InterruptedException, IOException {
        Edition edition = getEditionByYear(currentYear);
        ArrayList<Player> players = null;
        int startPosition = 0;

        if (formatChoice){
            menu.showMessage("Loading data from CSV files...");
        } else {
            try {
                players = readStatusGame_JSON();
                startPosition = getLastPositionOfTrials(players, edition);
            } catch (FileNotFoundException e) {
                players = addPlayers(edition.getNumberOfPlayers());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        executeTrials(players, tm, edition, startPosition, formatChoice);
    }

    private int getLastPositionOfTrials(ArrayList<Player> players, Edition edition) {
        String nameOfTrial = players.get(0).getNameOfTrial();
        ArrayList<Trial> trials = edition.getTrials();

        for (int i = 0; i < trials.size(); i++) {
            if(trials.get(i).getName().equalsIgnoreCase(nameOfTrial)) return i+1;
        }
        return 0;
    }

    /**
     * Execution of trials
     *
     * @param players players that are going to play
     * @param tm trial manager of trials.
     */
    public void executeTrials(ArrayList<Player> players, TrialManager tm, Edition e, int startPosition, boolean formatChoice)
            throws InterruptedException, IOException {

        ArrayList<String> names = getNamesOfTrials(e);
        e.setTrials(tm.getSpecificTrialsByNames(names));

        for (int i = startPosition, trialsSize = e.getSizeOfTrials(); i < trialsSize; i++) {
            Trial trial = e.getSpecificTrialByID(i);

            menu.spacing();
            menu.showMessage("Trial #" + (i + 1) + " - " + trial.getName());

            if (trial.type == 4) { //if we have a budget trial
                menu.showMessage(trial.executeBudget(players));
            } else {
                for (Player p : players) {
                    if (p.isDisqualified()) {

                        p.setTrial(trial);

                        Thread thread = new Thread(p);
                        thread.start();
                        //TODO: Cambiar esto por que no funcions a
                        thread.join();

                        //m.showMessage(trial.executeTrial(p));
                        if (p.getPi() >= 10 || p.getPi() <= 0) { //if player can evolve or has no PI left
                            menu.showMessage(p.checkStatus());
                        }
                    }
                }
            }
            if (!checkNotDisqualifiedPlayers(players)) break; //all player have been eliminated
            if (i != trialsSize - 1) { //we are at the end of the trials
                if (!checkContinue()){
                    if(formatChoice) {
                        menu.showMessage("This is CSV");
                    } else {
                        writeStatusGame_JSON(players);
                    }

                    menu.spacing();
                    menu.showMessage("Saving ...");
                    break;
                }
            } else {
                menu.showEndMessage(checkNotDisqualifiedPlayers(players), e.getYear());
                deleteStatusFile(formatChoice);
            }
        }
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

    public boolean checkEditionIsCreated(int currentYear) {
        for (Edition e : editions) {
            if (e.checkYear(currentYear)) return true;
        }
        return false;
    }

    /**
     * Adding names of trials
     * @return names of trials
     */
    public ArrayList<String> getNamesOfTrials(Edition edition) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Trial> trials = edition.getTrials();

        for (Trial t : trials) {
            names.add(t.getName());
        }

        return names;
    }

    /**
     * Method used to see if the conductor wants to continue execution
     *
     * @return true for continue, false for no.
     */
    private boolean checkContinue() {
        String keep_playing = menu.askForContinue();
        return keep_playing.equalsIgnoreCase("yes");
    }

    /**
     * This method returns if there are disqualified players.
     *
     * @param players: arraylist of players
     * @return true for alive players, false for not
     */
    private boolean checkNotDisqualifiedPlayers(ArrayList<Player> players) {
        int players_not_disqualified = 0;

        for (Player p : players) {
            if (p.isDisqualified()) { //is not disqualified
                players_not_disqualified++;
            }
        }

        return players_not_disqualified > 0;
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

    public void writeStatusGame_JSON( ArrayList<Player> players) throws IOException{
        new JsonWriter().writeStatusGame(players);
    }

    public ArrayList<Player> readStatusGame_JSON() throws IOException{
        return new JsonReader().readStatusGame();
    }

    private void deleteStatusFile(boolean formatChoice) {
        boolean deletion;

        if(formatChoice) {
            deletion =  new FileDeleter().deleteCSVFile();
        } else {
            deletion =  new FileDeleter().deleteJsonFile();
        }

        if(!deletion) {
            menu.spacing();
            menu.showMessage("Could not delete the Status File...");
            menu.spacing();
        }
    }
}

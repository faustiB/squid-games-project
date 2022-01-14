package Business;

import Presentation.Menu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Method to manage each edition.
 */
public class Edition {
    private final int year;
    private final int numberOfPlayers;
    private final int numberOfTrials;
    private ArrayList<Trial> trials;

    /**
     * Constructor of the edition class.
     * @param year: year of the edition.
     * @param numberOfPlayers: number of players.
     * @param numberOfTrials: number of trials.
     * @param trials: arraylist of trials.
     */
    public Edition(int year, int numberOfPlayers, int numberOfTrials, ArrayList<Trial> trials) {
        this.year = year;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfTrials = numberOfTrials;
        this.trials = trials;
    }

    /**
     * Constructor of the editions without the trials arraylist.
     * @param year: year of the edition.
     * @param numberOfPlayers: number of players.
     * @param numberOfTrials: number of trials.
     */
    public Edition(int year, int numberOfPlayers, int numberOfTrials){
        this.year = year;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfTrials = numberOfTrials;
    }

    /**
     * Method to check the year
     * @param input: year
     * @return true for same year, false for different year.
     */
    public boolean checkYear(int input) {
        return year == input;
    }

    /**
     * Getter of the year.
     * @return year of the edition.
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter of the number of players.
     * @return number of players.
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Getter for the size of the trials.
     * @return size of the trials arraylist.
     */
    public int getSizeOfTrials() {
        return trials.size();
    }

    /**
     * This method generates a hashmap with the name of the trials.
     * @return hashmap with the name of the trials
     */
    public HashMap<Integer,String> getNamesOfTrialsForDescription(){
        HashMap<Integer,String> map = new HashMap<>();
        String className;
        String[] arr;
        int i = 1;

        for (Trial t :trials) {
            arr = t.getClass().toString().split("\\.");
            className = t.getTextualDescription(arr[1]);

            map.put(i,className+"-"+t.getName());
            ++i;
        }

        return map;
    }

    /**
     * This method duplicates the trials of an edition.
     * @param edition: edition choosen.
     */
    public void duplicateTrials(Edition edition) {
        this.trials = edition.trials;
    }

    /**
     * This method returns the description of an edition.
     * @return description of an edition.
     */
    public String[] getArrayDescription() {
        return new String[]{String.valueOf(year),String.valueOf(numberOfPlayers),String.valueOf(numberOfTrials)};
    }

    /**
     * Adding names of trials
     * @return names of trials
     */
    public ArrayList<String> getNamesOfTrials(){
        ArrayList<String> names = new ArrayList<>();

        for (Trial t: trials) {
            names.add(t.getName());
        }

        return names;
    }

    /**
     * Execution of trials
     * @param players players that are going to play
     * @param tm trial manager of trials.
     */
    public void executeTrials(ArrayList<Player> players, TrialManager tm) {
        Menu m = new Menu();

        ArrayList<String> names = getNamesOfTrials();
        trials = tm.getSpecificTrialsByNames(names);

        for (int i = 0, trialsSize = trials.size(); i < trialsSize; i++) {
            Trial trial = trials.get(i);
            m.spacing();
            m.showMessage("Trial #" + (i+1) + " - "+ trial.getName());
            for (Player p: players) {
                trial.executeTrial(p);
                p.checkEvolution();
            }
        }
    }
}

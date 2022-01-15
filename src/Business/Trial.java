package Business;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Master class of the trials. The different trials get inheritance from this class.
 */
public class Trial {
    protected String name;
    protected int type;

    /**
     * Constructor of the trial class.
     *
     * @param name: name of the trial.
     * @param type: type of trial.
     */
    public Trial(String name, int type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Method used to get the details of the trial.
     *
     * @return hashmap of strings.
     */
    public HashMap<String, String> getDetails() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", this.name);
        return map;
    }

    /**
     * Method created to check if the name is correct.
     *
     * @param input: input string with name.
     * @return true for correct, false for incorrect.
     */
    public boolean checkName(String input) {
        return this.name.equals(input);
    }

    /**
     * Getter of name.
     *
     * @return trial name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of type
     *
     * @return type of trial.
     */
    public int getType() {
        return type;
    }

    /**
     * Getter of the textual description.
     *
     * @param type: type of trial.
     * @return textual description.
     */
    public String getTextualDescription(String type) {
        String ret = "";
        switch (type) {
            case "Article" -> ret = "Paper Publication";
            case "Thesis" -> ret = "Doctoral thesis defense";
            case "Master" -> ret = "Master studies";
            case "Budget" -> ret = "Budget request";
        }

        return ret;
    }

    /**
     * Method to return the name of the trial on an array.
     *
     * @return string array.
     */
    public String[] getArrayDescription() {
        return new String[]{name};
    }

    /**
     * Overwritten method for the trials execution.
     *
     * @param p: player
     */
    public String executeTrial(Player p) {
        return null;
    }

    /**
     * Overwritten method for the budget execution.
     *
     * @param p: player
     */
    public String executeBudget(ArrayList<Player> p) {
        return "";
    }


}


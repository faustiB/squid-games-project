package Business;

import java.util.HashMap;

import static java.lang.Math.sqrt;

/**
 * Class created to manage the Thesis objects.
 */
public class Thesis extends Trial {
    private final String studyField;
    private final int difficulty;

    /**
     * Constructor for the thesis.
     * @param name: name
     * @param type: type of trial.
     * @param studyField: field of study.
     * @param difficulty: difficulty of the thesis.
     */
    public Thesis(String name, int type, String studyField, int difficulty) {
        super(name, type);
        this.studyField = studyField;
        this.difficulty = difficulty;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String,String> map = new HashMap<>();
        map.put("Trial",super.name);
        map.put("Field",this.studyField);
        map.put("Difficulty",String.valueOf(this.difficulty));

        return map;
    }

    @Override
    public String[] getArrayDescription(){
        return new String[]{name,studyField,String.valueOf(difficulty)};
    }

    /**
     * Method created to execute the thesis trial.
     */
    @Override
    public void executeTrial(Player p) {
        int result = 0;

        for (int i = 1; i <= this.difficulty; i++) {
            result = result + (2*i - 1);
        }

        //return pi > sqrt(result);
    }

    //TODO: revisar master evoluciona a doctor (no guanya punts)
    /**
     * Method created to return the points won or lost by the player.
     * @return true for win, false for loose.
     */
    public int getPoints(boolean result) {
        if (result) return 5;
        else return -5;
    }
}

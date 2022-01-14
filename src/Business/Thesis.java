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
    public String executeTrial(Player p) {
        int result = 0;
        String message = "\t";

        for (int i = 1; i <= this.difficulty; i++) {
            result = result + (2*i - 1);
        }

        boolean passed = p.getPi() > sqrt(result);
        p.setPi(getPoints(passed, p));

        if (passed) {
            message = message.concat(p.getNameAndTitle()+ " was successful. Congrats! PI count: "+ p.getPi());
        } else {
            message = message.concat(p.getNameAndTitle()+ " was not successful. Sorry... PI count: "+ p.getPi());
        }

        return message;
    }

    /**
     * Method created to return the points won or lost by the player.
     * @return true for win, false for loose.
     */
    private int getPoints(boolean result, Player p) {
        if (p.getTitle() == 1 && result){
            p.evolutePlayer();
        } else if(result){
            return 5;
        } else {
            return -5;
        }

        return 0;
    }
}

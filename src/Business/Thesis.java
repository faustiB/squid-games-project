package Business;

import java.util.HashMap;

import static java.lang.Math.sqrt;

public class Thesis extends Trial {
    private String studyField;
    private int difficulty;

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

    public boolean executeThesis(int pi) {
        int result = 0;

        for (int i = 1; i <= this.difficulty; i++) {
            result = result + (2*i - 1);
        }

        return pi > sqrt(result);
    }

    //TODO: revisar master evoluciona a doctor (no guanya punts)
    public int getPoints(boolean result) {
        if (result) return 5;
        else return -5;
    }
}

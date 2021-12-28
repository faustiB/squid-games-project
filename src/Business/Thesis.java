package Business;

import java.util.HashMap;

public class Thesis extends Trial {
    private String studyField;
    private int difficulty;

    public Thesis(String name, int type, String studyField, int dificultat) {
        super(name, type);
        this.studyField = studyField;
        this.difficulty = dificultat;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String,String> map = new HashMap<>();
        map.put("Trial",super.name);
        map.put("Field",this.studyField);
        map.put("Difficulty",String.valueOf(this.difficulty));

        return map;
    }
}

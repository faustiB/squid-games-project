package Business;

import java.util.HashMap;

public class Thesis extends Trial {
    private String studyField;
    private int difficulty;

    public Thesis(String name, String studyField, int dificultat) {
        super(name);
        this.studyField = studyField;
        this.difficulty = dificultat;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String,String> map = new HashMap<>();
        map.put("Trial",super.name);
        map.put("Field",this.studyField);
        map.put("Difficulty",String.valueOf(this.difficulty));
        /*
        System.out.println("Trial: "+ super.name);
        System.out.println("Field: "+ this.studyField);
        System.out.println("Difficulty: " + this.difficulty);*/

        return map;
    }
}

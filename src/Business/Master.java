package Business;

import java.util.HashMap;

public class Master extends Trial {
    private String masterName;
    private int numCredits;
    private int chanceToPass;

    public Master(String name, String masterName, int numCredits, int chanceToPass) {
        super(name);
        this.masterName = masterName;
        this.numCredits = numCredits;
        this.chanceToPass = chanceToPass;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String,String> map = new HashMap<>();

        map.put("Trial",super.name);
        map.put("Master",this.masterName);
        map.put("ECTS",String.valueOf(this.numCredits));
        map.put("% chance to pass each one",String.valueOf(this.chanceToPass));
        /*
        System.out.println("Trial: "+ super.name);
        System.out.println("Master: "+ this.masterName);
        System.out.println("ECTS: " + this.numCredits + ", with a " + this.chanceToPass + "% chance to pass each one");
        */
        return map;
    }
}

package Business;

import java.util.HashMap;

public class Master extends Trial {
    private String masterName;
    private int numCredits;
    private int chanceToPass;

    public Master(String name, int type, String masterName, int numCredits, int chanceToPass) {
        super(name, type);
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
        map.put("% chance",String.valueOf(this.chanceToPass));

        return map;
    }

    @Override
    public String[] getArrayDescription(){
        return new String[]{name,masterName,String.valueOf(numCredits),String.valueOf(chanceToPass)};
    }
}

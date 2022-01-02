package Business;

import java.util.HashMap;

public class Trial {

    protected String name;
    protected int type;

    public Trial(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public HashMap<String, String> getDetails() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", this.name);
        return map;
    }

    public boolean checkName(String input) {
        return this.name.equals(input);
    }


    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getTextualDescription(String s) {
        String ret = "";
        switch (s) {
            case "Article" -> ret = "Paper Publication";
            case "Thesis" -> ret = "Doctoral thesis defense";
            case "Master" -> ret = "Master studies";
            case "Budget" -> ret = "Budget request";
        }

        return ret;
    }

    public String[] getArrayDescription(){
        return new String[]{name};
    }
}

package Business;

import java.util.HashMap;

public class Trial {

    protected String name;

    public Trial(String name) {
        this.name = name;
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
}

package Business;

import java.util.ArrayList;
import java.util.HashMap;

public class Edition {

    private int year;
    private int numberOfPlayers;
    private int numberOfTrials;
    private ArrayList<Trial> trials;


    public Edition(int year, int numberOfPlayers, int numberOfTrials, ArrayList<Trial> trials) {
        this.year = year;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfTrials = numberOfTrials;
        this.trials = trials;
    }

    public boolean checkYear(int input) {
        return year == input;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public HashMap<Integer,String> getNamesOfTrials(){
        HashMap<Integer,String> map = new HashMap<>();
        String className;
        String[] arr;
        int i = 1;

        for (Trial t :trials) {

            arr = t.getClass().toString().split("\\.");
            className = t.getTextualDescription(arr[1]);

            map.put(i,className+"-"+t.getName());
            ++i;
        }

        return map;
    }

}

package Business;

import java.util.ArrayList;

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

    public boolean checkYear(int input){
        return year == input;
    }

    public int getYear() {
        return year;
    }
}

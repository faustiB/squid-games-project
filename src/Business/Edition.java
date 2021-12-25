package Business;

import java.util.ArrayList;

public class Edition {

    private int year;
    private int numberOfPlayers;
    private int numberOfTrials;
    private ArrayList<Trial> trials;




    public boolean checkYear(int input){
        return year == input;
    }

}

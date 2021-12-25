package Business;

import Presentation.Menu;

import java.util.ArrayList;

public class EditionManager {

    Menu menu = new Menu();
    private ArrayList<Edition> editions;

    /**
     * Creation of editions
     */
    public void createEdition(TrialManager tm) {

        boolean check = false;

        int yearOfEdition = menu.askForInteger("Enter the edition's year: ");
        do {
            if ( checkIfYearIsInEditions(yearOfEdition) ){
                yearOfEdition = menu.askForInteger("Edition already created, please a new edition's year: ");
            } else {
                check = true;
            }

        } while (!check);

        int numberOfPlayers = menu.askForIntegerBetweenDelimeters("Enter the Initial number of players: ", 1, 5);
        int numberOfTrials = menu.askForIntegerBetweenDelimeters("Enter the number of trials", 3, 12);

        menu.showMessage("\n\t--- Trials ---\n");
        tm.showingOfTrials();
        //TODO: se muestran los trials, falta crear un metodocomo por ejemplo el de despues, para ir pidiendo uno a uno
        // no se si tiene que estar hecho en menu o mas adelante.
        //ArrayList<Trial> trials = menu.askForTrials(numberOfTrials,tm);


    }


    public boolean checkIfYearIsInEditions(int input) {

        for (Edition e : editions) {
            if (e.checkYear(input)){
                return true;
            }
        }
        return false;
    }

}

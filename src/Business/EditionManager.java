package Business;

import Presentation.Menu;

import java.util.ArrayList;

public class EditionManager {

    Menu menu = new Menu();
    private ArrayList<Edition> editions = new ArrayList<Edition>();

    /**
     * Creation of editions
     */
    public void createEdition(TrialManager tm) {

        boolean check = false;
        if (tm.checkLengthOfTrialsIsNotZero()) {
            menu.spacing();
            int yearOfEdition = menu.askForInteger("Enter the edition's year: ");

            if (editions.size() > 0) {
                do {
                    if (checkIfYearIsInEditions(yearOfEdition)) {
                        yearOfEdition = menu.askForInteger("Edition already created, please a new edition's year: ");
                    } else {
                        check = true;
                    }
                } while (!check);
            }
            int numberOfPlayers = menu.askForIntegerBetweenDelimeters("Enter the Initial number of players: ", 1, 5);
            int numberOfTrials = menu.askForIntegerBetweenDelimeters("Enter the number of trials: ", 3, 12);

            menu.showMessage("\n\t--- Trials ---\n");
            tm.showingOfTrials();

            ArrayList<Trial> trials = menu.askForTrials(numberOfTrials, tm);

            Edition edition = new Edition(yearOfEdition, numberOfPlayers, numberOfTrials, trials);
            editions.add(edition);

            menu.showMessage("\nThe edition was created successfully\n");
        } else {
            menu.showMessage("\nNo trials yet created, please first create trials.\n");
        }

    }

    /**
     * Cheking if year introduced , is already created.
     * @param input year introduced
     * @return boolean that states if year is already created.
     */
    public boolean checkIfYearIsInEditions(int input) {

        for (Edition e : editions) {
            if (e.checkYear(input)) {
                return true;
            }
        }
        return false;
    }

    public void listEditions(TrialManager tm) {
        int option;
        if (editions.size() == 0){
            menu.showMessage("\nNo editions created yet...\n");
        } else {
            option = getEditionSelectedInput();
            if (option != editions.size()){
                if (menu.checkBetweenNumbersType(option + 1, 1, getEditionsSize() )){
                    //if (option )
                    System.out.println("\nKELOKE VAMO A MOTRAL \n");
                }
            } else {

            }
        }
    }

    public int getEditionSelectedInput() {
        int option = -1;
        boolean check = false;

        if (checkLengthOfEditionsIsNotZero()) {
            showingOfEditions();
            menu.spacing();
            menu.showMessage(editions.size() + 1 + ") Back");
            option = menu.askForInteger("Enter an option: ");

            do {
                option = menu.askForInteger("Enter an option: ");
                if (menu.checkBetweenNumbersType(option ,1,getEditionsSize()+1)){
                    check = true;
                }
            } while(!check);


            option--;
        }

        return option;
    }

    private int getEditionsSize() {
        return editions.size();
    }

    public boolean checkLengthOfEditionsIsNotZero() {
        return getEditionsSize() != 0;
    }

    public void showingOfEditions(){
        menu.showEditions(editions);
    }

}
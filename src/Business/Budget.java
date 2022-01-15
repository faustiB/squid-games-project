package Business;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class created to manage the Budget objects.
 */
public class Budget extends Trial {
    private final String entityName;
    private final int budgetQuantity;

    /**
     * Constructor for the budget.
     * @param name: name.
     * @param type: type of trial.
     * @param entityName: entity name:
     * @param budgetQuantity: budget quantity.
     */
    public Budget(String name, int type, String entityName, int budgetQuantity) {
        super(name, type);
        this.entityName = entityName;
        this.budgetQuantity = budgetQuantity;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String,String> map = new HashMap<>();

        map.put("Trial",super.name);
        map.put("Entity",this.entityName);
        map.put("Budget",String.valueOf(this.budgetQuantity));

        return map;
    }

    @Override
    public String[] getArrayDescription(){
        return new String[]{name,entityName,String.valueOf(budgetQuantity)};
    }

    /**
     * Method created to execute the budget trial.
     */
    @Override
    public String executeBudget(ArrayList<Player> players){
        int points = 0;
        String message = "\t";

        for (Player player : players) {
            if (player.isDisqualified()) {
                points = points + player.getPi();
            }
        }

        boolean result =  Math.log10(this.budgetQuantity)/Math.log10(2) < points;

        if(result) {
            message = message.concat("The research group got the budget!\n");
        } else {
            message = message.concat("The research group did not get the budget...\n");
        }

        for (Player player : players) {
            if (player.isDisqualified()) {
                player.setPi(getPoints(result, points));
                message = message.concat("\t" + player.getNameAndTitle()+ " PI count: "+ player.getPi()+"\n");
                player.checkStatus();
            }
        }

        return message;
    }

    /**
     * Method created to return the points won or lost by the player.
     * @return true for win, false for loose.
     */
    private int getPoints(boolean result, int points) {
        if (result) return (int) Math.ceil((double) points/2);
        else return -2;
    }
}

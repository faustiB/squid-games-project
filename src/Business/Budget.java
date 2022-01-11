package Business;

import java.util.HashMap;

public class Budget extends Trial {
    private String entityName;
    private int budgetQuantity;

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

    public boolean executeBudget(int points) {
        return Math.log10(this.budgetQuantity)/Math.log10(2) < points;
    }

    public int getPoints(boolean result, int points) {
        if (result) return (int) Math.ceil((double) points/2);
        else return -2;
    }
}

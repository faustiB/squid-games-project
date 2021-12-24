package Business;

import java.util.HashMap;

public class Budget extends Trial {
    private String entityName;
    private int budgetQuantity;

    public Budget(String name, String entityName, int budgetQuantity) {
        super(name);
        this.entityName = entityName;
        this.budgetQuantity = budgetQuantity;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String,String> map = new HashMap<>();

        map.put("Trial",super.name);
        map.put("Entity",this.entityName);
        map.put("Budget",String.valueOf(this.budgetQuantity));
        /*
        System.out.println("Trial: "+ super.name);
        System.out.println("Entity: "+ this.entityName);
        System.out.println("Budget: " + this.budgetQuantity);*/
        return map;
    }
}

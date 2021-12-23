package Business;

public class Budget extends Trial {
    private String entityName;
    private int budgetQuantity;

    public Budget(String name, String entityName, int budgetQuantity) {
        super(name);
        this.entityName = entityName;
        this.budgetQuantity = budgetQuantity;
    }
}

package Business;

public class Master extends Trial {
    private String masterName;
    private int numCredits;
    private int chanceToPass;

    public Master(String name, String masterName, int numCredits, int chanceToPass) {
        super(name);
        this.masterName = masterName;
        this.numCredits = numCredits;
        this.chanceToPass = chanceToPass;
    }
}

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

    @Override
    public void printInformation() {
        System.out.println("Trial: "+ super.name);
        System.out.println("Master: "+ this.masterName);
        System.out.println("ECTS: " + this.numCredits + ", with a " + this.chanceToPass + "% chance to pass each one");
    }
}

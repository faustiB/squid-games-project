package Business;

public class Thesis extends Trial {
    private String campEstudis;
    private int dificultat;

    public Thesis(String name, String campEstudis, int dificultat) {
        super(name);
        this.campEstudis = campEstudis;
        this.dificultat = dificultat;
    }
}

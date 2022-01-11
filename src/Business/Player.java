package Business;

public class Player {
    private String name;
    private int pi;
    private int title; //0 for engineer, 1 for Master, 2 for PhD

    public Player(String name) {
        this.name = name;
        this.pi = 5;
        this.title = 0;
    }

    public int getPi() {
        return pi;
    }

    public String getNameAndTitle() {
        return switch (this.title) {
            case 1 -> "Master " + this.name;
            case 2 -> this.name + ", PhD";
            default -> this.name;
        };
    }
}

package Business;

/**
 * Class that manages each player.
 */
public class Player {
    private final String name;
    private final int pi;
    private final int title; //0 for engineer, 1 for Master, 2 for PhD

    /**
     * Constructor of the player class.
     * @param name: name of the player
     */
    public Player(String name) {
        this.name = name;
        this.pi = 5;
        this.title = 0;
    }

    /**
     * Getter of the points of investigation.
     * @return pi points.
     */
    public int getPi() {
        return pi;
    }

    /**
     * Useful method to return the name and the title of the player.
     * @return name and title of the player.
     */
    public String getNameAndTitle() {
        return switch (this.title) {
            case 1 -> "Master " + this.name;
            case 2 -> this.name + ", PhD";
            default -> this.name;
        };
    }
}

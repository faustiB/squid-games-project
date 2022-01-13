package Business;

/**
 * Class that manages each player.
 */
public class Player {
    private final String name;
    private int pi;
    private int title; //0 for engineer, 1 for Master, 2 for PhD

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
     * Getter of the name
     * @return name of the player.
     */
    public String getName() {
        return name;
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

    /**
     * Setter for the points of each player.
     * @param points: points won or lost
     */
    public void setPi(int points) {
        this.pi = this.pi + points;
    }

    /**
     * Method that reviews if a player can evolute.
     */
    public void checkEvolution() {
        if(this.pi >= 10 && this.title == 0) { //engineer with 10 or more points
            this.pi = 5;
            this.title = 1;
            System.out.println(getName()+ " is now a master (with 5 PI). ");
        } else if (this.pi >= 10 && this.title == 1){ //master with 10 or more points
            this.pi = 5;
            this.title = 2;
            System.out.println(getName()+ " is now a doctor (with 5 PI). ");
        }
    }
}

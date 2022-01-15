package Business;

import Presentation.Menu;

/**
 * Class that manages each player.
 */
public class Player implements Runnable {
    private final String name;
    private int pi;
    private int title; //0 for engineer, 1 for Master, 2 for PhD
    private boolean disqualified;
    private Trial trial;

    /**
     * Constructor of the player class. Default
     * @param name: name of the player
     */
    public Player(String name) {
        this.name = name;
        this.pi = 5;
        this.title = 0;
        this.disqualified = false;
    }

    /**
     * Full constructor with all atributes.
     * @param name name
     * @param pi points
     * @param title title
     * @param disqualified isDisqualified?
     * @param trial trial to be executed
     */
    public Player(String name, int pi, int title, boolean disqualified, Trial trial) {
        this.name = name;
        this.pi = pi;
        this.title = title;
        this.disqualified = disqualified;
        this.trial = trial;
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
     * Getter of the disqualified parameter.
     * @return true for diqualified.
     */
    public boolean isDisqualified() {
        return !disqualified;
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
     * @return message string
     */
    public String checkStatus() {
        String message = "\t";

        if(this.pi >= 10 && this.title == 0) { //engineer with 10 or more points
            this.pi = 5;
            this.title = 1;
            message = message.concat(this.name+ " is now a master (with 5 PI). ");
        } else if (this.pi >= 10 && this.title == 1){ //master with 10 or more points
            this.pi = 5;
            this.title = 2;
            message = message.concat(this.name+ " is now a doctor (with 5 PI). ");
        } else if (this.pi <= 0) {
            message = message.concat(this.name+ " is Disqualified. ");
            this.disqualified = true;
        }
        return message;
    }

    /**
     * Evolute title of player by summing the points.
     */
    public void evolutePlayer() {
        this.pi = 10;
    }

    /**
     * Getter of title
     * @return title
     */
    public int getTitle() {
        return this.title;
    }

    /**
     * Getter of the name of the trial
     * @return name of the trial
     */
    public String getNameOfTrial() {
        return this.trial.name;
    }

    /**
     * Setter of trial
     * @param trial: trial passed by parameter
     */
    public void setTrial(Trial trial) {
        this.trial = trial;
    }

    /**
     * This method returns the description of a player.
     *
     * @return description of a player.
     */
    public String[] getArrayDescription() {
        return new String[]{name,String.valueOf(pi), String.valueOf(title), String.valueOf(disqualified), trial.getName()};
    }

    /**
     * Run in thread the execution of the assigned trial
     */
    @Override
    public void run() {
        Menu m  = new Menu();
        String message = trial.executeTrial(this);
        m.showMessage(message);
    }
}

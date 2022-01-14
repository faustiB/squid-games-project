package Business;

import java.util.HashMap;
import java.util.Random;

/**
 * Class created to manage the Article objects.
 */
public class Article extends Trial {
    private final String articleName;
    private final String magazineQuartile;
    private final int acceptProbability;
    private final int revisionProbability;
    private final int denyProbability;

    /**
     * Constructor for the article.
     * @param name: name
     * @param type: type of trial
     * @param articleName: article name
     * @param magazineQuartile: quartile of the magazine
     * @param acceptProbability: probability of acceptance.
     * @param revisionProbability: probability of revision.
     * @param denyProbability: probability of denial.
     */
    public Article(String name, int type, String articleName, String magazineQuartile, int acceptProbability,
                   int revisionProbability, int denyProbability) {
        super(name, type);
        this.articleName = articleName;
        this.magazineQuartile = magazineQuartile;
        this.acceptProbability = acceptProbability;
        this.revisionProbability = revisionProbability;
        this.denyProbability = denyProbability;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Trial", super.name);
        map.put("Journal", this.articleName);
        map.put("% acceptance", String.valueOf(this.acceptProbability));
        map.put("revision", String.valueOf(this.revisionProbability));
        map.put("% rejection", String.valueOf(this.denyProbability));

        return map;
    }

    @Override
    public String[] getArrayDescription(){
        return new String[]{name,articleName,magazineQuartile,String.valueOf(acceptProbability),String.valueOf(revisionProbability),String.valueOf(denyProbability)};
    }

    /**
     * Method created to execute the article trial.
     */
    @Override
    public String executeTrial(Player p){
        String message = "\t";
        Random rand = new Random();
        boolean cont = true;

        message = message.concat(p.getNameAndTitle()+ " is submitting... ");
        while (cont) {
            int prob = rand.nextInt(100);

            if (prob <= this.acceptProbability) {
                p.setPi(getPoints(true));
                message = message.concat("Accepted! PI count: "+ p.getPi());
                cont = false;
            } else if (prob <= this.acceptProbability+this.revisionProbability) {
                message = message.concat("Revisions... ");
            } else {
                p.setPi(getPoints(false));
                message = message.concat("Rejected. PI count: "+ p.getPi());
                cont = false;
            }
        }

        return message;
    }

    /**
     * Method created to return the points won or lost by the player
     * according to the quartile.
     * @return true for win, false for loose.
     */
    private int getPoints(boolean result) {
        int points = 0;

        if (result) {
            switch (this.magazineQuartile) {
                case "Q1" -> points = 4;
                case "Q2" -> points = 3;
                case "Q3" -> points = 2;
                case "Q4" -> points = 1;
            }
        } else {
            switch (this.magazineQuartile) {
                case "Q1" -> points = -5;
                case "Q2" -> points = -4;
                case "Q3" -> points = -3;
                case "Q4" -> points = -2;
            }
        }

        return points;
    }
}

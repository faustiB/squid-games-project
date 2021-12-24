package Business;

import java.util.HashMap;

public class Article extends Trial {
    private String articleName;
    private String magazineQuartile;
    private int acceptProbability;
    private int revisionProbability;
    private int denyProbability;

    public Article(String name, String articleName, String magazineQuartile, int acceptProbability, int revisionProbability, int denyProbability) {
        super(name);
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
        map.put("% acceptance", String.valueOf(this.revisionProbability));
        map.put("revision", String.valueOf(this.revisionProbability));
        map.put("% rejection", String.valueOf(this.denyProbability));
        /*
        System.out.println("Trial: "+ super.name);
        System.out.println("Journal: "+ this.articleName);
        System.out.println("Chances: " + this.acceptProbability + "% acceptance. " + this.revisionProbability + "% " +
                "revision, " + this.denyProbability + "% rejection");
*/
        return map;
    }
}

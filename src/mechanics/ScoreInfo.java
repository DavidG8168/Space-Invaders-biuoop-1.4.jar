package mechanics;

/**
 * Score info keeps the score information.
 * @author David Goichman.
 */
public class ScoreInfo {
    // The members.
    private String playername;
    private int points;

    /**
     * The constructor.
     * @param name
     *          The name of the player.
     * @param score
     *          The score of the player.
     */
    public ScoreInfo(String name, int score) {
        this.playername = name;
        this.points = score;
    }

    /**
     * Returns the name of the player.
     * @return
     *          The name of the player.
     */
    public String getName() {
        return this.playername;
    }

    /**
     * Returns the score of the player.
     * @return
     *          The score of the player.
     */
    public int getScore() {
        return this.points;
    }
}
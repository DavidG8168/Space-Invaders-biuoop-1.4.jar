package animation;

import java.awt.Color;
import biuoop.DrawSurface;
import mechanics.HighScoresTable;
import mechanics.ScoreInfo;
import java.util.ArrayList;

/**
 * This class animates the scoreboard.
 * @author David Goichman.
 */
public class HighScoresAnimation implements Animation {
    // The members.
    private HighScoresTable table;
    private boolean stop = false;

    /**
     * The constructor.
     * @param scores
     *            The score table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.table = scores;

    }

    /**
     * Animates the score screen.
     * @param d
     *            The drawsurface.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // Background image.
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        // Drawing the title of the score board.
        d.setColor(Color.WHITE);
        d.drawText(250, 50, "TOP SCORES", 40);
        // The player name headline.
        d.setColor(Color.WHITE);
        d.drawText(80, 80, "Name:", 35);
        // The name of the scoreboard.
        d.setColor(Color.WHITE);
        d.drawText(400, 80, "Score:", 35);
        ArrayList<ScoreInfo> scores = new ArrayList<>(table.getHighScores());
        for (int i = 1; i <= scores.size(); i++) {
            // Drawing the names.
            d.setColor(Color.YELLOW);
            d.drawText(90, 80 + i * 30, scores.get(i - 1).getName(), 25);
            // Drawing the scores/
            d.setColor(Color.RED);
            d.drawText(420, 80 + i * 30, scores.get(i - 1).getScore() + "", 25);
        }
        // Drawing the closing message.
        d.setColor(Color.YELLOW);
        d.drawText(500, 320, "Press 'm' to close !", 25);
    }

    /**
     * Method controls the stopping condition.
     * @return stopping condition.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
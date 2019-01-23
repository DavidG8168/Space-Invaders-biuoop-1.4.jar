package play;

import biuoop.DrawSurface;
import mechanics.Sprite;

/**
 * 'ScoreIndicator' is used to display the score.
 * @author David
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;

    /**
     * The constructor.
     * @param score
     *            The current score.
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
    }

    /**
     * Draws the score on the screen.
     * @param d
     *            The drawsurface.
     */
    public void drawOn(DrawSurface d) {
        String scoreString = "Score: ";
        scoreString = scoreString + Integer.toString(this.currentScore.getValue());
        d.setColor(java.awt.Color.WHITE);
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(java.awt.Color.BLACK);
        d.drawText(200, 20, scoreString, 20);
    }

    /**
     * Unused method.
     * @param dt
     *          The amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
    }

    /**
     * Unused method.
     * @param g
     *          The game level.
     */
    public void addToGame(GameLevel g) {
    }
}

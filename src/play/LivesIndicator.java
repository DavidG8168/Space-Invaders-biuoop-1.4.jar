package play;

import biuoop.DrawSurface;
import mechanics.Sprite;

/**
 * 'livesIndicator' is used to track player lives.
 * @author David Goichman.
 */
public class LivesIndicator implements Sprite {
    private Counter life;

    /**
     * The constructor.
     * @param lives
     *            The amount of lives the player has.
     */
    public LivesIndicator(Counter lives) {
        this.life = lives;
    }

    /**
     * This method displays the amount of lives on the screen.
     * @param d
     *            The drawsurface.
     */
    public void drawOn(DrawSurface d) {
        String lifeString = "Lives: ";
        lifeString = lifeString + Integer.toString(this.life.getValue());
        d.setColor(java.awt.Color.BLACK);
        d.drawText(50, 20, lifeString, 20);
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
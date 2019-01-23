package play;

import java.awt.Color;
import animation.Animation;
import biuoop.DrawSurface;

/**
 * The 'Endscreen' method displays a victory or loss message.
 * @author David Goichman.
 */
public class EndScreen implements Animation {
    // The members of the class.
    private boolean stop;
    private Counter score;

    /**
     * Constructor.
     * @param points
     *            The points counter.
     */
    public EndScreen(Counter points) {
        this.score = points;
        this.stop = false;
    }

    /**
     * This animates the message screen.
     * @param d
     *            The drawsurface.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
            d.setColor(java.awt.Color.YELLOW);
            d.drawText(10, d.getHeight() / 2,
                    "GAME OVER, SCORE: " + this.score.getValue() + "" + " Press 'm' to see high scores !", 30);
        }

    /**
     * This method stops the message screen.
     * @return stopping condition.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
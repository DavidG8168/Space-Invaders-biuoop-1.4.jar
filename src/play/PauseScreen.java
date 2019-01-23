package play;

import java.awt.Color;
import animation.Animation;
import biuoop.DrawSurface;

/**
 * The 'PauseScreen' method is in charge of pausing the game.
 * @author David Goichman.
 */
public class PauseScreen implements Animation {
    // The members of the class.
    private boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * This animates the pause screen.
     * @param d
     *            The drawsurface.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.WHITE);
        d.drawText(10, d.getHeight() / 2 - 100 , "Paused, press the 'm' key to continue", 30);
    }

    /**
     * This method stops the pause screen.
     * @return stopping condition.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
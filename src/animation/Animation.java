package animation;

import biuoop.DrawSurface;

/**
 * The 'Animation' interface is used to animate the game.
 * @author David Goichman.
 */
public interface Animation {
    /**
     * This method animates a frame in the game.
     * @param d
     *            The draw surface.
     * @param dt
     *          The amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * This method stops the game.
     * @return Returns if the game should continue or not.
     */
    boolean shouldStop();
}
package mechanics;

import biuoop.DrawSurface;
import play.GameLevel;

/**
 * Sprite Interface.
 */
public interface Sprite {
    /**
     * Method draws the sprite on the surface.
     * @param d
     *            The drawsurface.
     */
    // draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Method used to animate the ball.
     * @param dt
     *          The amount of seconds passed since the last call.
     */
    // notify the sprite that time has passed
    void timePassed(double dt);

    /**
     * Method used to add objects to the game.
     * @param g
     *            The game object.
     */
    // adds to game
    void addToGame(GameLevel g);
}

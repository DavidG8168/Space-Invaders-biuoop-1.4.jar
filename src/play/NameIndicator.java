package play;

import biuoop.DrawSurface;
import mechanics.Sprite;

/**
 * 'NameIndicator' is used to display the level name.
 * @author David Goichman.
 */
public class NameIndicator implements Sprite {
    private String name;

    /**
     * The constructor.
     * @param lvlName
     *            The name of the level.
     */
    public NameIndicator(String lvlName) {
        this.name = lvlName;
    }

    /**
     * This method displays the name of the level on the screen.
     * @param d
     *            The drawsurface.
     */
    public void drawOn(DrawSurface d) {
        String title = "Level Name: " + this.name;
        d.setColor(java.awt.Color.BLACK);
        d.drawText(400, 20, title, 20);
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
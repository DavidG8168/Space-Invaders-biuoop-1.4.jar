package mechanics;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The 'Sprite Collection' object is used to collect the game sprites.
 * @author David Goichman.
 *
 */
public class SpriteCollection {
    // The member, a list of sprites.
    private List<Sprite> spriteList = new ArrayList<>();

    /**
     * Method adds the sprite to list.
     * @param s
     *            The added sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Method moves the sprites.
     * @param dt
     *          The amount of seconds passed since the last call.
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            Sprite runningSprite = this.spriteList.get(i);
            runningSprite.timePassed(dt);
        }
    }

    /**
     * Method draws the sprites.
     * @param d
     *            The drawsurface.
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            Sprite runningSprite = this.spriteList.get(i);
            runningSprite.drawOn(d);
        }
    }

    /**
     * Removes the sprite.
     * @param s
     *            The sprite that will be removed.
     */
    public void removeSprites(Sprite s) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            if (s == this.spriteList.get(i)) {
                this.spriteList.remove(i);
            }
        }
    }

    /**
     * Gets the size of the list.
     * @return The size of the sprite list.
     */
    public int getSize() {
        return this.spriteList.size();
    }
}
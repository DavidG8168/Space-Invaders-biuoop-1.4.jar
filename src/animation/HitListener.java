package animation;

import mechanics.Bullet;
import mechanics.Shields;
import mechanics.Alien;

/**
 * The given HitListener interface.
 * @author David Goichman.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the thing that's doing the hitting.
    /**
     * This method is called when the block is hit.
     * @param beingHit
     *            The block that is being hit.
     * @param hitter
     *            The ball that hits the block.
     */
    void hitEvent(Alien beingHit, Bullet hitter);

    /**
     * This method is called when the block is hit.
     * @param beingHit
     *            The block that is being hit.
     * @param hitter
     *            The ball that hits the block.
     */
    void hitEvent(Shields beingHit, Bullet hitter);
}
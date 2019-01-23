package play;

import animation.HitListener;
import mechanics.Bullet;
import mechanics.Shields;
import mechanics.Alien;

/**
 * The 'BallRemover' class removes balls from the game.
 */
public class BulletRemover implements HitListener {
    // The BallRemover members.
    private GameLevel game;

    // BlockRemover Constructor.
    /**
     * This is the constructor.
     * @param game
     *            The game.
     */
    public BulletRemover(GameLevel game) {
        this.game = game;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    /**
     * This method removes the bullets from the game.
     * @param beingHit
     *            This is the bottom of the screen.
     * @param hitter
     *            This is the bullets that touches the bottom.
     */
    public void hitEvent(Alien beingHit, Bullet hitter) {
        // Checking if touching the bottom of the screen block.
        if (hitter.getY() + hitter.getSize() >= beingHit.getCollisionRectangle().getUpperLeft().getY()) {
            hitter.removeFromGame(game);
        }
    }

    /**
     * This method removes the bullets from the game.
     * @param beingHit
     *            This is the bottom of the screen.
     * @param hitter
     *            This is the bullet that touches the shield.
     */
    public void hitEvent(Shields beingHit, Bullet hitter) {
        // Checking if touching the bottom of the screen block.
        if (hitter.getY() + hitter.getSize() >= beingHit.getCollisionRectangle().getUpperLeft().getY()) {
            hitter.removeFromGame(game);
        }
    }
}

package play;

import animation.HitListener;
import mechanics.Bullet;
import mechanics.Shields;
import mechanics.Alien;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
/**
 * Block remover is used to remove blocks from the game.
 */
public class AlienRemover implements HitListener {
    // The BlockRemover members.
    private GameLevel game;
    private Counter remainingBlocks;

    // BlockRemover Constructor.
    /**
     * This is the constructor.
     * @param game
     *            The game.
     * @param removedBlocks
     *            The removed blocks.
     */
    public AlienRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    /**
     * This method removes the block from the game.
     * @param beingHit
     *            This is the block that is being hit.
     * @param hitter
     *            This is the ball that hits the block.
     */
    public void hitEvent(Alien beingHit, Bullet hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }

    /**
     * This method removes the block from the game.
     * @param beingHit
     *            The shield that is being hit.
     * @param hitter
     *            The bullet that hits it.
     */
    public void hitEvent(Shields beingHit, Bullet hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
        }
    }
}

package play;

import animation.HitListener;
import mechanics.Bullet;
import mechanics.Shields;
import mechanics.Alien;

/**
 * 'ScoreTrackingListener' is used to track the total game score.
 */
public class ScoreTrackingListener implements HitListener {
    // The member, the current score.
    private Counter currentScore;

    /**
     * The constructor of the class.
     * @param scoreCounter
     *            The current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method adds the points to the total score.
     * @param beingHit
     *            The block that is being hit.
     * @param hitter
     *            The ball that hits the block.
     */
    public void hitEvent(Alien beingHit, Bullet hitter) {
       currentScore.increase(100);
    }

    /**
     * Unused method.
     * @param beingHit
     *          The shield that is being hit.
     * @param hitter
     *          The thing that does the hitting.
     */
    public void hitEvent(Shields beingHit, Bullet hitter) {
        // TODO Auto-generated method stub
    }
}

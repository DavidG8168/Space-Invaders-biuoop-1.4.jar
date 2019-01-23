package mechanics;

import java.util.ArrayList;
import java.util.List;
import animation.HitListener;
import animation.HitNotifier;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import play.GameLevel;

/**
 * The 'Shields' program is an object used to make the shield blocks on screen.
 * @author David Goichman
 */
public final class Shields implements Collidable, Sprite, HitNotifier, HitListener {
    // The members of the object.
    private Rectangle rectangle;
    private int hp;
    private List<HitListener> hitListeners;

    /**
     * This method is used to apply values to the Shield object.
     * @param rec
     *            This is the rectangle, the shape of the block.
     * @param hitPoint
     *            This is used to count the hits of each block.
     */
    // Shield Constructor.
    public Shields(Rectangle rec, int hitPoint) {
        this.rectangle = rec;
        this.hp = hitPoint;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * This methods returns the rectangle the Shield is built based on.
     * @return Returns the rectangle used in the block.
     */
    // Shield accessor.
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @param hitter
     *            The hitting ball.
     * @param collisionPoint
     *            This is the point in which the ball collides into the block.
     * @param currentVelocity
     *            This is the current Velocity of the ball.
     * @return Returns the new Velocity of the ball after the collision.
     */
    public Velocity hit(Bullet hitter, Point collisionPoint, Velocity currentVelocity) {
        // Hit points are reduced upon hit.
        this.hp--;
        // Notifies hit.
        this.notifyHit(hitter);
        this.removeHitListener(this);
        return null;
    }

    /**
     * Method draws blocks.
     * @param surface
     *            This is the surface the blocks will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.CYAN);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth() + 1, (int) this.rectangle.getHeight() + 1);
    }

    /**
     * This method adds the shield block to the Game object.
     * @param g
     *            The game object.
     */
    public void addToGame(GameLevel g) {
        // Adds the block to the Game object.
        g.addSprite(this);
        g.addCollidable(this);
    }

    // Method unused for now.
    // Following instructions.
    // Boop.
    /**
     * Unused method.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
    }

    /**
     * Removes the shield block from the game.
     * @param game
     *            The game block will be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies of block hit.
     * @param hitter
     *            The bullet that hits the blocks.
     */
    public void notifyHit(Bullet hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds hit to listener.
     * @param hl
     *            The HitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * Removes hit from listener.
     * @param hl
     *            The HitListener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    /**
     * Returns hit points.
     * @return hp.
     */
    public int getHitPoints() {
        return this.hp;
    }

    /**
     * Unused method.
     * @param beingHit
     *            The block that is being hit.
     * @param hitter
     *            The ball that hits the block.
     */
    public void hitEvent(Alien beingHit, Bullet hitter) {
        // TODO Auto-generated method stub
    }

    /**
     * Unused method.
     * @param beingHit
     *            The block that is being hit.
     * @param hitter
     *            The ball that hits the block.
     */
    public void hitEvent(Shields beingHit, Bullet hitter) {
        // TODO Auto-generated method stub
    }
}
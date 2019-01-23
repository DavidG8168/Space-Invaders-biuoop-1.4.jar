package mechanics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import animation.HitListener;
import animation.HitNotifier;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import play.GameLevel;

/**
 * The 'Alien' program is an object used to make the aliens on screen.
 * @author David Goichman
 */
public class Alien implements Collidable, Sprite, HitNotifier, HitListener {
    // The members of the object.
    private Rectangle rectangle;
    private int hp;
    private List<HitListener> hitListeners;
    private boolean enemy = false;
    private double speed;

    /**
     * This method is used to apply values to the alien object.
     * @param rec
     *            This is the rectangle, the shape of the block.
     * @param hitPoint
     *            This is used to count the hits of each block.
     * @param evil
     *            If the block is an enemy.
     * @param s
     *            The speed multiplier.
     */
    // Block Constructor.
    public Alien(Rectangle rec, int hitPoint, boolean evil, int s) {
        this.rectangle = rec;
        this.hp = hitPoint;
        this.hitListeners = new ArrayList<HitListener>();
        this.enemy = evil;
        this.speed = 0.3 * s;
    }

    /**
     * This methods returns the rectangle the block is built based on.
     * @return Returns the rectangle used in the block.
     */
    // Block accessor.
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This method notifies a hit on the alien.
     * @param hitter
     *            The hitting ball.
     * @param collisionPoint
     *            This is the point in which the ball collides into the block.
     * @param currentVelocity
     *            This is the current Velocity of the ball.
     * @return Returns null.
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
     * Method draws aliens.
     * @param surface
     *            This is the surface the aliens will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        if (enemy) {
            // Drawing the space invaders.
            Image img = null;
            String path = "resources/enemy.png";
            try {
                img = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            surface.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    img);
        } else {
            // This is used to draw the background.
            surface.setColor(java.awt.Color.BLACK);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        }
    }

    /**
     * This method adds the alien to the GameLevel object.
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
     * Method moves the invaders.
     */
    public void moveInvaders() {
        speed = speed * speedCheck();
        this.rectangle = new Rectangle(
                new Point(this.rectangle.getUpperLeft().getX() + speed, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Removes the alien from the game.
     * @param game
     *            The game block will be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies of an alien hit.
     * @param hitter
     *            The ball that hits the blocks.
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
     * Method checks if invaders should change direction.
     * @return speed multiplier.
     */
    public double speedCheck() {
        // If we leave the screen, change direction.
        if (this.rectangle.getUpperRight().getX() + speed > 800 || this.rectangle.getUpperLeft().getX() + speed < 0) {
            this.rectangle = new Rectangle(
                    new Point(this.rectangle.getUpperLeft().getX(),
                            this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight() * 2),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            return -1 * 1.5;
        }
        return 1;
    }

    /**
     * Unused method.
     * @param beingHit
     *            The shield.
     * @param hitter
     *            The bullet.
     */
    public void hitEvent(Shields beingHit, Bullet hitter) {
        // TODO Auto-generated method stub

    }

    /**
     * Checks if alien hits a shield.
     * @return true or false.
     */
    public boolean hitShields() {
        if (this.getCollisionRectangle().getBottomLeft().getY() >= 520) {
            return true;
        }
        return false;
    }
}
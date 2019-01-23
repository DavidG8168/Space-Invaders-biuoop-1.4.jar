package mechanics;

import geometry.Point;
import geometry.Rectangle;

/**
 * Collidable Interface.
 */
public interface Collidable {
    /**
     * This methods returns the rectangle the collidable is built based on.
     * @return Returns the rectangle used.
     */
    // Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint
     *            This is the point in which the ball collides into the collidable.
     * @param currentVelocity
     *            This is the current Velocity of the ball.
     * @param hitter
     *            The ball.
     * @return Returns the new Velocity of the ball after the collision.
     */
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    Velocity hit(Bullet hitter, Point collisionPoint, Velocity currentVelocity);
}

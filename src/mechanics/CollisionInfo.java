package mechanics;

import geometry.Point;

/**
 * The 'CollisionInfo' program contains all information about the collided
 * object.
 */
public class CollisionInfo {
    // CollisionInfo Members.
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Applies values to the members of the object.
     * @param collisionPoint
     *            The point of collision between the ball and the object.
     * @param collisionObject
     *            The object the ball collides into.
     */
    // Constructor.
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point of collision.
     * @return The collision point of the object and ball.
     */
    // Getters.
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the object the ball collides into.
     * @return The collision object.
     */
    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}

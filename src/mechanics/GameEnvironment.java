package mechanics;

import java.util.ArrayList;
import java.util.List;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * The 'GameEnvironment' program is an object used to create the game
 * environment.
 * @author David Goichman
 */
public class GameEnvironment {
    // The member, a list of collidables.
    private List<Collidable> collideList = new ArrayList<>();

    /**
     * @param c
     *            The collidable added to the list.
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collideList.add(c);
    }

    /**
     * This method returns the information of the closest collidable in the path of
     * the Bullet.
     * @param trajectory
     *            This is the trajectory line of the ball.
     * @return Returns the collision info of the closest object.
     */
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidingWithLine = new ArrayList<>();
        for (int i = 0; i < this.collideList.size(); i++) {
            // Adding collidables to the list.
            Collidable runningCollide = this.collideList.get(i);
            Rectangle runningRect = runningCollide.getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(runningRect) != null) {
                collidingWithLine.add(runningCollide);
            } else {
                continue;
            }
        }
        // These will hold the info of the closest object.
        Point closestPoint = null;
        Collidable closest = null;
        double min = -1;
        for (int i = 0; i < collidingWithLine.size(); i++) {
            Collidable runningCollide = collidingWithLine.get(i);
            Point hitPoint = trajectory.closestIntersectionToStartOfLine(runningCollide.getCollisionRectangle());
            double currentDistance = hitPoint.distance(trajectory.start());
            if (min == -1) {
                min = currentDistance;
                closest = runningCollide;
                closestPoint = hitPoint;
            } else {
                if (currentDistance < min) {
                    min = currentDistance;
                    closest = runningCollide;
                    closestPoint = hitPoint;
                }
            }
        }
        if (closestPoint == null && closest == null) {
            return null;
        }
        // Returns new collision info.
        CollisionInfo crash = new CollisionInfo(closestPoint, closest);
        return crash;
    }

    /**
     * Removes a collidable from the list.
     * @param c
     *            The collidable that will be removed.
     */
    public void removeCollidable(Collidable c) {
        for (int i = 0; i < this.collideList.size(); i++) {
            if (c == this.collideList.get(i)) {
                this.collideList.remove(i);
            }
        }
    }
}
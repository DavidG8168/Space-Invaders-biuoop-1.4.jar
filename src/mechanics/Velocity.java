package mechanics;

import geometry.Point;

// Velocity specifies the change in position on the `x` and the `y` axes.
/**
 * The 'Velocity' program is an object used to calculate the velocity of the
 * balls in the rest of the program.
 * @author David Goichman
 */
public class Velocity {
    private double dx;
    private double dy;
    private double framesPerSecond = 60;

    // Velocity Constructor
    /**
     * This method is used to apply values to the line fields.
     * @param dx
     *            This is the movement in the x axis.
     * @param dy
     *            This is the movement in the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method is used to calculate the velocity of the ball.
     * @param angle
     *            This is the angle of the ball movement.
     * @param speed
     *            This is the speed of the ball at that angle.
     * @return returns the velocity as calculated.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Mathematical expression found on the internet.
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        Velocity vl = new Velocity(dx, dy);
        return vl;
    }

    // Velocity Accessors
    /**
     * This method is used to get the dx value of the velocity.
     * @return returns the dx value.
     */
    public double getdx() {
        return this.dx;
    }

    /**
     * This method is used to get the dy value of the velocity.
     * @return returns the dy value.
     */
    public double getdy() {
        return this.dy;
    }

    /**
     * This method is used to apply the change of location to the point.
     * @param p
     *            This is the point that will be changed.
     * @return returns the new point after movement.
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Method scales the velocity.
     * @param frames
     *            The framerate.
     * @return Returns scaled velocity.
     */
    public Velocity scale(double frames) {
        return new Velocity((this.dx * frames) * framesPerSecond, (this.dy * frames) * framesPerSecond);
    }
}
package geometry;
/**
 * The 'Point' program is an object used to make the points in the rest of the
 * program.
 */

public class Point {
    //Point members.
    private double x;
    private double y;

    // Point Constructor
    /**
     * This method is used to apply values to the line fields.
     * @param x
     *            This is the x value of the point.
     * @param y
     *            This is the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method is used to calculate the distance between 2 points.
     * @param other
     *            This is other point we will the the distance between.
     * @return returns the distance between the points.
     */
    // Distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double x1 = this.x;
        double y1 = this.y;
        double x2 = other.x;
        double y2 = other.y;
        double sum = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        return Math.sqrt(sum);
    }

    /**
     * This method is used to check if 2 points are equal.
     * @param other
     *            This is the point we will compare the current one to.
     * @return returns the answer, if they are equal or not.
     */
    // Equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        boolean xEquals = (this.x == other.x);
        boolean yEquals = (this.y == other.y);
        boolean answer = (xEquals && yEquals);
        return answer;
    }

    // Point Accessors
    /**
     * This method is used to get the x value of the point.
     * @return returns the x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method is used to get the y value of the point.
     * @return returns the y value.
     */
    public double getY() {
        return this.y;
    }
}

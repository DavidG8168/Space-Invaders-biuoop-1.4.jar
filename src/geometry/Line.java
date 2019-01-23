package geometry;
import java.util.List;

/**
 * The 'Line' program is an object used to make the lines in
 * 'AbstractArtDrawing'.
 */
public class Line {
    private Point start;
    private Point end;

    // Constructors.
    /**
     * This method is used to apply values to the line fields.
     * @param start
     *            This is the start point of the line.
     * @param end
     *            This is the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This method is used to apply values to the line fields.
     * @param x1
     *            This is the start x point of the line.
     * @param y1
     *            This is the start y point of the line.
     * @param x2
     *            This is the end x point of the line.
     * @param y2
     *            This is the end y point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startP = new Point(x1, y1);
        Point endP = new Point(x2, y2);
        this.start = startP;
        this.end = endP;
    }

    // Returns the length of the line.
    /**
     * This method is used to get the length of the line.
     * @return returns the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    // Returns the middle point of the line.
    /**
     * This method is used to get the middle point of the line.
     * @return returns the middle point.
     */
    public Point middle() {
        double xMid, yMid;
        xMid = (start.getX() + end.getX()) / 2;
        yMid = (start.getY() + end.getY()) / 2;
        Point mid = new Point(xMid, yMid);
        return mid;
    }

    // Returns the start point of the line.
    /**
     * This method is used to get the start point of the line.
     * @return returns the start point.
     */
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line.
    /**
     * This method is used to get the end point of the line.
     * @return returns the end point.
     */
    public Point end() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise.
    /**
     * This method is used to check if the lines intersect.
     * @param other
     *            This is the other line we will check if it intersects with the
     *            current line.
     * @return returns true or false.
     */
    public boolean isIntersecting(Line other) {
        // The point values are transferred to new variables for convinience.
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double y3 = other.start.getY();
        double y4 = other.end.getY();
        // New values are calculated for mathematic expression use (Expression found on
        // the internet).
        double bx = x2 - x1;
        double by = y2 - y1;
        double dx = x4 - x3;
        double dy = y4 - y3;
        double perp = bx * dy - by * dx;
        if (perp == 0) {
            return false;
        }
        double cx = x3 - x1;
        double cy = y3 - y1;
        double t = (cx * dy - cy * dx) / perp;
        if (t < 0 || t > 1) {
            return false;
        }
        double u = (cx * by - cy * bx) / perp;
        if (u < 0 || u > 1) {
            return false;
        }
        return true;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    /**
     * This method is used to get the intersection point values.
     * @param other
     *            This is the other line we will find the intersection point with
     * @return returns the intersection point
     */
    public Point intersectionWith(Line other) {
        // The point values are transferred to new variables for convinience.
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double y3 = other.start.getY();
        double y4 = other.end.getY();
        // If the lines do not intersect we return NULL and finish the method.
        if (!isIntersecting(other)) {
            return null;
        }
        // Line1 will be represedted as the equation a1x + b1y = c1.
        // Expressions also found on the internet.
        double a1 = y2 - y1;
        double b1 = x1 - x2;
        double c1 = a1 * (x1) + b1 * (y1);
        // Line 2 will be represented as the equation a2x + b2y = c2.
        double a2 = y4 - y3;
        double b2 = x3 - x4;
        double c2 = a2 * (x3) + b2 * (y3);
        double deter = a1 * b2 - a2 * b1;
        // The new point values are calculated.
        double newPointX = (b2 * c1 - b1 * c2) / deter;
        double newPointY = (a1 * c2 - a2 * c1) / deter;
        // A new point is made and returned.
        return new Point(newPointX, newPointY);
    }

    /**
     * This method is used to check if 2 lines are equal.
     * @param other
     *            This is the other line we will compare the current line to.
     * @return returns true or false.
     */
    // Equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        // The points of the lines are compared to check for equality.
        if (this.start == other.start && this.end == other.end) {
            return true;
        }
        if (this.start == other.end && this.end == other.start) {
            return true;
        }
        if (this.end == other.start && this.start == other.end) {
            return true;
        }
        return false;
    }
    /**
     * Method returns the closest intersection point of the rectangle to the line.
     * @param rect
     *          The rectangle we will find the intersection with.
     * @return  Returns the closest intersection point.
     */
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersect = rect.intersectionPoints(this);
        //If there are no intersection point return null.
        if (intersect == null) {
            return null;
        }
        //Finding the minimum.
        int minIndex = 0;
        double min = -1;
        for (int i = 0; i < intersect.size(); i++) {
            Point runningPoint = intersect.get(i);
            double distance = runningPoint.distance(this.start);
            if (min == -1) {
                min = distance;
                minIndex = i;
            } else {
                if (distance < min) {
                    minIndex = i;
                    min = distance;
                }
            }
        }
        return intersect.get(minIndex);
    }
}

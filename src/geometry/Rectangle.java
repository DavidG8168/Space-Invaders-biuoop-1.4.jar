package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * The 'Rectangle' program is an object used to make rectangles
 * They are used literally everywhere.
 */
public class Rectangle {
    //Rectangle members.
    private Point upperLeft;
    private double width;
    private double height;
    private Point bottomLeft;
    private Point bottomRight;
    private Point upperRight;
    /**
     * @param upperLeft
     *          Start point of the rectangle.
     * @param width
     *          Width of the rectangle.
     * @param height
     *          Height of the rectangle.
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        //Creating extra poins for easier calculations.
        this.bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.upperRight = new Point(this.getUpperLeft().getX() + this.width, this.upperLeft.getY());
        this.bottomRight = new Point(this.upperRight.getX(), this.upperRight.getY() + this.height);
    }

    /**
     * Method finds intersection points with the rectangle
     * and a line.
     * @param line
     *          The line the rectangle intersects with.
     * @return
     *          Returns a list of intersection points.
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List intersectionPoints(Line line) {
        List<Point> interL = new ArrayList<>();
        //Creating new lines.
        Line top, bottom, left, right;
        top = new Line(upperLeft, upperRight);
        bottom = new Line(bottomLeft, bottomRight);
        left = new Line(upperLeft, bottomLeft);
        right = new Line(upperRight, bottomRight);
        //Checking intersections and adding them.
        if (line.isIntersecting(top)) {
            Point p = (line.intersectionWith(top));
            interL.add(p);
            if (!exists(interL, p)) {
                interL.add(p);
            }
        }
        if (line.isIntersecting(bottom)) {
            Point p = (line.intersectionWith(bottom));
            if (!exists(interL, p)) {
                interL.add(p);
            }
        }
        if (line.isIntersecting(left)) {
            Point p = (line.intersectionWith(left));
            if (!exists(interL, p)) {
                interL.add(p);
            }
        }
        if (line.isIntersecting(right)) {
            Point p = (line.intersectionWith(right));
            if (!exists(interL, p)) {
                interL.add(p);
            }
        }
        //If none exist returns null.
        if (interL.size() == 0) {
            return null;
        }
        //Returns the list of points.
        return interL;
    }
    /**
     * Method checks if point p is on the line.
     * @param l1
     *          The line.
     * @param p
     *          The point.
     * @return  True or false.
     */
    public boolean exists(List<Point> l1, Point p) {
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).equals(p)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Method returns width of rectangle.
     * @return  Width of rectangle.
     */
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    /**
     * Method returns height of rectangle.
     * @return  Height of rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Method returns upper left point.
     * @return  upper left point.
     */
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Method returns upper right point.
     * @return  upper right point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
    /**
     * Method returns bottom left point.
     * @return  bottom left point.
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }
    /**
     * Method returns bottom right point.
     * @return  bottom right point.
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }
}

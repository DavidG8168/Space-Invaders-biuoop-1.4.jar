package play;

/**
 * The 'Counter' class is used to track points.
 * @author David Goichman.
 */
public class Counter {
    // The counter.
    private int count = 0;

    // add number to current count.
    /**
     * This method is used to increase the points.
     * @param number
     *            The number of points given.
     */
    void increase(int number) {
        this.count += number;
    }

    // subtract number from current count.
    /**
     * This method is used to decrease points.
     * @param number
     *            The number of points taken.
     */
    void decrease(int number) {
        this.count -= number;
    }

    // get current count.
    /**
     * This method returns the amount of points.
     * @return Returns the amount of points
     */
    int getValue() {
        return this.count;
    }
}
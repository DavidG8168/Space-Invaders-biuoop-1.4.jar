package play;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import mechanics.SpriteCollection;

//The CountdownAnimation will display the given gameScreen,
//for numOfSeconds seconds, and on top of them it will show
//a countdown from countFrom back to 1, where each number will
//appear on the screen for (numOfSeconds / countFrom) secods, before
//it is replaced with the next one.
/**
 * The 'CountdownAnimation' is in charge of the countdown.
 * @author David Goichman.
 */
public class CountdownAnimation implements Animation {
    // The members;
    private double numSecs;
    private int startNum;
    private SpriteCollection sprites;
    private Sleeper sleeper = new Sleeper();
    private int i;
    private boolean first = true;

    /**
     * The constructor.
     * @param numOfSeconds
     *            The number of seconds the countdown lasts.
     * @param countFrom
     *            The number the countdown starts from.
     * @param gameScreen
     *            The sprite collection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numSecs = numOfSeconds;
        this.startNum = countFrom;
        this.sprites = gameScreen;
        this.i = startNum;
    }

    /**
     * Animates the countdown.
     * @param d
     *            The drawsurface.
     * @param dt
     *          The amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        d.setColor(java.awt.Color.BLACK);
        d.drawText(680, 25, "Starting in: " + i + "", 20);
        long wait = (long) ((numSecs / startNum) * 1000);
        if (first) {
            i--;
            this.first = false;
        } else {
            sleeper.sleepFor(wait);
            i--;
        }
    }

    /**
     * Stops the countdown when it ends.
     * @return stopping condition.
     */
    public boolean shouldStop() {
        return i < 0;
    }
}
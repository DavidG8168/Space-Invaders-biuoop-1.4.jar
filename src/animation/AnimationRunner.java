package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * 'AnimationRunner' Runs the animation loop.
 * @author David Goichman.
 */
public class AnimationRunner {
    // The members of the class.
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * The Constuctor.
     * @param g
     *          The GUI.
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
    }

    /**
     * This method runs the animation.
     * @param animation
     *            The animation object.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, millisecondsPerFrame / 1000.0D);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Method returns the frames per fps of the game.
     * @return the fps.
     */
    public int getFrames() {
        return this.framesPerSecond;
    }
}
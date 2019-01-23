package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The decorator pattern for key presses.
 */
public class KeyPressStoppableAnimation implements Animation {
    // The members of the class.
    private KeyboardSensor keyboard;
    private String str;
    private Animation runner;
    private boolean wasPressed;
    private boolean isAlreadyPressed;

    /**
     * The constructor.
     * @param kbs
     *          The keyboard sensor.
     * @param s
     *          The key that will continue the animation.
     * @param a
     *          The animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor kbs, String s, Animation a) {
        this.keyboard = kbs;
        this.str = s;
        this.runner = a;
        this.wasPressed = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Method animates all the screens.
     * @param d
     *          The drawsurface.
     * @param dt
     *          The time between the last iteration.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // Animates the screens.
        runner.doOneFrame(d, dt);
        // If the key is pressed and was not pressed before.
        if (this.keyboard.isPressed(str) && !isAlreadyPressed) {
            wasPressed = true;
            isAlreadyPressed = false;
        }
        // If the key is not pressed.
        if (!this.keyboard.isPressed(str)) {
            isAlreadyPressed = false;
            wasPressed = false;
        }
    }

    /**
     * Method is in charge of stopping condition.
     * @return return if it should be stop or not.
     */
    public boolean shouldStop() {
        // If the key was pressed before.
        if (this.wasPressed) {
            this.wasPressed = false;
            return true;
        }
        // Stopping the screen.
        return runner.shouldStop();
    }
}

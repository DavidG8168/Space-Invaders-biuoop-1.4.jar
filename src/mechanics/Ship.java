package mechanics;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import play.GameLevel;

/**
 * The 'Paddle' program is an object used to create the paddle.
 */
public class Ship implements Sprite, Collidable {
    // Members.
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int paddleSpeed;
    private int framesPerSecond;
    private int trigger = 0;
    private int timer = 0;
    private int wasHit = 0;

    /**
     * Method mothes the paddle to the left.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void moveLeft(double dt) {
        int speed = (int) ((this.framesPerSecond) * (this.paddleSpeed * dt));
        final int leftLimit = 0;
        if (this.rectangle.getUpperLeft().getX() > leftLimit) {
            double movement = this.rectangle.getUpperLeft().getX() - speed;
            Rectangle movementRectangle = new Rectangle(new Point(movement, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            this.rectangle = movementRectangle;
        }
    }

    /**
     * Method moves the paddle to the right.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void moveRight(double dt) {
        int speed = (int) ((this.framesPerSecond) * (this.paddleSpeed * dt));
        final int rightLimit = 800;
        if (this.rectangle.getUpperRight().getX() < rightLimit) {
            double movement = this.rectangle.getUpperLeft().getX() + speed;
            Rectangle movementRectangle = new Rectangle(new Point(movement, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            this.rectangle = movementRectangle;
        }
    }

    /**
     * Method sets the fields of the paddle.
     * @param rec
     *            This is the rectangle the paddle will be shapen by.
     * @param key
     *            This is the keyboard object of the gui.
     * @param speed
     *            The paddle speed.
     * @param fps
     *            The framerate of the game.
     */
    public Ship(Rectangle rec, biuoop.KeyboardSensor key, int speed, int fps) {
        this.keyboard = key;
        this.rectangle = rec;
        this.paddleSpeed = speed;
        this.framesPerSecond = fps;
    }

    /**
     * This method will move the paddle when keys are pressed.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    // Sprite
    public void timePassed(double dt) {
        // We count the time before shooting a bullet.
        if (passTime() == 0) {
            // HOLD the space bar to shoot a bullet.
            if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                // We send a trigger message to the GameFlow class.
                this.trigger = 1;
            }
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * Method draws the paddle on the surface.
     * @param d
     *            This is the surface of the paddle.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.ORANGE);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX() + 1, (int) this.rectangle.getUpperLeft().getY() + 1,
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX() - 1, (int) this.rectangle.getUpperLeft().getY() - 1,
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(java.awt.Color.ORANGE);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth() + 1, (int) this.rectangle.getHeight() + 1);
    }

    /**
     * Returns the rectangle of the paddle.
     * @return paddle of the rectangle.
     */
    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @param collisionPoint
     *            The collision point with the paddle.
     * @param currentVelocity
     *            The velocity of the ball that hits the paddle.
     * @param hitter
     *            The ball that hits the block.
     * @return The new velocity.
     */
    public Velocity hit(Bullet hitter, Point collisionPoint, Velocity currentVelocity) {
        wasHit = 1;
        return null;
    }

    /**
     * Method adds paddle to the game.
     * @param g
     *            The game object.
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Removes the block from the game.
     * @param game
     *            The game block will be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Sending the trigger message to the GameFlow.
     * @return the trigger.
     */
    public int getTrigger() {
        return this.trigger;
    }

    /**
     * Resetting the trigger.
     */
    public void resetTrigger() {
        this.trigger = 0;
    }

    /**
     * Passing 0.35 seconds for the bullet cooldown.
     * @return the timer for bullet shots.
     */
    public int passTime() {
        timer++;
        if (timer == 20) {
            timer = 0;
        }
        return timer;
    }

    /**
     * Retrieves hit status.
     * @return wasHit.
     */
    public int hitCheck() {
        return wasHit;
    }
}

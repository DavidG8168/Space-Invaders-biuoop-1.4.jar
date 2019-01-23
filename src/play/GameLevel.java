package play;

import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.HitListener;
import animation.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import mechanics.Bullet;
import mechanics.Alien;
import mechanics.AlienBullets;
import mechanics.Collidable;
import mechanics.GameEnvironment;
import mechanics.Ship;
import mechanics.Shields;
import mechanics.Sprite;
import mechanics.SpriteCollection;
import mechanics.Velocity;

/**
 * The 'Game' program is an object used to create and start the game.
 * @author David Goichman
 */
public class GameLevel implements Animation {
    // Game members.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Ship paddle;
    private GUI gui;
    private KeyboardSensor keyboard;
    // The counters.
    private Counter blockCounter = new Counter();
    private Counter scoreCounter = new Counter();
    private Counter livesCounter = new Counter();
    // The animation parameters.
    private AnimationRunner runner;
    private boolean running = true;
    // The level information.
    private LevelInformation level;
    private List<Alien> aliens = new ArrayList<>();
    private static int speedMultiplier = 2;

    /**
     * The constructor.
     * @param k
     *            The KeyboardSensor.
     * @param ar
     *            The AnimationRunner.
     * @param score
     *            The score.
     * @param lives
     *            The amount of lives.
     */
    public GameLevel(KeyboardSensor k, AnimationRunner ar, Counter score, Counter lives) {
        // Creating the level.
        this.level = new LevelCreator(1);
        this.keyboard = k;
        this.runner = ar;
        this.scoreCounter = score;
        this.livesCounter = lives;
    }

    /**
     * Method adds a collidable to the environment.
     * @param c
     *            This is the collidable that will be added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Method adds a sprite to the game environment.
     * @param s
     *            This is the sprite that will be added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * This method creates all the objects and adds them to the game.
     */
    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        // Creating a new sprite collection & environment.
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        // Setting the background color & level decoration.
        this.addSprite(level.getBackground());
        // Adding the score, lives, & level name.
        HitListener scoreTracker = new ScoreTrackingListener(this.scoreCounter);
        Sprite scoreIndicator = new ScoreIndicator(this.scoreCounter);
        Sprite title = new NameIndicator(this.level.levelName());
        this.addSprite(scoreIndicator);
        this.addSprite(title);
        Sprite livesIndicator = new LivesIndicator(this.livesCounter);
        this.addSprite(livesIndicator);
        // Creating all the rows of blocks & adding them to the listener.
        HitListener blockRemover = new AlienRemover(this, this.blockCounter);
        List<Alien> blocks = level.blocks();
        for (int i = 0; i < level.numberOfBlocksToRemove(); i++) {
            Alien blockRows = blocks.get(i);
            blockRows.addToGame(this);
            blockCounter.increase(1);
            blockRows.addHitListener(blockRemover);
            blockRows.addHitListener(scoreTracker);
        }
        this.aliens = blocks;
        // Creating shields.
        for (int i = 0; i < 20; i++) {
            Shields row1 = new Shields(new Rectangle(new Point(10 * i, 520), 10, 10), 1);
            row1.addHitListener(blockRemover);
            row1.addToGame(this);
            Shields row2 = new Shields(new Rectangle(new Point(10 * i, 530), 10, 10), 1);
            row2.addHitListener(blockRemover);
            row2.addToGame(this);
        }
        for (int i = 30; i < 50; i++) {
            Shields row1 = new Shields(new Rectangle(new Point(10 * i, 520), 10, 10), 1);
            row1.addHitListener(blockRemover);
            row1.addToGame(this);
            Shields row2 = new Shields(new Rectangle(new Point(10 * i, 530), 10, 10), 1);
            row2.addHitListener(blockRemover);
            row2.addToGame(this);
        }
        for (int i = 60; i < 80; i++) {
            Shields row1 = new Shields(new Rectangle(new Point(10 * i, 520), 10, 10), 1);
            row1.addHitListener(blockRemover);
            row1.addToGame(this);
            Shields row2 = new Shields(new Rectangle(new Point(10 * i, 530), 10, 10), 1);
            row2.addHitListener(blockRemover);
            row2.addToGame(this);
        }
    }

    /**
     * This method animated a frame.
     */
    public void playOneTurn() {
        // Creating a new keyboard object.
        // Creating a paddle and adding it.
        Rectangle paddleRec = new Rectangle(new Point(300, 580), this.level.paddleWidth(), 10);
        paddle = new Ship(paddleRec, keyboard, level.paddleSpeed(), runner.getFrames());
        paddle.addToGame(this);
        // The countdown.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * This method runs the game.
     */
    public void run() {
        while (true) {
            playOneTurn();
            this.livesCounter.decrease(1);
            if (livesCounter.getValue() == 0) {
                System.out.println("Congratulations, you win... Nothing !");
                System.out.println("Your total score is: " + scoreCounter.getValue());
                gui.close();
                return;
            }
        }
    }

    /**
     * This method removes the collidable.
     * @param c
     *            The collidable that will be removed.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * This method removes the sprite.
     * @param s
     *            The sprite that will be removed.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprites(s);
    }

    /**
     * This method is in charge of a frame in the game.
     * @param d
     *            The drawsurface.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        AlienBullets alb = new AlienBullets(this.aliens, this, environment);
        alb.createBullet();
        // Creating the balls.
        this.createBullets();
        // Move the aliens.
        this.level.moveAliens();
        // Draw all the sprites & notify time passage.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        // Pausing if 'p' is pressed.
        if (this.keyboard.isPressed("p")) {
            Animation pause = new PauseScreen();
            Animation pauseScreen = new KeyPressStoppableAnimation(this.keyboard, "m", pause);
            this.runner.run(pauseScreen);
        }
        // Stopping if no aliens remain, they hit the shields or the ship was hit.
        if (this.blockCounter.getValue() == 0 || !this.hitShields() || paddle.hitCheck() == 1
                || !this.hitShields()) {
            // Decreasing lives.
            livesCounter.decrease(1);
            // Changing running condition.
            this.running = false;
            // Removing the destroyed ship.
            paddle.removeFromGame(this);
            // Removing the old aliens.
            this.removeOldAliens();
            // Remaking the level.
            this.level = new LevelCreator(speedMultiplier);
            speedMultiplier++;
            // Recreating the listeners.
            HitListener blockRemover = new AlienRemover(this, this.blockCounter);
            HitListener scoreTracker = new ScoreTrackingListener(this.scoreCounter);
            // Re creating the aliens.
            List<Alien> blocks = level.blocks();
            for (int i = 0; i < level.numberOfBlocksToRemove(); i++) {
                Alien blockRows = blocks.get(i);
                blockRows.addToGame(this);
                blockCounter.increase(1);
                blockRows.addHitListener(blockRemover);
                blockRows.addHitListener(scoreTracker);
            }
            this.aliens = blocks;
        }
    }

    /**
     * This method is in charge of stopping the game.
     * @return stopping condition.
     */
    public boolean shouldStop() {
        // TODO Auto-generated method stub
        return !this.running;
    }

    /**
     * This method creates the bullets in the game.
     */
    public void createBullets() {
        if (paddle.getTrigger() == 1) {
            // Getting the ball velocity.
            List<Velocity> speed = level.initialBallVelocities();
            // Creating the balls.
            Bullet ball = new Bullet(
                    paddle.getCollisionRectangle().getUpperLeft().getX()
                            + paddle.getCollisionRectangle().getWidth() / 2,
                    paddle.getCollisionRectangle().getUpperLeft().getY() - 10, 3, java.awt.Color.WHITE, environment,
                    this);
            ball.setVelocity(speed.get(0));
            // Adding the balls to the game.
            ball.addToGame(this);
            paddle.resetTrigger();
        }
    }

    /**
     * Returns the amount of blocks left.
     * @return The amount of blocks left in the level.
     */
    public int getNumOfBlocks() {
        return blockCounter.getValue();
    }

    /**
     * Stops the game if aliens hit shields.
     * @return true if they don't hit false overwise.
     */
    public boolean hitShields() {
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).hitShields()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remvoes old aliens when resetting the level.
     */
    public void removeOldAliens() {
        for (int i = 0; i < aliens.size(); i++) {
            aliens.get(i).removeFromGame(this);
        }
    }
}
package play;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Rectangle;
import mechanics.Alien;
import mechanics.Sprite;
import mechanics.Velocity;

/**
 * Creates the Space Invaders Level.
 */
public class LevelCreator implements LevelInformation {
    private List<Alien> blocks = new ArrayList<>();
    private int speed;

    /**
     * Constructor.
     * @param s
     *          The speed.
     */
    public LevelCreator(int s) {
        speed = s;
    }

    /**
     * The amount of balls in the game.
     * @return The amount of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Sets ball speeds.
     * @return The ball speeds.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballSpeeds = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(180, 7);
        ballSpeeds.add(v);
        return ballSpeeds;
    }

    /**
     * Sets speed of paddle.
     * @return The paddle speed.
     */
    public int paddleSpeed() {
        return 15;
    }

    /**
     * Sets paddle width.
     * @return The paddle width.
     */
    public int paddleWidth() {
        return 100;

    }

    /**
     * Sets the name of the level.
     * @return The name of the level.
     */
    public String levelName() {
        return "Space Invaders";
    }

    /**
     * Sets the background, color or image.
     * @return The background.
     */
    public Sprite getBackground() {
        Sprite background = new Alien(new Rectangle(new Point(0, 0), 800, 600), 0, false, 0);
        return background;
    }

    /**
     * Sets the blocks in the level.
     * @return List of blocks in the level.
     */
    public List<Alien> blocks() {
        for (int i = 1; i < 11; i++) {
            Alien blockRow1 = new Alien(new Rectangle(new Point(47 * i, 100), 47, 20), 1, true, speed);
            blocks.add(blockRow1);
        }
        for (int i = 1; i < 11; i++) {
            Alien blockRow2 = new Alien(new Rectangle(new Point(47 * i, 150), 47, 20), 1, true, speed);
            blocks.add(blockRow2);
        }
        for (int i = 1; i < 11; i++) {
            Alien blockRow3 = new Alien(new Rectangle(new Point(47 * i, 200), 47, 20), 1, true, speed);
            blocks.add(blockRow3);
        }
        for (int i = 1; i < 11; i++) {
            Alien blockRow4 = new Alien(new Rectangle(new Point(47 * i, 250), 47, 20), 1, true, speed);
            blocks.add(blockRow4);
        }
        for (int i = 1; i < 11; i++) {
            Alien blockRow5 = new Alien(new Rectangle(new Point(47 * i, 300), 47, 20), 1, true, speed);
            blocks.add(blockRow5);
        }
        return blocks;
    }

    /**
     * Sets number of blocks to remove from level.
     * @return Number of blocks to remove from level.
     */
    public int numberOfBlocksToRemove() {
        return 50;
    }

    /**
     * Moves the aliens.
     */
    public void moveAliens() {
        for (int i = 0; i < this.blocks.size(); i++) {
            blocks.get(i).moveInvaders();
        }
    }
}

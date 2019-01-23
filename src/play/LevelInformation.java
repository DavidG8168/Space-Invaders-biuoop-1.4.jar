package play;

import java.util.List;

import mechanics.Alien;
import mechanics.Sprite;
import mechanics.Velocity;

/**
 * The interface handles all levels.
 */
public interface LevelInformation {
    /**
     * Decides the amount of balls.
     * @return The number of balls.
     */
    int numberOfBalls();

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * Decides the velocity of each ball.
     * @return The list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Decides the speed of the balls.
     * @return The speed.
     */
    int paddleSpeed();

    /**
     * Decides the width of the paddle.
     * @return The width of the paddle.
     */
    int paddleWidth();

    // the level name will be displayed at the top of the screen.
    /**
     * Decides the name of the level.
     * @return The name of the level.
     */
    String levelName();

    // Returns a sprite with the background of the level
    /**
     * Decides the background color of the level.
     * @return The background color of the level.
     */
    Sprite getBackground();

    // The Blocks that make up this level, each block contains
    // its size, color and location.
    /**
     * Holds the blocks that make this level.
     * @return The list of blocks in this level.
     */
    List<Alien> blocks();

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    /**
     * Decides how many blocks needed to be removed to win.
     * @return The number of blocks needed to win.
     */
    int numberOfBlocksToRemove();

    /**
     * Method moves the Space Invaders.
     */
    void moveAliens();
}

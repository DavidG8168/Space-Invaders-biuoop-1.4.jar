package mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import play.GameLevel;

/**
 * This class is in charge of creating the bullets that aliens will shot.
 */
public class AlienBullets {
    // The members.
    private List<Alien> aliens = new ArrayList<>();
    private GameLevel level;
    private GameEnvironment environment;
    private Velocity v = Velocity.fromAngleAndSpeed(0, 7);
    private static int timer = 0;

    /**
     * The constructor.
     * @param a
     *            The list of aliens.
     * @param lvl
     *            The gamelevel.
     * @param game
     *            The game environment.
     */
    public AlienBullets(List<Alien> a, GameLevel lvl, GameEnvironment game) {
        this.aliens = a;
        this.level = lvl;
        this.environment = game;
    }

    /**
     * Finding the alien that will shoot the bullet.
     * @return The index of the alien that shoots.
     */
    public int findLow() {
        // Finding all the lowest aliens.
        List<Alien> lowest = new ArrayList<>();
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getCollisionRectangle().getBottomLeft().getY() == this.findLowestY()) {
                lowest.add(aliens.get(i));
            }
        }
        // Getting an index of a random one.
        Random randomizer = new Random();
        Alien random = lowest.get(randomizer.nextInt(lowest.size()));
        for (int i = 0; i < aliens.size(); i++) {
            if (random == aliens.get(i)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Creats a bullet for the alien to shoot.
     */
    public void createBullet() {
        // Timer used to measure seconds.
        // Using a random alien index, we create a bullet.
        if (timer == 0) {
            int where = this.findLow();
            Bullet ball = new Bullet(
                    aliens.get(where).getCollisionRectangle().getBottomLeft().getX()
                            + aliens.get(where).getCollisionRectangle().getWidth() / 2,
                    aliens.get(where).getCollisionRectangle().getBottomLeft().getY() + 10, 3, java.awt.Color.RED,
                    environment, level);
            ball.setVelocity(v);
            ball.addToGame(level);
        }
        timer++;
        // Resetting the timer.
        if (timer == 40) {
            timer = 0;
        }
    }

    /**
     * Findint the lowest alien Y value.
     * @return the value.
     */
    public double findLowestY() {
        // Finding lowest Y value an alien can be in.
        double min = 0;
        for (int i = 0; i < aliens.size() - 1; i++) {
            if (aliens.get(i).getCollisionRectangle().getBottomLeft().getY() <= aliens.get(i + 1)
                    .getCollisionRectangle().getBottomLeft().getY()) {
                min = aliens.get(i).getCollisionRectangle().getBottomLeft().getY();
            }
        }
        return min;
    }
}

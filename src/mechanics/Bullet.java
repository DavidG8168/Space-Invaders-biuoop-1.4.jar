package mechanics;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import play.GameLevel;

/**
 * The 'Bullet' program is an object used to make the bullet the 'Ship' shoots.
 */
public class Bullet implements Sprite {
    // Bullet information.
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    // Bullet container information.
    private int height;
    private int width;
    // Boolean used for reset of the Bullet if ir spawns outside container.
    private boolean start;
    // This is the game environment.
    private GameEnvironment gameBoard;
    private GameLevel lvl;

    // Bullet Constructors.
    /**
     * This method is used to apply values to the ball fields.
     * @param center
     *            This is the center point of the ball.
     * @param r
     *            This is the radius of the ball.
     * @param color
     *            This is the color of the ball.
     * @param gameBoard
     *            This is the game environment we will be using.
     * @param gameLevel
     *            This is the game level we will use.
     */
    public Bullet(Point center, int r, java.awt.Color color, GameEnvironment gameBoard, GameLevel gameLevel) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.start = true;
        this.gameBoard = gameBoard;
        this.lvl = gameLevel;
    }

    /**
     * This method is used to apply values to the ball fields.
     * @param x
     *            This is the center x point of the ball.
     * @param y
     *            This is the center y point of the ball.
     * @param r
     *            This is the radius of the ball.
     * @param color
     *            This is the color of the ball.
     * @param gameBoard
     *            This is the game environment we will be using.
     * @param gameLevel
     *            This is the game level we will use.
     */
    public Bullet(double x, double y, int r, java.awt.Color color, GameEnvironment gameBoard, GameLevel gameLevel) {
        Point p = new Point(x, y);
        this.center = p;
        this.r = r;
        this.color = color;
        this.start = true;
        this.gameBoard = gameBoard;
        this.lvl = gameLevel;
    }

    // Bullet accessors.
    /**
     * This method is used to get the x value of the center point.
     * @return returns the x value of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This method is used to get the y value of the center point.
     * @return returns the y value of the center point.
     */
    public int getY() {
        return (int) this.center.getY();

    }

    /**
     * This method is used to get the radius value of the center point.
     * @return returns the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * This method is used to get the color of the ball.
     * @return returns the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This method is used to draw the Bullet on the surface.
     * @param surface
     *            This is the surface the ball will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        this.height = surface.getHeight();
        this.width = surface.getWidth();
        // If the ball is created outside of the container, it will be reset to the
        // minimal position of top left.
        int newX = this.getX(), newY = this.getY();
        if (start) {
            start = false;
            if (this.center.getX() < this.r || this.center.getX() > width - this.r) {
                newX = r;
            }
            if (this.center.getY() < this.r || this.center.getY() > height - this.r) {
                newY = r;
            }
        }
        Point fixed = new Point(newX, newY);
        center = fixed;
        surface.setColor(java.awt.Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize() + 1);
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * This method is used to apply the velocity of the Bullet.
     * @param v
     *            This is the velocity the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method is used to apply the velocity of the ball using dx and dy values.
     * @param dx
     *            This is the change in distance of the x grid.
     * @param dy
     *            This is the change in distance of the y grid.
     */
    public void setVelocity(double dx, double dy) {
        Velocity newVel = new Velocity(dx, dy);
        this.velocity = newVel;
    }

    /**
     * This method is used to get the velocity of the ball.
     * @return returns the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This method is used in the Game object to animate and move the Bullet around
     * the GUI.
     * @param dt
     *            The amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
        // We scale the veloctiy to the dt.
        Velocity scaledVelocity = this.velocity.scale(dt);
        // We will find the trajectory of the Bullet.
        Line trajectory = new Line(this.center, scaledVelocity.applyToPoint(this.center));
        // If the ball does not collide with any objectm, it will move regularly.
        if (this.gameBoard.getClosestCollision(trajectory) == null) {
            this.center = scaledVelocity.applyToPoint(this.center);
            // If the Bullet does hit an object, however, we will take into account a slight
            // offset in the hit point
            // So that we can change it's velocity in a slightly easier manner.
        } else {
            // The collision point of the Bullet and the object.
            Point collisionPoint = this.gameBoard.getClosestCollision(trajectory).collisionPoint();
            this.gameBoard.getClosestCollision(trajectory).collisionObject().hit(this, collisionPoint,
                    this.velocity);
            this.removeFromGame(lvl);
        }
    }

    /**
     * This method is useed to add the Bullet sprite to the Game object.
     * @param g
     *            The Game object.
     */
    public void addToGame(GameLevel g) {
        // Adding the sprite using the addSprite function.
        g.addSprite(this);
    }

    /**
     * Removes the Bullet from the game.
     * @param game
     *            The game the Bullet will be removed from.
     */
    public void removeFromGame(GameLevel game) {
        if (this != null) {
            game.removeSprite(this);
        }
    }
}

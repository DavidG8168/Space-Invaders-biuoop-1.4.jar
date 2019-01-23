package animation;

/**
 * This class handles the highscore table task in the menu.
 * @author David Goichman.
 */
public class ShowHighScoresTask implements Task<Void> {
    // The members of the class.
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * The constructor.
     * @param runner
     *          The animation runner.
     * @param highScoresAnimation
     *          The highscores table.
     */
    public ShowHighScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * Shows the highscore table.
     * @return null.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
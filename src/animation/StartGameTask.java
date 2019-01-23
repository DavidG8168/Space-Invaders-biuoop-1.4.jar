package animation;

import java.util.List;

import play.GameFlow;
import play.LevelInformation;

/**
 * Class starts the game.
 */
public class StartGameTask implements Task<Void> {
    //The members of the class.
    private GameFlow gflow = null;
    private List<LevelInformation> levels = null;

    /**
     * The constructor.
     * @param game
     *          The gameflow.
     * @param levelsList
     *          The list of levels.
     */
    public StartGameTask(GameFlow game, List<LevelInformation> levelsList) {
        this.gflow = game;
        this.levels = levelsList;
    }

    /**
     * Method runs the game.
     * @return null.
     */
    public Void run() {
        gflow.runLevels(levels);
        return null;
    }
}

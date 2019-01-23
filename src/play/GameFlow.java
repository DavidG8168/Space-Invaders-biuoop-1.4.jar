package play;

import java.io.File;
import java.io.IOException;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import mechanics.HighScoresTable;
import mechanics.ScoreInfo;

/**
 * The 'GameFlow' class is in charge of changing level.
 */
public class GameFlow {
    // The members of the class.
    private Counter score = new Counter();
    private Counter lives = new Counter();
    private KeyboardSensor k;
    private AnimationRunner a;
    private HighScoresTable scoreTable;
    private GUI gui;

    /**
     * The constructor.
     * @param ar
     *            The AnimationRunner.
     * @param ks
     *            The Keyboard Sensor.
     * @param g
     *            The gui.
     * @param allScores
     *            The scores.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g, HighScoresTable allScores) {
        gui = g;
        a = ar;
        k = ks;
        lives.increase(3);
        scoreTable = allScores;
    }

    /**
     * This method uns each level.
     * @param levels
     *            The list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // Creating the scoreboard file.
        File highscores = new File("highscores");
        // If it does not exist we create a new one.
        if (!highscores.exists()) {
            try {
                this.scoreTable.save(new File("highscores"));
            } catch (IOException e) {
                System.out.println("Failed creating a file !");
                return;
            }
            // If it exists we load a previous scoreboard file.
        } else {
            try {
                this.scoreTable.load(highscores);
            } catch (IOException e) {
                System.out.println("Failed reading from file");
                return;
            }
        }
        // Running the levels.
        while (true) {
            GameLevel level = new GameLevel(this.k, this.a, this.score, this.lives);
            level.initialize();
            while (level.getNumOfBlocks() > 0 && this.lives.getValue() > 0) {
                level.playOneTurn();
            }
            break;
        }
        //Endscreen is shown.
        Animation win = new EndScreen(this.score);
        Animation winScreen = new KeyPressStoppableAnimation(k, "m", win);
        this.a.run(winScreen);
        // If the player needs to be added to the score board, we show it.
        if (checkScore()) {
            add();
            Animation scoreboard = new HighScoresAnimation(scoreTable);
            Animation scoreScreen = new KeyPressStoppableAnimation(k, "m", scoreboard);
            this.a.run(scoreScreen);
        }
    }

    /**
     * Method checks if the score needs to be added to the scoreboard.
     * @return True or False.
     */
    public boolean checkScore() {
        return !(this.scoreTable.getRank(this.score.getValue()) > this.scoreTable.size());
    }

    /**
     * Method adds the score to the board.
     */
    public void add() {
        // Getting the player name.
        DialogManager dialog = this.gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "Enter your name:", "");
        if (name != null) {
            this.scoreTable.add(new ScoreInfo(name, this.score.getValue()));
            try {
                this.scoreTable.save(new File("highscores"));
            } catch (IOException e) {
                System.out.println("Failed creating a file.");
                return;
            }
        }
    }
}

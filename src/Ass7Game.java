import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import animation.CloseGameTask;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import animation.ShowHighScoresTask;
import animation.StartGameTask;
import animation.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import mechanics.HighScoresTable;
import play.GameFlow;
import play.LevelCreator;
import play.LevelInformation;

/**
 * The 'Main' program is used to start and play the game.
 */
public class Ass7Game {
    /**
     * This is the main, are you not entertained yet.
     * @param args
     *            Command line does nothing here.
     * @throws IOException
     *             Throws exception if failed opening file.
     */
    public static void main(String[] args) throws IOException {
        // Creating the GUI
        GUI gui = new GUI("Space Invaders", 800, 600);
        // Creating the Animation Runner.
        AnimationRunner runner = new AnimationRunner(gui);
        // Creating the keyboard sensor.
        KeyboardSensor key = gui.getKeyboardSensor();
        // Creating the HighScoresTable.
        HighScoresTable scoreTable = new HighScoresTable(15);
        // Running the menu and game.
        while (true) {
            // Creating The Space Invaders level.
            List<LevelInformation> spaceInvaderLevel = new ArrayList<LevelInformation>();
            spaceInvaderLevel.add(new LevelCreator(1));
            // Creating A GAME FLOW.
            GameFlow game = new GameFlow(runner, key, gui, scoreTable);
            // Creating a MENU.
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Space Invaders", key);
            menu.addSelection("s", "Press to START GAME.", new StartGameTask(game, spaceInvaderLevel));
            menu.addSelection("h", "Press to SEE SCORES.", new ShowHighScoresTask(runner,
                    new KeyPressStoppableAnimation(key, "m", new HighScoresAnimation(scoreTable))));
            // Press 'Q' to quit the game.
            menu.addSelection("q", "Press to QUIT game.", new CloseGameTask(gui));
            // Running the MENU.
            runner.run(menu);
            // Getting a MENU TASK.
            Task<Void> task = menu.getStatus();
            // Running a TASK.
            task.run();
            // Resetting the MENU.
            menu.reset();
        }
    }
}

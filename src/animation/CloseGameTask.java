package animation;

import biuoop.GUI;

/**
 * This task closes the game.
 * @author David Goichman.
 */
public class CloseGameTask implements Task<Void> {
    // The member of the class.
    private GUI game;

    /**
     * The constructor.
     * @param gui
     *          The gui.
     */
    public CloseGameTask(GUI gui) {
        this.game = gui;
    }

    /**
     * This method closes the gui to end the game.
     * @return null.
     */
    public Void run() {
        game.close();
        return null;
    }
}

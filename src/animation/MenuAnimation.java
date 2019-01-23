package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class animated the menu.
 * @param <T>
 *            The tasks.
 */
public class MenuAnimation<T> implements Menu<T> {
    // The members of the class.
    private KeyboardSensor keyboardSensor;
    private String title;
    private List<T> menuReturnValues;
    private List<String> menuItemNames;
    private List<String> menuItemKeys;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;
    private T status;

    /**
     * The constructor.
     * @param title
     *            The title of the menu.
     * @param keyboardSensor
     *            The keyboard sensor.
     */
    public MenuAnimation(String title, KeyboardSensor keyboardSensor) {
        this.title = title;
        this.keyboardSensor = keyboardSensor;
        menuItemKeys = new ArrayList();
        menuItemNames = new ArrayList();
        menuReturnValues = new ArrayList();
        reset();
    }

    /**
     * Adds selection choices to the menu.
     * @param key
     *            The key to use the menu option.
     * @param message
     *            The name of the menu choice.
     * @param returnVal
     *            The return value.
     */
    public void addSelection(String key, String message, T returnVal) {
        menuItemKeys.add(key);
        menuItemNames.add(message);
        menuReturnValues.add(returnVal);
    }

    /**
     * method returns the status of the selection.
     * @return The status.
     */
    public T getStatus() {
        return status;
    }

    /**
     * Method resets the menu.
     */
    public void reset() {
        status = null;
    }

    /**
     * Method controls the animation stopping condition.
     * @return if to stop.
     */
    public boolean shouldStop() {
        return status != null;
    }

    /**
     * Method animates the menu.
     * @param d
     *            The drawsurface.
     * @param dt
     *            The seconds between each iteration.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // Drawing the background image.
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        // Drawing the text.
        d.setColor(Color.RED);
        d.drawText(51, 50, title, 40);
        d.drawText(49, 50, title, 40);
        d.drawText(50, 51, title, 40);
        d.drawText(50, 49, title, 40);
        d.setColor(Color.YELLOW);
        d.drawText(50, 50, title, 40);
        // Drawing the menu options.
        for (int i = 0; i < menuItemNames.size(); i++) {
            int optionX = 100;
            int optionY = 120 + i * 40;
            String optionText = "(" + (String) menuItemKeys.get(i) + ") " + (String) menuItemNames.get(i);
            d.setColor(Color.RED);
            d.drawText(optionX + 1, optionY, optionText, 24);
            d.drawText(optionX - 1, optionY, optionText, 24);
            d.drawText(optionX, optionY + 1, optionText, 24);
            d.drawText(optionX, optionY - 1, optionText, 24);
            d.setColor(Color.YELLOW);
            d.drawText(optionX, optionY, optionText, 24);
        }
        for (int i = 0; i < menuReturnValues.size(); i++) {
            if (keyboardSensor.isPressed((String) menuItemKeys.get(i))) {
                status = menuReturnValues.get(i);
                break;
            }
        }
    }
}

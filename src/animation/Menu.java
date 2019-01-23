package animation;

/**
 * This interface contains the menu methods.
 * @author David Goichman.
 * @param <T>
 *            The task handler interface.
 */
public interface Menu<T> extends Animation {
    /**
     * Method adds options to the menu.
     * @param key
     *            The button to use the option.
     * @param message
     *            The action the button will perform.
     * @param returnVal
     *            What we will get from pressing the button.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Method gets task that needs to be performed.
     * @return The task that needs to be performed.
     */
    T getStatus();

    /**
     * Resets selection.
     */
    void reset();
}
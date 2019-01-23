package animation;

/**
 * This interface handles the tasks in the menu.
 * @author David Goichman
 * @param <T>
 *            The task type.
 */
public interface Task<T> {
    /**
     * Method runs the task.
     * @return The task.
     */
    T run();
}
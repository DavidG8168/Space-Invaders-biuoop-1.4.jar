package animation;

/**
 * This is the given HitNotifier interface.
 * @author David Goichman.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    /**
     * This method adds the HitListener to the list.
     * @param hl
     *            The HitListener we will add.
     */
    void addHitListener(HitListener hl);

    // Remove hl from the list of listeners to hit events.
    /**
     * This method removes the HitListener from the list.
     * @param hl
     *            The HitListener we will remove.
     */
    void removeHitListener(HitListener hl);
}
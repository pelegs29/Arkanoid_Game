//318509700
package game.listeners;

/**
 * This interface handles the HitNotifier methods.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public interface HitNotifier {

    /**
     * This method simply add hl as listener to future hit events.
     *
     * @param hl given HitListener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * This method simply removes hl as listener.
     *
     * @param hl given HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}

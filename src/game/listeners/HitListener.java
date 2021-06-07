//318509700
package game.listeners;

import game.elements.Ball;
import game.elements.Block;

/**
 * This interface handles the HitListener methods.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public interface HitListener {

    /**
     * This method will get called when the block is being hit.
     * and will execute hit event.
     *
     * @param beingHit block's which is being hit.
     * @param hitter   the ball which hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
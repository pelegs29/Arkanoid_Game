//318509700
package game.listeners;

import game.elements.Ball;
import game.elements.Block;

/**
 * This class implements printing listener which s in charge of printing
 * a massage every time a block as hit.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class PrintingHitListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
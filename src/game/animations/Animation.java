//318509700
package game.animations;

import biuoop.DrawSurface;

/**
 * This interface handles the game.Animation methods.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public interface Animation {
    /**
     * this method is in charge of doing a single frame in the animation.
     *
     * @param d the given DrawSurface to draw on the animation.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if the animation should stop and false otherwise.
     */
    boolean shouldStop();
}

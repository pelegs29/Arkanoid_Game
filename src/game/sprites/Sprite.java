//318509700
package game.sprites;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * This interface handles the Sprites methods.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public interface Sprite {

    /**
     * This method draws the sprite to the screen.
     *
     * @param d the given DrawSurface surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * This method notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * This method adds the Sprite to the game.Game.
     *
     * @param g given game.Game object.
     */
    void addToGame(GameLevel g);

    /**
     * This method removes the Sprite from the game.Game.
     *
     * @param g given game.Game object.
     */
    void removeFromGame(GameLevel g);
}
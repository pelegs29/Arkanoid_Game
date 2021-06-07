//318509700
package game.sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all the methods related to
 * the object SpriteCollection.
 * this class will hold the collection of Sprite objects.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class SpriteCollection {

    private final List<Sprite> spritesList;

    /**
     * Sole Constructor.
     * creates an empty ArrayList of game.sprites.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<Sprite>();
    }

    /**
     * This method adds the given Sprite to the Sprites collection.
     *
     * @param s the given Sprite to be added.
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * This method removes the given Sprite from the Sprites collection.
     *
     * @param s the given Sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        spritesList.remove(s);
    }

    /**
     * This method calls timePassed() on all game.sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the spritesList before iterating over them.
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.spritesList);
        // Notify all game.sprites that time passed.
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * This method calls drawOn(d) on all game.sprites.
     *
     * @param d the given DrawSurface surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        // Make a copy of the spritesList before iterating over them.
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.spritesList);
        // draw all game.sprites.
        for (Sprite s : spritesCopy) {
            s.drawOn(d);
        }
    }

    /**
     * @return this Sprites List.
     */
    public List<Sprite> getSpritesList() {
        return this.spritesList;
    }
}
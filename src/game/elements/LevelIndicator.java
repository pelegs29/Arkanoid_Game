//318509700
package game.elements;

import biuoop.DrawSurface;
import game.GameFlow;
import game.GameLevel;
import game.sprites.Sprite;

/**
 * This class implements the level indicator functionality in the game.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class LevelIndicator implements Sprite {
    private final String levelName;

    /**
     * Constructor which sets the levelName given.
     *
     * @param levelName the string of the level name to be presented.
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int textXVal = (GameFlow.GUI_WIDTH / 2) + 150;
        int textYVal = 17;
        d.drawText(textXVal, textYVal, "Level Name: " + this.levelName, textYVal);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
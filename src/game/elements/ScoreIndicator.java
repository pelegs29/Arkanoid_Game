//318509700
package game.elements;

import biuoop.DrawSurface;
import game.GameLevel;
import game.listeners.ScoreTrackingListener;
import game.sprites.Sprite;

/**
 * This class implements the score indicator functionality in the game.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class ScoreIndicator implements Sprite {

    //the scoreListener for this time can be final.
    private final ScoreTrackingListener scoreListener;

    /**
     * Main Constructor.
     * creates ScoreIndicator with the given ScoreTrackingListener.
     *
     * @param listener the given ScoreTrackingListener.
     */
    public ScoreIndicator(ScoreTrackingListener listener) {
        this.scoreListener = listener;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int textXVal = (GameLevel.GUI_WIDTH / 2) - 40;
        int textYVal = 17;
        d.drawText(textXVal, textYVal, "Score: " + this.scoreListener.getCurrentScore().toString(), textYVal);
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
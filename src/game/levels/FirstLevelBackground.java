//318509700
package game.levels;

import biuoop.DrawSurface;
import game.GameFlow;
import game.GameLevel;
import game.sprites.Sprite;

import java.awt.Color;

/**
 * This class generate the background for the level.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class FirstLevelBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        //draw background.
        d.setColor(new Color(30, 30, 33));
        d.fillRectangle(0, 0, GameFlow.GUI_WIDTH, GameFlow.GUI_HEIGHT);

        //draw the crosshair.
        d.setColor(Color.BLUE);
        d.drawLine(250, 165, 380, 165);
        d.drawLine(420, 165, 550, 165);
        d.drawLine(400, 0, 400, 140);
        d.drawLine(400, 190, 400, 310);
        d.drawCircle(400, 165, 60);
        d.drawCircle(400, 165, 95);
        d.drawCircle(400, 165, 125);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }

    @Override
    public void removeFromGame(GameLevel g) {
    }
}
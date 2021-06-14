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
public class SecondLevelBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(229, 229, 232));
        d.fillRectangle(0, 0, GameFlow.GUI_WIDTH, GameFlow.GUI_HEIGHT);

        d.setColor(new Color(210, 205, 93));
        for (int i = 0; i < 700; i = i + 6) {
            d.drawLine(130, 130, i, 250);
        }

        d.setColor(new Color(213, 209, 156));
        d.fillCircle(130, 130, 70);

        d.setColor(new Color(217, 200, 66));
        d.fillCircle(130, 130, 60);

        d.setColor(new Color(236, 227, 17));
        d.fillCircle(130, 130, 50);
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
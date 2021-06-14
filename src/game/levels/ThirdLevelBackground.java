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
public class ThirdLevelBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(19, 121, 24));
        d.fillRectangle(0, 0, GameFlow.GUI_WIDTH, GameFlow.GUI_HEIGHT);
        d.setColor(new Color(54, 51, 51));
        d.fillRectangle(55, 450, 100, 200);
        d.setColor(new Color(208, 199, 199));

        //creates the windows.
        for (int i = 0; i < 150; i = i + 30) {
            for (int j = 0; j < 85; j = j + 17) {
                d.fillRectangle(66 + j, 460 + i, 10, 25);
            }
        }
        d.setColor(new Color(66, 66, 68));
        d.fillRectangle(90, 400, 30, 50);
        d.setColor(new Color(85, 85, 87));
        d.fillRectangle(99, 210, 13, 190);
        d.setColor(new Color(226, 189, 132));
        d.fillCircle(106, 210, 13);
        d.setColor(new Color(238, 102, 78));
        d.fillCircle(106, 210, 9);
        d.setColor(Color.WHITE);
        d.fillCircle(106, 210, 4);
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
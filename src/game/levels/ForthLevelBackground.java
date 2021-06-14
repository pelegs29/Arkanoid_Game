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
public class ForthLevelBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

        //draw background.
        d.setColor(new Color(30, 114, 206));
        d.fillRectangle(0, 0, GameFlow.GUI_WIDTH, GameFlow.GUI_HEIGHT);

        //draw rain drops and clouds.
        d.setColor(new Color(197, 197, 199));
        for (int i = 0; i < 250; i += 25) {
            for (int j = 20; j < 140; j += 20) {
                d.drawLine(90 + j, 370 + i, 85 + j, 390 + i);
                d.drawLine(580 + j, 520 + i, 570 + j, 540 + i);
            }
        }
        d.fillCircle(90, 350, 25);
        d.fillCircle(110, 370, 30);
        d.fillCircle(205, 340, 30);
        d.fillCircle(580, 480, 25);
        d.fillCircle(600, 500, 30);
        d.fillCircle(708, 490, 30);

        d.setColor(new Color(175, 171, 171));
        d.fillCircle(130, 335, 35);
        d.fillCircle(180, 365, 25);
        d.fillCircle(620, 485, 35);
        d.fillCircle(670, 515, 25);

        d.setColor(new Color(146, 149, 151));
        d.fillCircle(150, 365, 25);
        d.fillCircle(170, 335, 40);
        d.fillCircle(640, 515, 25);
        d.fillCircle(660, 485, 40);
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
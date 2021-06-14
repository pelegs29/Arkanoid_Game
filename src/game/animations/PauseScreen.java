//318509700
package game.animations;

import biuoop.DrawSurface;
import game.GameFlow;

import java.awt.Color;

/**
 * This class is an Animation of the pause screen after the player pressed 'p'.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class PauseScreen implements Animation {

    /**
     * A constructor is not needed -
     * the class has no fields and keeps running forever.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(43, 108, 135));
        d.fillRectangle(0, 0, GameFlow.GUI_WIDTH * 2, GameFlow.GUI_HEIGHT * 2);
        d.setColor(Color.BLACK);
        d.drawText((d.getWidth() / 2) - 165, (d.getHeight() / 3) + 5, "Paused", 100);
        d.drawText(143, (d.getHeight() / 2) + 253, "-- please press 'space' to continue --", 32);
        d.setColor(Color.WHITE);
        d.drawText((d.getWidth() / 2) - 170, d.getHeight() / 3, "Paused", 100);
        d.drawText(140, (d.getHeight() / 2) + 250, "-- please press 'space' to continue --", 32);
    }

    @Override
    public boolean shouldStop() {

        //no stopping condition - will stop only with KeyPressStoppableAnimation.
        return false;
    }
}
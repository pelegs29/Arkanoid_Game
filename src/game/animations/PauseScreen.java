package game.animations;

import biuoop.DrawSurface;
import game.GameFlow;

import java.awt.Color;

public class PauseScreen implements Animation {

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
        return false;
    }
}

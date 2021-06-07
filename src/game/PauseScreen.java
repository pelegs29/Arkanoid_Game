package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class PauseScreen implements Animation {
    protected final KeyboardSensor keyboard;
    protected boolean stop;

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
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
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

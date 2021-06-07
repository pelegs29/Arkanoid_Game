package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class WelcomeScreen extends PauseScreen {

    public WelcomeScreen(KeyboardSensor k) {
        super(k);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(175, 140, 34));
        d.fillRectangle(0, 0, GameFlow.GUI_WIDTH * 2, GameFlow.GUI_HEIGHT * 2);
        d.setColor(Color.BLACK);
        d.drawText(55, (d.getHeight() / 3) + 5, "Welcome", 150);
        d.drawText(143, (d.getHeight() / 2) + 253, "-- please press 'space' to start --", 32);
        d.drawText(60, (d.getHeight() / 2) - 6, "Press 'p' at any time to pause the game", 40);
        d.setColor(Color.WHITE);
        d.drawText(50, d.getHeight() / 3, "Welcome", 150);
        d.drawText(140, (d.getHeight() / 2) + 250, "-- please press 'space' to start --", 32);
        d.drawText(56, (d.getHeight() / 2) - 10, "Press 'p' at any time to pause the game", 40);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
}

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.elements.Block;
import game.elements.Counter;

import java.awt.Color;

public class EndScreen extends PauseScreen {
    Boolean hasWon;
    String scoreString;

    public EndScreen(KeyboardSensor k, boolean victoryFlag, Counter score) {
        super(k);
        this.hasWon = victoryFlag;
        this.scoreString = String.valueOf(score.getValue());
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String endString;
        if (this.hasWon) {
            endString = "You Win!";
            d.setColor(new Color(23, 179, 35));
        } else {
            endString = "Game Over.";
            d.setColor(new Color(173, 42, 42));
        }
        d.fillRectangle(0, 0, GameFlow.GUI_WIDTH * 2, GameFlow.GUI_HEIGHT * 2);
        d.setColor(Color.BLACK);
        d.drawText((d.getWidth() / 2) - 285, (d.getHeight() / 3) + 5, endString, 100);
        d.drawText(143, (d.getHeight() / 2) + 253, "-- please press 'space' to exit --", 32);
        d.drawText(140, (d.getHeight() / 2) -6, "Your Score is " + scoreString, 60);
        d.setColor(Color.WHITE);
        d.drawText((d.getWidth() / 2) - 290, d.getHeight() / 3, endString, 100);
        d.drawText(140, (d.getHeight() / 2) + 250, "-- please press 'space' to exit --", 32);
        d.drawText(136, (d.getHeight() / 2) -10, "Your Score is " + scoreString, 60);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
}

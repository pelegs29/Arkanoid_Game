package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.sprites.SpriteCollection;

import java.awt.*;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection sprites;
    private GUI gui;
    private boolean stop;

    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen, GUI gui) {
        stop = false;
        for (int i = countFrom; i > 0; i--) {
            DrawSurface d = gui.getDrawSurface();
            gameScreen.drawAllOn(d);
            d.setColor(Color.WHITE);
            d.drawText((d.getWidth() / 2) + 2, (d.getHeight() / 2) + 2, String.valueOf(i), 45);
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(i), 45);
            gui.show(d);
            Sleeper sleep = new Sleeper();
            double timer = (numOfSeconds / (double) countFrom);
            sleep.sleepFor((long) (timer * 1000));
        }
        stop = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

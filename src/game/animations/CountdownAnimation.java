package game.animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.sprites.SpriteCollection;

import java.awt.Color;

public class CountdownAnimation implements Animation {
    private final int countFrom;
    private int currentCount;
    private final SpriteCollection sprites;
    private final double sleepTimer;

    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.currentCount = this.countFrom;
        this.sprites = gameScreen;
        this.sleepTimer = (numOfSeconds / (double) countFrom);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText((d.getWidth() / 2) - 13, d.getHeight() - 248, String.valueOf(currentCount), 45);
        d.setColor(Color.BLACK);
        d.drawText((d.getWidth() / 2) - 15, d.getHeight() - 250, String.valueOf(currentCount), 45);
        this.currentCount--;
    }

    @Override
    public boolean shouldStop() {
        if (currentCount != countFrom) {
            Sleeper sleep = new Sleeper();
            sleep.sleepFor((long) (sleepTimer * 1000));
        }
        return this.currentCount == 0;
    }
}

//318509700
package game.animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.sprites.SpriteCollection;

import java.awt.Color;

/**
 * This class is an Animation of a countdown before each level starts.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class CountdownAnimation implements Animation {
    private final int countFrom;
    private int currentCount;
    private final SpriteCollection sprites;
    private final double sleepTimer;

    /**
     * Constructor which initialize the args given and sets the sleep timer accordingly.
     *
     * @param numOfSeconds the total number of seconds of the animation.
     * @param countFrom    the starting number of the countdown.
     * @param gameScreen   the given sprites of the level.
     */
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
        //dont sleep before the countdown started.
        if (currentCount != countFrom) {
            Sleeper sleep = new Sleeper();
            sleep.sleepFor((long) (sleepTimer * 1000));
        }
        this.sprites.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText((d.getWidth() / 2) - 13, d.getHeight() - 248, String.valueOf(currentCount), 45);
        d.setColor(Color.BLACK);
        d.drawText((d.getWidth() / 2) - 15, d.getHeight() - 250, String.valueOf(currentCount), 45);
        this.currentCount--;
    }

    @Override
    public boolean shouldStop() {

        /*
         * the game will doOneFrame if the count will be 0
         * but will not have enough time to show 0,
         * therefore -> stop if the current count reach -1.
         */
        return this.currentCount == -1;
    }
}

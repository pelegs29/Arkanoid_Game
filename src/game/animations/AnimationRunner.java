//318509700
package game.animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * This class runs the animation with a GUI object given.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Constructor which sets the gui and initialize tje FPS and sleeper.
     *
     * @param givenGui the given GUI to make the animation on.
     */
    public AnimationRunner(GUI givenGui) {
        this.gui = givenGui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * this method is in charge of running the animation given
     * using the doOneFrame method of the animation given.
     * with the same logic from looping code in the last assigment.
     *
     * @param animation the Animation object given to be animated.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
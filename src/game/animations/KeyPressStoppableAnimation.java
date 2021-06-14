//318509700
package game.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class is a decorator for Animation which
 * adds the functionality of stopping with a specific keystroke.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String keyStroke;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor which sets the given args
     * and initialize the stop condition to false and isAlreadyPressed to true.
     *
     * @param sensor    the given KeyboardSensor of the game.
     * @param key       the given KeyStroke to stop the animation.
     * @param animation the given animation to decorate.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.keyStroke = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        //if the KeyStore is not pressed right now set isAlreadyPressed to false.
        if (!this.keyboardSensor.isPressed(keyStroke)) {
            this.isAlreadyPressed = false;
        }

        //stop only if the KeyStroke is pressed and has not been pressed before.
        if (this.keyboardSensor.isPressed(keyStroke) && !isAlreadyPressed) {
            this.stop = true;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
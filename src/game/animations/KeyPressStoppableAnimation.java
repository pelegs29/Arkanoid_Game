package game.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String keyStroke;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.keyStroke = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.keyboardSensor.isPressed(keyStroke)) {
            this.isAlreadyPressed = false;
        }
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

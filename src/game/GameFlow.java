package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animations.AnimationRunner;
import game.animations.EndScreen;
import game.animations.KeyPressStoppableAnimation;
import game.animations.WelcomeScreen;
import game.elements.Counter;
import game.levels.LevelInformation;

import java.util.List;

public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gameGui;
    private Counter score;

    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gg) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gameGui = gg;
    }

    public void runLevels(List<LevelInformation> levels) {
        this.score = new Counter();
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new WelcomeScreen()));
        boolean victoryFlag = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.gameGui, this.score);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.isGameOver()) {
                victoryFlag = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new EndScreen(victoryFlag, this.score)));
        this.gameGui.close();
    }
}
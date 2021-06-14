//318509700
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

/**
 * This class will handle the flow of the game.
 * it will move from each level to the next according to its args.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gameGui;

    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;

    /**
     * Main Constructor.
     *
     * @param ar given AnimationRunner.
     * @param ks given KeyboardSensor.
     * @param gg given Game.GUI.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gg) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gameGui = gg;
    }

    /**
     * This method is in charge of running the levels
     * in the game back to back according to the order of the levels list given.
     *
     * @param levels given level list to run in order.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter();
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new WelcomeScreen()));
        boolean victoryFlag = true;

        //loop to run all the levels given.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.gameGui, score);

            level.initialize();

            //run each level until the level should stop according to its shouldStop method.
            while (!level.shouldStop()) {
                level.run();
            }

            //stop the game is no more balls are left.
            if (level.isGameOver()) {
                victoryFlag = false;
                break;
            }
        }

        //run endScreen animation according to the winning/losing state.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new EndScreen(victoryFlag, score)));
        this.gameGui.close();
    }
}
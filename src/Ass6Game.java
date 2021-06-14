//318509700

import biuoop.GUI;
import game.GameFlow;
import game.animations.AnimationRunner;
import game.levels.LevelInformation;
import game.levels.LevelsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * This program create a game object,
 * initializes and runs it.
 *
 * @author Peleg Shlomo
 * @version 1.5
 * @since 1.0
 */
public class Ass6Game {
    /**
     * This is the main method which makes use of the Game class methods.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        LevelsBuilder builder = new LevelsBuilder(args);
        List<LevelInformation> levelsList = new ArrayList<>(builder.buildLevels());
        GUI gameGui = new GUI("Arkanoid Game", GameFlow.GUI_WIDTH, GameFlow.GUI_HEIGHT);
        GameFlow game = new GameFlow(new AnimationRunner(gameGui), gameGui.getKeyboardSensor(), gameGui);
        game.runLevels(levelsList);
    }
}
//318509700

import biuoop.GUI;
import game.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This program create a game object,
 * initializes and runs it.
 *
 * @author Peleg Shlomo
 * @version 1.2
 * @since 1.0
 */
public class Ass6Game {
    /**
     * This is the main method which makes use of the Game class methods.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels_list = new ArrayList<>();
        LevelInformation level_1 = new FirstLevel();
        LevelInformation level_2 = new SecondLevel();
        LevelInformation level_3 = new ThirdLevel();
        LevelInformation level_4 = new ForthLevel();
        levels_list.add(level_1);
        levels_list.add(level_2);
        //levels_list.add(level_3);
        //levels_list.add(level_4);
        GUI gameGui = new GUI("Arkanoid Game", GameFlow.GUI_WIDTH, GameFlow.GUI_HEIGHT);
        GameFlow game = new GameFlow(new AnimationRunner(gameGui), gameGui.getKeyboardSensor(), gameGui);
        game.runLevels(levels_list);
    }
}

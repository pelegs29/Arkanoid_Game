//318509700

import game.*;

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
        LevelInformation level_1 = new FirstLevel();
        LevelInformation level_2 = new SecondLevel();
        LevelInformation level_3 = new ThirdLevel();
        LevelInformation level_4 = new ForthLevel();
        GameLevel game = new GameLevel(level_4);
        game.initialize();
        game.run();
    }
}

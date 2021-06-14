//318509700
package game.levels;

import game.elements.Block;
import game.sprites.Sprite;
import geometry.Velocity;

import java.util.List;

/**
 * This interface handles the game.LevelInformation methods.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public interface LevelInformation {

    /**
     * @return the number of balls in the start of the level.
     */
    int numberOfBalls();

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * @return list of velocities for each ball in the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed in the level.
     */
    int paddleSpeed();

    /**
     * @return the paddle width in the level.
     */
    int paddleWidth();

    /**
     * @return the name of the level.
     */
    String levelName();

    /**
     * @return the sprite of the background
     */
    Sprite getBackground();

    /**
     * @return a list of all the block in the level
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
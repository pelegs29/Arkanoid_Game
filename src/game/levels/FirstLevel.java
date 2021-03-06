//318509700
package game.levels;

import game.GameFlow;
import game.elements.Ball;
import game.elements.Block;
import game.sprites.Sprite;
import geometry.Point;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all of the information of the level.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class FirstLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(0, Velocity.generateSpeed(Ball.DEFAULT_SIZE));
        velocityList.add(velocity);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Sniper";
    }

    @Override
    public Sprite getBackground() {
        return new FirstLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        Block block = new Block(new Point((GameFlow.GUI_WIDTH / 2.0) - 15, 150),
                30, 30);
        block.setColor(Color.RED);
        blocksList.add(block);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
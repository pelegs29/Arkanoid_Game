//318509700
package game.levels;

import game.GameFlow;
import game.GameLevel;
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
public class ThirdLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 50; i <= this.numberOfBalls() * 50; i = i + 50) {
            Velocity velocity = Velocity.fromAngleAndSpeed(285 + i,
                    Velocity.generateSpeed(Ball.DEFAULT_SIZE));
            velocityList.add(velocity);
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 11;
    }

    @Override
    public int paddleWidth() {
        return 110;
    }

    @Override
    public String levelName() {
        return "Green City";
    }

    @Override
    public Sprite getBackground() {
        return new ThirdLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Color[] colorsArr;
            colorsArr = new Color[]{Color.RED, Color.GREEN, Color.ORANGE,
                    Color.BLUE, Color.MAGENTA, Color.PINK};

            //loop that create the blocks.
            for (int j = 3 + i; j > 0; j--) {
                double calculateStartX = GameFlow.GUI_WIDTH - (GameLevel.BORDER_WIDTH + Block.DEFAULT_WIDTH * 7);
                double newXVal = calculateStartX + (Block.DEFAULT_WIDTH * (7 - j));
                double newYVal = 250 + (-1 * Block.DEFAULT_HEIGHT * i);
                Block block = new Block(new Point(newXVal, newYVal), Block.DEFAULT_WIDTH, Block.DEFAULT_HEIGHT);
                block.setColor(colorsArr[i]);
                blocksList.add(block);
            }
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
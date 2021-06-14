//318509700
package game.levels;

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
public class SecondLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 5; i <= this.numberOfBalls() * 5; i = i + 5) {
            Velocity velocity = Velocity.fromAngleAndSpeed(330 + i,
                    Velocity.generateSpeed(Ball.DEFAULT_SIZE));
            velocityList.add(velocity);
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide and Shine";
    }

    @Override
    public Sprite getBackground() {
        return new SecondLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        int numberOfBlocks = 10;
        Color[] colorsArr;
        colorsArr = new Color[]{Color.RED, Color.GREEN, Color.ORANGE,
                Color.BLUE, Color.MAGENTA, Color.PINK};
        List<Block> blocksList = new ArrayList<>();

        //loop to create the blocks.
        for (int i = 0, j = 0; i < numberOfBlocks; i++) {
            int width = Block.DEFAULT_WIDTH;
            int newXVal = GameLevel.BORDER_WIDTH + (i * width);
            Block block = new Block(new Point(newXVal, 250),
                    width, Block.DEFAULT_HEIGHT);
            block.setColor(colorsArr[j]);

            //switch color every 2 blocks.
            if (i % 2 == 0) {
                j++;
            }
            blocksList.add(block);
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
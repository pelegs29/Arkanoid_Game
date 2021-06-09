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

public class ForthLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 50; i <= this.numberOfBalls() * 50; i = i + 50) {
            Velocity velocity = Velocity.fromAngleAndSpeed(260 + i,
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
        return "Fantastic Four";
    }

    @Override
    public Sprite getBackground() {
        return new ForthLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Color[] colorsArr;
            colorsArr = new Color[]{Color.RED, Color.GREEN, Color.ORANGE,
                    Color.BLUE, Color.MAGENTA, Color.WHITE, Color.PINK};
            for (int j = 0; j < 10; j++) {
                int newXVal = GameLevel.BORDER_WIDTH + (j * Block.DEFAULT_WIDTH);
                int newYVal = 100 + (Block.DEFAULT_HEIGHT * i);
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
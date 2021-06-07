package game;

import game.elements.Ball;
import game.elements.Block;
import game.sprites.Sprite;
import geometry.Point;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Green City";
    }

    @Override
    public Sprite getBackground() {
        Block background = new Block(new Point(0, 0),
                GameFlow.GUI_WIDTH * 2, GameFlow.GUI_HEIGHT * 2);
        background.setColor(new Color(19, 121, 24));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Color[] colorsArr;
            colorsArr = new Color[]{Color.RED, Color.GREEN, Color.ORANGE,
                    Color.BLUE, Color.MAGENTA, Color.PINK};
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
package game;

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
        for (int i = 20; i <= this.numberOfBalls() * 20; i = i + 20) {
            Velocity velocity = Velocity.fromAngleAndSpeed(320 + i,
                    Velocity.generateSpeed(Ball.DEFAULT_SIZE));
            velocityList.add(velocity);
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Fantastic Four";
    }

    @Override
    public Sprite getBackground() {
        Block background = new Block(new Point(0, 0),
                GameFlow.GUI_WIDTH * 2, GameFlow.GUI_HEIGHT * 2);
        background.setColor(new Color(30, 114, 206));
        return background;
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
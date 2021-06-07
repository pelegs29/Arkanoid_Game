package game;

import game.elements.Ball;
import game.elements.Block;
import game.sprites.Sprite;
import geometry.Point;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SecondLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 10; i <= this.numberOfBalls() * 10; i = i + 10) {
            Velocity velocity = Velocity.fromAngleAndSpeed(310 + i,
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
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide and Shine";
    }

    @Override
    public Sprite getBackground() {
        Block background = new Block(new Point(0, 0),
                GameLevel.GUI_WIDTH * 2, GameLevel.GUI_HEIGHT * 2);
        background.setColor(Color.WHITE);
        return background;
    }

    @Override
    public List<Block> blocks() {
        Color[] colorsArr;
        colorsArr = new Color[]{Color.RED, Color.GREEN, Color.ORANGE,
                Color.BLUE, Color.MAGENTA, Color.PINK};
        List<Block> blocksList = new ArrayList<>();
        for (int i = 0, j = 0; i < 8; i++) {
            int width = Block.DEFAULT_WIDTH + 19;
            int newXVal = GameLevel.BORDER_WIDTH + (i * width);
            Block block = new Block(new Point(newXVal, 150),
                    width, Block.DEFAULT_HEIGHT);
            block.setColor(colorsArr[j]);
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

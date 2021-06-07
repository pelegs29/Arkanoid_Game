package game;

import game.elements.Ball;
import game.elements.Block;
import game.sprites.Sprite;
import geometry.Point;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
        Block background = new Block(new Point(0, 0),
                GameFlow.GUI_WIDTH * 2, GameFlow.GUI_HEIGHT * 2);
        background.setColor(new Color(30, 30, 33));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        Block block = new Block(new Point((GameFlow.GUI_WIDTH / 2.0) - 10, 150),
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

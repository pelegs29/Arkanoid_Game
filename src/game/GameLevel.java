//318509700
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animations.Animation;
import game.animations.AnimationRunner;
import game.animations.CountdownAnimation;
import game.animations.KeyPressStoppableAnimation;
import game.animations.PauseScreen;
import game.collidables.Collidable;
import game.elements.Ball;
import game.elements.Block;
import game.elements.Counter;
import game.elements.LevelIndicator;
import game.elements.Paddle;
import game.elements.ScoreIndicator;
import game.levels.LevelInformation;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.HitListener;
import game.listeners.ScoreTrackingListener;
import game.sprites.Sprite;
import game.sprites.SpriteCollection;
import geometry.Point;
import geometry.Velocity;

import java.util.List;

/**
 * This class handles all the methods related to
 * the object game.Game.
 *
 * @author Peleg Shlomo
 * @version 1.8
 * @since 1.0
 */
public class GameLevel implements Animation {

    //sprites field is final until a setter will be needed.
    private final SpriteCollection sprites;
    private game.GameEnvironment environment;
    private biuoop.GUI gui;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
    private boolean running;
    private boolean lost;
    private Counter blocksLeft;
    private Counter ballsLeft;
    private final Counter score;

    //Class Constants
    public static final int BORDER_WIDTH = 20;

    /**
     * Main Constructor.
     * creates a game level and setting up all of the class field given.
     * in addition to setting the lost flag to false and running flag to true.
     *
     * @param level   given LevelInformation.
     * @param sensor  given KeyboardSensor.
     * @param runner  given AnimationRunner.
     * @param gameGui given Game.GUI.
     * @param score   given score Counter.
     */
    public GameLevel(LevelInformation level, KeyboardSensor sensor, AnimationRunner runner,
                     GUI gameGui, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new game.GameEnvironment();
        this.levelInformation = level;
        this.keyboard = sensor;
        this.runner = runner;
        this.gui = gameGui;
        this.score = score;
        this.lost = false;
        this.running = true;
    }

    /**
     * Add given Collidable to the gameEnvironment's game.collidables list.
     *
     * @param c given Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add given Sprite to the SpriteCollection's game.sprites list.
     *
     * @param s given Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes given Collidable from the gameEnvironment's game.collidables list.
     *
     * @param c given Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes given Sprite from the SpriteCollection's game.sprites list.
     *
     * @param s given Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method Initializes a new game by creating the GUI,
     * Blocks, Ball and Paddle and adds them to the game.Game.
     */
    public void initialize() {
        this.blocksLeft = new Counter(this.levelInformation.numberOfBlocksToRemove());
        this.ballsLeft = new Counter();

        BlockRemover blockRemover = new BlockRemover(this, this.blocksLeft);
        BallRemover ballRemover = new BallRemover(this, this.ballsLeft);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        this.addSprite(this.levelInformation.getBackground());

        /*
         * setting the environments which creates the background and border
         * blocks in the constructor and adding them to the SpriteCollection.
         */
        this.setEnvironment(new game.GameEnvironment(BORDER_WIDTH));
        for (int i = 0; i < this.environment.getCollidablesList().size(); i++) {
            addSprite((Block) this.environment.getCollidablesList().get(i));
        }

        //creates the blocks, the "death block" and the balls.
        createBlocksFromList(this.levelInformation.blocks(), blockRemover, scoreListener);
        createDeathBlock(ballRemover);
        createBallsFromList(this.levelInformation.initialBallVelocities());

        Paddle paddle = new Paddle(this.levelInformation.paddleSpeed(),
                this.levelInformation.paddleWidth(), keyboard, this.environment);
        paddle.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreListener);
        scoreIndicator.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(this.levelInformation.levelName());
        levelIndicator.addToGame(this);
    }

    /**
     * This method should only run after the initialize() method !
     * this method running the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (getGUI() == null) {
            this.running = false;
        }

        //if no balls are left set the lost flag to true and the running flag to false.
        if (this.ballsLeft.getValue() == 0) {
            this.running = false;
            this.lost = true;

            // if there are no blocks left add 100 points to the counter and set the running flag to false.
        } else if (this.blocksLeft.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }

        //stop if 'p' keyboard stroke is pressed.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return the lost flag of the game.
     */
    public boolean isGameOver() {
        return this.lost;
    }

    /**
     * @param gameEnvironment new game.Game's game.GameEnvironment;
     */
    public void setEnvironment(game.GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * @param givenGui new game.Game's GUI.
     */
    public void setGui(biuoop.GUI givenGui) {
        this.gui = givenGui;
    }

    /**
     * @return game.Game's GUI.
     */
    public biuoop.GUI getGUI() {
        return this.gui;
    }

    /**
     * This method creates the balls in the level
     * according to the list of balls velocity given by the LevelInformation.
     *
     * @param velocityList given list of velocities of the balls.
     */
    private void createBallsFromList(List<Velocity> velocityList) {
        for (Velocity velocity : velocityList) {
            Ball ball = new Ball(Ball.DEFAULT_SIZE, velocity);
            ball.setGameEnvironment(this.environment);
            this.ballsLeft.increaseBy1();
            ball.addToGame(this);
        }
    }

    /**
     * This method creates the block in the level according to the block list and the listeners given.
     *
     * @param blockList     the given list of blocks in the level.
     * @param remover       the remover listener.
     * @param scoreListener the score listener.
     */
    private void createBlocksFromList(List<Block> blockList, HitListener remover, HitListener scoreListener) {
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(remover);
            block.addHitListener(scoreListener);
        }
    }

    /**
     * This method creates the "Death" block which removes the ball if the balls hit it.
     *
     * @param ballRemover given ballRemover hitListener.
     */
    private void createDeathBlock(HitListener ballRemover) {
        Block deathBlock = new Block(new Point(BORDER_WIDTH, GameFlow.GUI_HEIGHT + (Ball.DEFAULT_SIZE)),
                GameFlow.GUI_WIDTH, (GameFlow.GUI_WIDTH - GameFlow.GUI_HEIGHT));
        deathBlock.addToCollidables(this);
        deathBlock.addHitListener(ballRemover);
    }
}
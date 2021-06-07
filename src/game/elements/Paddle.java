//318509700
package game.elements;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.GameLevel;
import game.GameEnvironment;
import game.collidables.Collidable;
import game.sprites.Sprite;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all the methods related to
 * the object Paddle.
 *
 * @author Peleg Shlomo
 * @version 1.1
 * @since 1.0
 */
public class Paddle implements Sprite, Collidable {

    private Rectangle paddle;
    private final int paddleDrift;
    private final biuoop.KeyboardSensor keyboard;
    private final GameEnvironment gameEnvironment;

    //Class Constants
    public static final int PADDLE_HEIGHT = 20;
    private static final int PADDLE_NUM_OF_PARTS = 5;
    private static final int FIRST_SECTION = 0;
    private static final int SECOND_SECTION = 1;
    private static final int THIRD_SECTION = 2;
    private static final int FORTH_SECTION = 3;

    /**
     * Main Constructor 1.
     * calculates this paddle spawn point and assign the keyboardSensor and game.GameEnvironment given.
     *
     * @param keyboardSensor given KeyboardSensor keyboard sensor.
     * @param environment    given game.GameEnvironment environment.
     */
    public Paddle(int paddleSpeed, int paddleWidth, biuoop.KeyboardSensor keyboardSensor, GameEnvironment environment) {
        this.paddleDrift = paddleSpeed;
        this.keyboard = keyboardSensor;
        this.gameEnvironment = environment;
        double paddleXvalue = (GameFlow.GUI_WIDTH / 2.0) - (paddleWidth / 2.0);
        double paddleYvalue = GameFlow.GUI_HEIGHT - (GameLevel.BORDER_WIDTH + PADDLE_HEIGHT);
        this.paddle = new Rectangle(new Point(paddleXvalue, paddleYvalue), paddleWidth, PADDLE_HEIGHT);
    }

    /**
     * This method moves the paddle to the left one time according
     * to the class PADDLE_DRIFT constant and the paddle position in the gui.
     */
    public void moveLeft() {

        //checks if the paddle is not at the far left of the gui.
        if (this.paddle.getUpperLeft().getX()
                != (gameEnvironment.getLeftSide().getCollisionRectangle().getLowerRight().getX())) {
            double newXVal = this.paddle.getUpperLeft().getX() - this.paddleDrift;
            if (newXVal <= GameLevel.BORDER_WIDTH) {
                newXVal = GameLevel.BORDER_WIDTH;
            }
            double yVal = this.paddle.getUpperLeft().getY();
            int width = (int) this.paddle.getWidth();
            this.paddle = new Rectangle(new Point(newXVal, yVal), width, PADDLE_HEIGHT);
        }
    }

    /**
     * This method moves the paddle to the right one time according
     * to the class PADDLE_DRIFT constant and the paddle position in the gui.
     */
    public void moveRight() {

        //checks if the paddle is not at the far right of the gui.
        if (this.paddle.getUpperRight().getX()
                != (gameEnvironment.getRightSide().getCollisionRectangle().getLowerLeft().getX())) {
            double newXVal = this.paddle.getUpperLeft().getX() + this.paddleDrift;
            int width = (int) this.paddle.getWidth();
            if (newXVal + width >= GameFlow.GUI_WIDTH - GameLevel.BORDER_WIDTH) {
                newXVal =(GameFlow.GUI_WIDTH - GameLevel.BORDER_WIDTH - width);
            }
            double yVal = this.paddle.getUpperLeft().getY();
            this.paddle = new Rectangle(new Point(newXVal, yVal), width, PADDLE_HEIGHT);
        }
    }


    /**
     * This methods splits the paddle to five even points.
     *
     * @return List of division points.
     */
    private List<Point> createPaddleDivision() {
        List<Point> pointsOfDivision = new ArrayList<>();

        //Splits the paddle to 5 even sections : [---0---1---2---3---]
        double division = this.paddle.getWidth() / (double) PADDLE_NUM_OF_PARTS;
        for (int i = 1; i < PADDLE_NUM_OF_PARTS; i++) {
            Point p = new Point(this.paddle.getUpperLeft().getX() + (division * i),
                    this.paddle.getUpperLeft().getX());
            pointsOfDivision.add(p);
        }
        return pointsOfDivision;
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            //System.out.println("the 'right arrow' key is pressed");
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            //System.out.println("the 'left arrow' key is pressed");
            moveLeft();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //create the paddle division.
        List<Point> divisionPoints = createPaddleDivision();
        Velocity newVelocity = currentVelocity;
        double ballGeneratedSpeed = Velocity.generateSpeed(Ball.DEFAULT_SIZE);

        //checks if the collision point occurred in the first section.
        if (collisionPoint.getX() <= divisionPoints.get(FIRST_SECTION).getX()) {
            newVelocity = Velocity.fromAngleAndSpeed(-60, ballGeneratedSpeed);

            //checks if the collision point occurred in the second section.
        } else if (collisionPoint.isBetween(divisionPoints.get(FIRST_SECTION), divisionPoints.get(SECOND_SECTION))
                || collisionPoint.getX() == divisionPoints.get(SECOND_SECTION).getX()) {
            newVelocity = Velocity.fromAngleAndSpeed(330, ballGeneratedSpeed);

            //checks if the collision point occurred in the third section.
        } else if (collisionPoint.isBetween(divisionPoints.get(SECOND_SECTION), divisionPoints.get(THIRD_SECTION))
                || collisionPoint.getX() == divisionPoints.get(THIRD_SECTION).getX()) {
            newVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());

            //checks if the collision point occurred in the forth section.
        } else if (collisionPoint.isBetween(divisionPoints.get(THIRD_SECTION), divisionPoints.get(FORTH_SECTION))
                || collisionPoint.getX() == divisionPoints.get(FORTH_SECTION).getX()) {
            newVelocity = Velocity.fromAngleAndSpeed(30, ballGeneratedSpeed);

            //checks if the collision point occurred in the fifth section.
        } else if (collisionPoint.getX() >= divisionPoints.get(FORTH_SECTION).getX()) {
            newVelocity = Velocity.fromAngleAndSpeed(60, ballGeneratedSpeed);
        }
        return newVelocity;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}
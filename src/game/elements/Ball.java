// ID 318509700
package game.elements;

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import game.collidables.CollisionInfo;
import game.sprites.Sprite;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;

import java.awt.*;
import java.util.Random;

/**
 * This class handles all the methods related to
 * the object game.game.elements.Ball.
 *
 * @author Peleg Shlomo
 * @version 1.6
 * @since 1.0
 */
public class Ball implements Sprite {

    //size and color are final until a setter will be needed.
    private final int size;
    private final Color color;
    private Point point;
    private Velocity velocity;
    private GameEnvironment environment;

    //Class Constants
    public static final int DEFAULT_SIZE = 7;

    /**
     * Main Constructor 1.
     * Sets default velocity with given arguments.
     *
     * @param center ball's center point.
     * @param r      ball's radius(size).
     * @param color  ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.color = color;
        this.point = center;
        this.size = r;
        this.velocity = new Velocity();
    }

    /**
     * Main Constructor 2.
     * Sets random color, random velocity and default point for the ball.
     *
     * @param size ball's radius(size).
     */
    public Ball(int size) {
        this.size = size;
        this.color = getRandColor();
        this.point = new Point(400, 530);
        this.velocity = Velocity.fromAngleAndSpeed(getRandAngle(), Velocity.generateSpeed(size));
    }

    /**
     * Main Constructor 3.
     * Sets random color, given velocity and default point for the ball.
     *
     * @param size     ball's radius(size).
     * @param velocity ball's given velocity.
     */
    public Ball(int size, Velocity velocity) {
        this.size = size;
        this.color = Color.WHITE;
        this.point = new Point(400, 530);
        this.velocity = velocity;
    }

    /**
     * This method is generation random color from array of chosen colors.
     *
     * @return random Color object.
     */
    public Color getRandColor() {
        Color[] colorsArr;
        colorsArr = new Color[]{Color.BLACK, Color.RED, Color.GREEN, Color.ORANGE,
                Color.BLUE, Color.MAGENTA, Color.PINK, Color.DARK_GRAY};
        Random rand = new Random();
        int randNum = rand.nextInt(colorsArr.length);
        return colorsArr[randNum];
    }

    /**
     * This method is generation random angle from 20 to 160.
     * inorder to avoid balls with angle too steep.
     *
     * @return random angle.
     */
    public static double getRandAngle() {
        int min = 290;
        int max = 430;
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);

        //create ball only if he is smaller than the gui size.
        if (!(this.size * 2 > Math.min(GameLevel.GUI_HEIGHT, GameLevel.GUI_WIDTH))) {
            surface.fillCircle(this.getX(), this.getY(), this.size);
            surface.setColor(Color.BLACK);
            surface.drawCircle(this.getX(), this.getY(), this.size);
        }
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * This method places the ball in a new point,
     * according to the ball trajectory and game environment's method to check
     * collision with collidable objects.
     */
    public void moveOneStep() {
        //compute the ball trajectory using the ball's velocity.
        Point nextPoint = this.getVelocity().applyToPoint(this.point);
        Line trajectory = new Line(nextPoint, this.getVelocity().applyToPoint(nextPoint));

        //if a collision found notify the hit object, update the velocity and move the ball to the new point.
        CollisionInfo nextHit = this.environment.getClosestCollision(trajectory);
        if (nextHit != null) {
            setVelocity(nextHit.collisionObject().hit(this, nextHit.collisionPoint(), this.velocity));
            this.point = this.velocity.applyToPoint(this.point);

            //check if a second hit is going to occur right away using newly computed ball trajectory.
            Point secondNextPoint = this.getVelocity().applyThreeTimesToPoint(this.point);
            Line secondTrajectory = new Line(nextPoint, this.getVelocity().applyToPoint(secondNextPoint));
            CollisionInfo secondNextHit = this.environment.getClosestCollision(secondTrajectory);

            //if a second collision found notify the hit object, update the velocity and move the ball.
            if (secondNextHit != null) {
                setVelocity(secondNextHit.collisionObject().hit(this, secondNextHit.collisionPoint(), this.velocity));
                this.point = this.velocity.applyToPoint(this.point);
            }

            //if no collision found move the ball according to it's velocity.
        } else {
            this.point = this.velocity.applyToPoint(this.point);
        }
    }

    /**
     * @param v new inputted Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx new geometry.Velocity's dx value.
     * @param dy new geometry.Velocity's dy value.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @param p new inputted Point.
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * @param givenEnvironment new given environment.
     */
    public void setGameEnvironment(GameEnvironment givenEnvironment) {
        this.environment = givenEnvironment;
    }

    /**
     * @return ball's Velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @return integer value of x value.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * @return integer value of y value.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * @return ball's radius(size).
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return ball's color.
     */
    public Color getColor() {
        return this.color;
    }
}
//318509700
package geometry;

/**
 * This class handles all the methods related to
 * the object Velocity, Velocity specifies the
 * change in position on the `x` and the `y` axes.
 *
 * @author Peleg Shlomo
 * @version 1.3
 * @since 1.0
 */
public class Velocity {

    //the fields are final until setters will be needed.
    private final double dx;
    private final double dy;

    //Class constants
    public static final double SPEED_FACTOR = 45.0;
    public static final int MAX_SIZE_SPEED = 50;

    /**
     * Sole Constructor
     * setting default values for dx and dy.
     */
    public Velocity() {
        this.dx = 5;
        this.dy = 5;
    }

    /**
     * Main Constructor 1.
     *
     * @param dx velocity's dx value.
     * @param dy velocity's dy value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Main Constructor 2.
     * Note : in order to fix position 90 degree has deducted.
     * (zero degree is up)
     *
     * @param angle velocity's angle given.
     * @param speed velocity's speed given.
     * @return new calculated Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }

    /**
     * This method is applying the new position of a point according to the velocity
     * position (x,y) will be -> (x+dx, y+dy).
     *
     * @param p Point given.
     * @return new Point with the new position.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * This method is applying the new position of a point according to the velocity three times'
     * position (x,y) will be -> (x+(3*dx), y+(3*dy)).
     *
     * @param p Point given.
     * @return new Point with the new position.
     */
    public Point applyThreeTimesToPoint(Point p) {
        return new Point(p.getX() + (3 * this.dx), p.getY() + (3 * this.dy));
    }

    /**
     * This method generate speed according to the ball size
     * Note : all the balls with size >= 50 have the same speed.
     *
     * @param ballSize ball's speed.
     * @return ball's speed.
     */
    public static double generateSpeed(int ballSize) {
        if (ballSize < MAX_SIZE_SPEED) {
            return (1.0 / ballSize * SPEED_FACTOR);
        } else {
            return (1.0 / MAX_SIZE_SPEED * SPEED_FACTOR);
        }
    }

    /**
     * @return velocity's dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return velocity's dy value.
     */
    public double getDy() {
        return this.dy;
    }
}
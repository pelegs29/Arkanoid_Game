//318509700
package geometry;

/**
 * This class handles all the methods related to
 * the object Point.
 *
 * @author Peleg Shlomo
 * @version 1.1
 * @since 1.0
 */
public class Point {

    //the fields are final until setters will be needed.
    private final double x;
    private final double y;

    /**
     * Main Constructor.
     *
     * @param x Point's x value.
     * @param y Point's y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method calculates the distance
     * between this point and the other point.
     *
     * @param other the other line.
     * @return the calculated distance.
     */
    public double distance(Point other) {
        double xDiff = this.x - other.x;
        double yDiff = this.y - other.y;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    /**
     * This method checks if two Points are equal,
     * Note: Algorithm is based on (www.shorturl.at/zGKW4).
     *
     * @param other other Point.
     * @return true if equal and false otherwise.
     */
    public boolean equals(Point other) {
        double errorMargin = Math.pow(10, -15);
        if ((Math.abs(other.getX() - this.getX()) < errorMargin)
                && ((Math.abs(other.getY() - this.getY()) < errorMargin))) {
            return true;
        }
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    /**
     * This method check if this point is between point a and point b.
     *
     * @param a Point a.
     * @param b Point b.
     * @return true if this point is between
     * point a and b , false otherwise.
     */
    public boolean isBetween(Point a, Point b) {
        return (((Math.min(a.y, b.y) < this.y) && (this.y < Math.max(a.y, b.y)))
                || ((Math.min(a.x, b.x) < this.x) && (this.x < Math.max(a.x, b.x))));
    }

    /**
     * @return Point's x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return Point's y value.
     */
    public double getY() {
        return this.y;
    }
}
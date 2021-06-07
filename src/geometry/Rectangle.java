//318509700
package geometry;

import game.elements.Ball;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all the methods related to
 * the object Rectangle.
 *
 * @author Peleg Shlomo
 * @version 1.3
 * @since 1.0
 */
public class Rectangle {

    //the fields are final until setters will be needed.
    private final Point upperLeft;
    private final Point upperRight;
    private final Point lowerLeft;
    private final Point lowerRight;

    //Class constants
    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Sole Constructor.
     * setting default rectangle's width & default height .
     */
    public Rectangle() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Main Constructor 1.
     * sets the upper right point to default (0,0)
     * and the rest of the points accordingly to the width and height given.
     *
     * @param width  rectangle's width.
     * @param height rectangle's height.
     */
    public Rectangle(int width, int height) {
        this.upperLeft = new Point(0, 0);
        this.upperRight = new Point(width, 0);
        this.lowerLeft = new Point(0, height);
        this.lowerRight = new Point(width, height);
    }

    /**
     * Main Constructor 2.
     * sets and upper right and the lower left points according to the
     * upper left and the lower right points.
     *
     * @param a upper left point of the rectangle.
     * @param d lower right point of the rectangle.
     */
    public Rectangle(Point a, Point d) {
        this.upperLeft = a;
        this.upperRight = new Point(d.getX(), a.getY());
        this.lowerLeft = new Point(a.getX(), d.getY());
        this.lowerRight = d;
    }

    /**
     * Main Constructor 3.
     * using the upper left, width and height given
     * calculates all the rest of the points.
     *
     * @param upperLeft upper left point of the rectangle.
     * @param width     rectangle's width.
     * @param height    rectangle's height.
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * This method calculates all the intersection points of
     * this rectangle with a given line.
     *
     * @param line the given line.
     * @return List of intersection points (can be empty if no intersection are found).
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionList = new ArrayList<>();
        for (Line side
                : this.getSides()) {
            Point intersection = side.intersectionWith(line);
            if (intersection != null) {
                intersectionList.add(intersection);
            }
        }
        return intersectionList;
    }

    /**
     * This method generate valid random point for a given ball.
     *
     * @param ballSize given ball's radius(size).
     * @return Valid Point for the given ball.
     */
    public Point randInWindow(int ballSize) {
        int diameter = 2 * ballSize;
        double maxX = this.lowerRight.getX() - diameter;
        double minX = this.lowerLeft.getX() + diameter;
        double maxY = this.lowerLeft.getY() - diameter;
        double minY = this.upperLeft.getY() + diameter;

        //check if the ball diameter is too big for the rectangle, if it does throw RuntimeException
        if (diameter > this.getWidth() || diameter > this.getHeight()) {
            //throw new RuntimeException("game.game.elements.Ball Size is too big to fit the window");
            return getCenter(this.getUpperLeft());

            //check if part of the ball is outside the rectangle, if it does center the ball in the rectangle.
        } else if ((maxY <= diameter || maxX <= diameter) || (minX > maxX || minY > maxY)) {
            return this.getCenter(this.upperLeft);
        }
        int x = (int) Math.floor(Math.random() * (maxX - minX + 1) + minX);
        int y = (int) Math.floor(Math.random() * (maxY - minY + 1) + minY);
        return new Point(x, y);
    }

    /**
     * This method calculates the center point of the rectangle.
     *
     * @param upLeft upper left point of the rectangle.
     * @return center Point of the rectangle.
     */
    public Point getCenter(Point upLeft) {
        double x = upLeft.getX() + (this.getWidth() / 2);
        double y = upLeft.getY() + (this.getHeight() / 2);
        return new Point(x, y);
    }

    /**
     * This method check if the given ball is inside the rectangle borders.
     *
     * @param b given ball.
     * @return true - if the ball is inside the rectangle,
     * false - if the ball is outside the rectangle.
     */
    public boolean isInRectangle(Ball b) {
        return ((this.getWidth() - b.getSize() >= b.getX() && b.getX() >= b.getSize())
                && (this.getHeight() - b.getSize() >= b.getY() && b.getY() >= b.getSize()));
    }

    /**
     * This method check if the given point is inside the rectangle borders.
     * using the Rectangle build in java class and its contain method.
     *
     * @param p given Point.
     * @return true - if the point is inside the rectangle,
     * false - if the point is outside the rectangle.
     */
    public boolean isInWindow(Point p) {
        return this.containsPoint(p);
    }

    /**
     * This method finds the minimum rectangle.
     *
     * @param other the second rectangle to check
     * @return minimum Rectangle.
     */
    public Rectangle findMin(Rectangle other) {
        double areaRectangle1 = this.getWidth() * this.getHeight();
        double areaRectangle2 = other.getWidth() * other.getHeight();
        if (areaRectangle1 <= areaRectangle2) {
            return this;
        } else {
            return other;
        }
    }

    /**
     * This method returns the "union" of the rectangles,
     * if one rectangle if partly outside the window this method will
     * return a new rectangle with the union of the window and the rectangle.
     *
     * @param other second rectangle.
     * @return new union of the rectangles.
     */
    public Rectangle newMinRectangle(Rectangle other) {

        //check if this rectangle contains other rectangle's lower right point
        if (this.isInWindow(other.lowerRight)) {
            return containsLowerRight(other);

            //check if this rectangle contains other rectangle's upper left point
        } else if (this.isInWindow(other.upperLeft)) {
            return containsUpperLeft(other);

            //check if this rectangle contains other rectangle's lower left point
        } else if (this.isInWindow(other.lowerLeft)) {
            Line otherLeft = new Line(other.upperLeft, other.lowerLeft);
            Line thisUpper = new Line(this.upperLeft, this.upperRight);
            Point a = otherLeft.intersectionWith(thisUpper);
            Line otherLower = new Line(other.lowerLeft, other.lowerRight);
            Line thisRight = new Line(this.upperRight, this.lowerRight);
            Point d = otherLower.intersectionWith(thisRight);
            return new Rectangle(a, d);

            //check if this rectangle contains other rectangle's upper right point
        } else if (this.isInWindow(other.upperRight)) {
            Line otherUpper = new Line(other.upperLeft, other.upperRight);
            Line thisLeft = new Line(this.upperLeft, this.lowerLeft);
            Point a = otherUpper.intersectionWith(thisLeft);
            Line otherRight = new Line(other.upperRight, other.lowerRight);
            Line thisLower = new Line(this.lowerLeft, this.lowerRight);
            Point d = otherRight.intersectionWith(thisLower);
            return new Rectangle(a, d);
        }
        /*
         * else = no point of the other rectangle is inside this rectangle ->
         * meaning that the whole other rectangle is outside this rectangle.
         */
        return this;
    }

    /**
     * This method calculate the new rectangle if
     * this rectangle contains the lower right point of the other rectangle.
     *
     * @param other the second rectangle.
     * @return new min rectangle.
     */
    public Rectangle containsLowerRight(Rectangle other) {
        if (this.isInWindow(other.upperRight)) {
            Line otherUpper = new Line(other.upperLeft, other.upperRight);
            Line thisLeft = new Line(this.upperLeft, this.lowerLeft);
            Point a = otherUpper.intersectionWith(thisLeft);
            return new Rectangle(a, other.lowerRight);
        } else if (this.isInWindow(other.lowerLeft)) {
            Line otherLeft = new Line(other.upperLeft, other.lowerLeft);
            Line thisUpper = new Line(this.upperLeft, this.upperRight);
            Point a = otherLeft.intersectionWith(thisUpper);
            return new Rectangle(a, other.lowerRight);
        }
        return new Rectangle(this.upperLeft, other.lowerRight);
    }

    /**
     * This method calculate the new rectangle if
     * this rectangle contains the upper left point of the other rectangle.
     *
     * @param other the second rectangle.
     * @return new min rectangle.
     */
    public Rectangle containsUpperLeft(Rectangle other) {
        if (this.isInWindow(other.upperRight)) {
            Line otherRight = new Line(other.upperRight, other.lowerRight);
            Line thisLower = new Line(this.lowerLeft, this.lowerRight);
            Point d = otherRight.intersectionWith(thisLower);
            return new Rectangle(other.upperLeft, d);
        } else if (this.isInWindow(other.lowerLeft)) {
            Line otherLower = new Line(other.lowerLeft, other.lowerRight);
            Line thisRight = new Line(this.upperRight, this.lowerRight);
            Point d = otherLower.intersectionWith(thisRight);
            return new Rectangle(other.upperLeft, d);
        }
        return new Rectangle(other.upperLeft, this.lowerRight);
    }

    /**
     * This method check if a rectangle contains other rectangle.
     *
     * @param other the second rectangle.
     * @return true - if the rectangle contains the other rectangle,
     * false - if the rectangle dont contains the other rectangle.
     */
    public boolean contains(Rectangle other) {
        return this.isInWindow(other.upperLeft)
                && this.isInWindow(other.upperRight)
                && this.isInWindow(other.lowerLeft)
                && this.isInWindow(other.lowerRight);
    }

    /**
     * This method check if a point is inside the rectangle.
     *
     * @param p the given point.
     * @return true - if the rectangle contains the point,
     * false - if the rectangle dont contains the point given.
     */
    public boolean containsPoint(Point p) {
        return (p.getX() > this.lowerLeft.getX() && p.getX() < this.upperRight.getX()
                && p.getY() > this.lowerLeft.getY() && p.getY() < this.upperRight.getX());
    }

    /**
     * This method simply checks if the given point
     * is one of this rectangle's edges.
     *
     * @param p given Point to check.
     * @return true - if the given point is indeed
     * one of this rectangle's edges, false otherwise.
     */
    public boolean haveThisPoint(Point p) {
        return p.equals(this.upperLeft)
                || p.equals(this.upperRight)
                || p.equals(this.lowerLeft)
                || p.equals(this.lowerRight);
    }

    /**
     * @return rectangle's upperLeft Point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return rectangle's upperRight Point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * @return rectangle's lowerLeft Point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * @return rectangle's lowerRight Point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * @return rectangle's width.
     */
    public double getWidth() {
        return this.lowerRight.getX() - this.lowerLeft.getX();
    }

    /**
     * @return rectangle's height.
     */
    public double getHeight() {
        return this.lowerLeft.getY() - this.upperLeft.getY();
    }

    /**
     * @return geometry.Line of this Rectangle's top side.
     */
    public Line getTopSide() {
        return new Line(this.upperLeft, this.upperRight);
    }

    /**
     * @return geometry.Line of this Rectangle's bottom side.
     */
    public Line getBottomSide() {
        return new Line(this.lowerLeft, this.lowerRight);
    }

    /**
     * @return geometry.Line of this Rectangle's left side.
     */
    public Line getLeftSide() {
        return new Line(this.upperLeft, this.lowerLeft);
    }

    /**
     * @return geometry.Line of this Rectangle's right side.
     */
    public Line getRightSide() {
        return new Line(this.upperRight, this.lowerRight);
    }

    /**
     * @return ArrayList of this Rectangle's sides.
     */
    public List<Line> getSides() {
        List<Line> sides = new ArrayList<>();
        sides.add(this.getTopSide());
        sides.add(this.getBottomSide());
        sides.add(this.getLeftSide());
        sides.add(this.getRightSide());
        return sides;
    }
}
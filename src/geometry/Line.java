//318509700
package geometry;

import java.util.List;

/**
 * This class handles all the methods related to
 * the object geometry.Line.
 *
 * @author Peleg Shlomo
 * @version 1.3
 * @since 1.0
 */
public class Line {

    //the fields are final until setters will be needed.
    private final Point startPoint;
    private final Point endPoint;


    //Class Constants
    private static final int FIRST_POINT = 0;

    /**
     * Main Constructor 1.
     *
     * @param start line's starting Point.
     * @param end   line's ending Point.
     */
    public Line(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }

    /**
     * Main Constructor 2.
     *
     * @param x1 starting Point X value.
     * @param y1 starting Point Y value.
     * @param x2 ending Point X value.
     * @param y2 ending Point X value.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * This method calculates the line's length.
     *
     * @return line's length
     */
    public double length() {
        return (this.startPoint).distance(this.endPoint);
    }

    /**
     * This method calculates the line's middle Point.
     *
     * @return line's middle Point.
     */
    public Point middle() {
        double xVal = (this.startPoint.getX() + this.endPoint.getX()) / 2;
        double yVal = (this.startPoint.getY() + this.endPoint.getY()) / 2;
        return new Point(xVal, yVal);
    }

    /**
     * @return line's staring Point.
     */
    public Point start() {
        return this.startPoint;
    }

    /**
     * @return line's ending Point.
     */
    public Point end() {
        return this.endPoint;
    }

    /**
     * This method checks if an intersection point
     * exist between two lines,using the intersectionWith
     * method that returns null if no intersection is found.
     * Note : the intersectionWith is efficient enough to reuse.
     *
     * @param other second line.
     * @return true - if an intersection point is found,
     * false - if an intersection point is not found.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * This method finds intersection point if the lines intersect.
     * Point a is the upperRight point,
     * Point b is the upperLeft point,
     * Point c is the lowerRight point,
     * Point d is the lowerLeft.
     *
     * @param other second line.
     * @return intersection Point if found, else -> null.
     */
    public Point intersectionWith(Line other) {
        Point a = new Point(this.startPoint.getX(), this.startPoint.getY());
        Point b = new Point(this.endPoint.getX(), this.endPoint.getY());
        Point c = new Point(other.startPoint.getX(), other.startPoint.getY());
        Point d = new Point(other.endPoint.getX(), other.endPoint.getY());

        //check if the input is two equal dots (a==b==c==d), if true return point a.
        if (a.equals(b) && c.equals(d) && a.equals(c)) {
            return a;
        }

        //checks if the line collide
        if (this.equals(other)) {
            return null;
        }

        //check intersection point with one or more unlimited slope lines.
        Point unlimCheck = checkUnlimSlope(a, b, c, d);

        //check if the found point if withing the lines starting and ending points.
        if (unlimCheck != null && checkPoint(unlimCheck, other, this)) {
            return unlimCheck;
        }

        //calculates y=mx+b for each of the lines.
        double m1 = ((a.getY() - b.getY()) / (a.getX() - b.getX()));
        double b1 = a.getY() - m1 * a.getX();
        double m2 = ((c.getY() - d.getY()) / (c.getX() - d.getX()));
        double b2 = c.getY() - m2 * c.getX();

        /*
         * check a state when a line and a point is given
         * and checking if the point is withing the line starting and ending points.
         * if it does -> return the Point.
         */
        if ((a.getX() == b.getX() && c.equals(d)) && (checkPoint(c, this))) {
            return c;
        } else if (c.getX() == d.getX() && a.equals(b) && (checkPoint(a, other))) {
            return a;
        }

        /*
         * calculate intersection points and check if the point
         * is withing the line starting and ending points.
         */
        double interX = ((b2 - b1) / (m1 - m2));
        double interY = m1 * interX + b1;
        if (checkPoint(new Point(interX, interY), this, other)) {
            return new Point(interX, interY);
        }
        return null;
    }

    /**
     * This method checks intersection points with lines that have
     * unlimited slope.
     *
     * @param a the starting point of line ab
     * @param b the ending point of line ab
     * @param c the starting point of line cd
     * @param d the ending point of line cd
     * @return intersection geometry.Point if found, else -> null.
     */
    private Point checkUnlimSlope(Point a, Point b, Point c, Point d) {

        //line ab have unlimited slope but line cd doesnt
        if (a.getX() == b.getX() && c.getX() != d.getX()) {
            double m2 = ((c.getY() - d.getY()) / (c.getX() - d.getX()));
            double b2 = c.getY() - m2 * c.getX();
            return new Point(a.getX(), (m2 * a.getX() + b2));

            //line cd have unlimited slope but line ab doesnt
        } else if (c.getX() == d.getX() && a.getX() != b.getX()) {
            double m1 = ((a.getY() - b.getY()) / (a.getX() - b.getX()));
            double b1 = a.getY() - m1 * a.getX();
            return new Point(c.getX(), (m1 * c.getX() + b1));

            //line ab and line dc have the same unlimited slope
        } else if ((a.getX() == b.getX() && c.getX() == d.getX() && a.getX() == c.getX())
                || (a.getY() == b.getY() && c.getY() == d.getY() && a.getY() == c.getY())) {

            //Point a equals to Point c and the points b,d are not between a,b
            if ((a.equals(c) && !d.isBetween(a, b) && !b.isBetween(a, d))) {
                return a;

                //Point b equals to Point c and the points a,d are not between c,d and c,a
            } else if ((b.equals(c) && !d.isBetween(c, a) && !a.isBetween(c, d))) {
                return b;

                //Point a equals to Point d and the points b,c are not between a,c and a,b
            } else if ((a.equals(d) && !c.isBetween(a, b) && !b.isBetween(a, c))) {
                return d;

                //Point d equals to Point b and the points a,c are not between b,c and b,a
            } else if ((d.equals(b) && !a.isBetween(b, c) && !c.isBetween(b, a))) {
                return d;
            }
        }

        //no intersection point
        return null;
    }

    /**
     * This method checks if a point is between the starting
     * point and the ending point of line given.
     *
     * @param a Point to check.
     * @param b Line to check.
     * @return true - if the point if valid,
     * false - if the point is outside the line.
     */
    private boolean checkPoint(Point a, Line b) {
        double x1 = b.startPoint.getX();
        double y1 = b.startPoint.getY();
        double x2 = b.endPoint.getX();
        double y2 = b.endPoint.getY();
        return (isBetween(a.getX(), x1, x2) && (isBetween(a.getY(), y1, y2)));
    }

    /**
     * This method checks if a Point given is  between the starting
     * point and the ending point of the lines given.
     *
     * @param a geometry.Point to check.
     * @param b first Line to check.
     * @param c second Line to check.
     * @return true - if the point if valid,
     * false - if the point is outside one/more lines.
     */
    private boolean checkPoint(Point a, Line b, Line c) {
        return checkPoint(a, b) && checkPoint(a, c);
    }

    /**
     * This method checks if the Point an is between a1 and a2.
     *
     * @param an target Point.
     * @param a1 first Point to compare with.
     * @param a2 second Point to compare with.
     * @return true if an is between a1 and a2, false - otherwise.
     */
    private boolean isBetween(double an, double a1, double a2) {
        return (Math.min(a1, a2) <= an) && (an <= Math.max(a1, a2));
    }

    /**
     * This method finds out if two lines are equal.
     *
     * @param other the second line.
     * @return true - if the lines are equal, false - otherwise.
     */
    public boolean equals(Line other) {
        return (this.startPoint.equals(other.startPoint) && this.endPoint.equals(other.endPoint)
                || (this.startPoint.equals(other.endPoint) && this.endPoint.equals(other.startPoint)));
    }

    /**
     * This method calculates the closest intersection point of this line and
     * the given rectangle.
     *
     * @param rect given Rectangle.
     * @return closest intersection point to the start of the line,
     * or null if no intersection has been found.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionList = rect.intersectionPoints(this);
        return closestIntersectionFromList(intersectionList);
    }

    /**
     * This method get a list of point on this line and return which of
     * the points is the closest to the start of the line.
     *
     * @param pointsList given list of Points.
     * @return closest Point to the start of this line.
     */
    public Point closestIntersectionFromList(List<Point> pointsList) {
        if (pointsList.isEmpty()) {
            return null;

            // if there is only one line - it is the closest.
        } else if (pointsList.size() == 1) {
            return pointsList.get(FIRST_POINT);
        }
        Point closestPoint = pointsList.get(FIRST_POINT);
        for (Point p
                : pointsList) {
            if (p.distance(this.startPoint) < closestPoint.distance(this.startPoint)) {
                closestPoint = p;
            }
        }
        return closestPoint;
    }
}
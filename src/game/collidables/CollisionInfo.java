//318509700
package game.collidables;

import geometry.Point;

/**
 * This class handles all the methods related to
 * the object CollisionInfo.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Main Constructor.
     * setting up the CollisionInfo object using args given.
     *
     * @param p collisionPoint.
     * @param c collisionObject.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collisionObject = c;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
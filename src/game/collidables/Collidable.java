//318509700
package game.collidables;

import game.GameLevel;
import game.elements.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * This interface handles the game.collidables methods.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  given Point of collision.
     * @param currentVelocity current ball's velocity.
     * @param hitter          the given ball collided.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Adds the collidable object to the game.
     *
     * @param g given game object.
     */
    void addToGame(GameLevel g);
}
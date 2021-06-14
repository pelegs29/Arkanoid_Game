//318509700
package game.elements;

import biuoop.DrawSurface;
import game.GameLevel;
import game.collidables.Collidable;
import game.listeners.HitListener;
import game.listeners.HitNotifier;
import game.sprites.Sprite;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all the methods related
 * to the object Block.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //some fields are final until a setter will be needed.
    private final Rectangle block;
    private Color color;
    private final List<HitListener> hitListeners;

    //Class constants
    public static final int DEFAULT_WIDTH = 76;
    public static final int DEFAULT_HEIGHT = 20;

    /**
     * Main Constructor 1.
     * setting the default gray color.
     *
     * @param block block's rectangle
     */
    public Block(Rectangle block) {
        this.block = block;
        this.color = Color.GRAY;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Main Constructor 2.
     * setting a rectangle using the point, width and
     * height given and the Main Constructor 1.
     *
     * @param a      rectangle's upperLeft point.
     * @param width  rectangle's width.
     * @param height rectangle's height.
     */
    public Block(Point a, int width, int height) {
        this(new Rectangle(a, width, height));
    }

    /**
     * This method notify all of the registered HitListeners object that hit occurs.
     *
     * @param hitter the ball that created the hit event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all game.listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        if (currentVelocity == null) {
            return null;
        }

        //checks if the collision point is at one of the block's edges.
        if (this.block.haveThisPoint(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        //checks if the collision point is at the top or bottom side of the block.
        if ((collisionPoint.isBetween(this.block.getTopSide().start(), this.block.getTopSide().end()))
                || (collisionPoint.isBetween(this.block.getBottomSide().start(), this.block.getBottomSide().end()))) {
            newVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        //checks if the collision point is at the right or left side of the block.
        if ((collisionPoint.isBetween(this.block.getLeftSide().start(), this.block.getLeftSide().end()))
                || (collisionPoint.isBetween(this.block.getRightSide().start(), this.block.getRightSide().end()))) {
            newVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    @Override
    public void timePassed() {
        //can be used for animating blocks in the future.
    }

    /**
     * This method simply add this block only to the list of game.collidables in the given game.
     *
     * @param g given game.Game's object.
     */
    public void addToCollidables(GameLevel g) {
        g.addCollidable(this);
    }

    /**
     * @param givenColor new inputted Block's color.
     */
    public void setColor(Color givenColor) {
        this.color = givenColor;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
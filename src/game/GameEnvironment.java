package game;//318509700

import game.collidables.Collidable;
import game.collidables.CollisionInfo;
import game.elements.Block;
import geometry.Line;
import geometry.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * This class handles all the methods related to
 * the object game.GameEnvironment.
 * this class will hold the collection of collidable objects.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class GameEnvironment {

    private final List<Collidable> collidablesList;

    //Class Constants
    private static final int INDEX_OF_RIGHT_SIDE = 2;
    private static final int INDEX_OF_LEFT_SIDE = 1;

    /**
     * Sole Constructor
     * creates an empty ArrayList of game.collidables.
     */
    public GameEnvironment() {
        this(GameLevel.BORDER_WIDTH);
    }

    /**
     * Main Constructor 1.
     * creates game.GameEnvironment using the collidable list given.
     *
     * @param list given list of game.collidables.
     */
    public GameEnvironment(List<Collidable> list) {
        this.collidablesList = list;
    }

    /**
     * Main Constructor 2.
     * creates and initializes game.GameEnvironment with border blocks
     * using the size of the border blocks given and gui size given.
     *
     * @param sizeOfBlocks given size(width) of the border blocks.
     */
    public GameEnvironment(int sizeOfBlocks) {
        this.collidablesList = getBordersBlocks(sizeOfBlocks);
    }

    /**
     * @param c given collidable to be added to the environment.
     */
    public void addCollidable(Collidable c) {
        collidablesList.add(c);
    }

    /**
     * @param c given collidable to be removed from the environment.
     */
    public void removeCollidable(Collidable c) {
        collidablesList.remove(c);
    }

    /**
     * This method contain HashTable of computed collision of each object in the environment
     * with the given ball trajectory.
     *
     * @param trajectory ball's movement from line.start() to line.end().
     * @return he information about the closest collision that is going to occur,
     * or null if no collision will occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Hashtable<Point, Collidable> collisionDict = new Hashtable<>();

        // Make a copy of the collidablesList before iterating over them.
        List<Collidable> collidablesListCopy = new ArrayList<>(this.collidablesList);

        /*
         * check collision with each collidable object in the game.GameEnvironment,
         * if a collision found add the collision point and the collision object
         * to the HashTable of collisions.
         */
        for (Collidable obj : collidablesListCopy) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(obj.getCollisionRectangle());
            if (collisionPoint != null) {
                collisionDict.put(collisionPoint, obj);
            }
        }
        if (collisionDict.isEmpty()) {
            return null;
        }

        //creates CollisionInfo if a collision found with all of the information collected.
        List<Point> collisionList = new ArrayList<>(collisionDict.keySet());
        Point collisionPoint = trajectory.closestIntersectionFromList(collisionList);
        return new CollisionInfo(collisionPoint, collisionDict.get(collisionPoint));
    }

    /**
     * This method will create 5 basic blocks :
     * background block and all four border blocks.
     * Note : the blocks insertion order is used in this program as their unique identifier.
     *
     * @param sizeOfBlock the given size of the border blocks.
     * @return list of all five basic game blocks.
     */
    public List<Collidable> getBordersBlocks(int sizeOfBlock) {
        List<Collidable> borders = new ArrayList<>();
        int width = GameFlow.GUI_WIDTH;
        int height = GameFlow.GUI_HEIGHT;
        Block score = new Block(new Point(0, 0), width, sizeOfBlock);
        score.setColor(Color.LIGHT_GRAY);
        Block top = new Block(new Point(0, sizeOfBlock), width - sizeOfBlock, sizeOfBlock);
        Block left = new Block(new Point(0, sizeOfBlock), sizeOfBlock, height - sizeOfBlock);
        Block right = new Block(new Point(width - sizeOfBlock, sizeOfBlock), sizeOfBlock, height);

        /*
         * Note : The blocks insertion order is used in this program as their unique identifier.
         * if a change needs to be made update this class constants to match the change.
         */
        borders.add(top);
        borders.add(left);
        borders.add(right);
        borders.add(score);
        return borders;
    }

    /**
     * @return the game.Game's right border block.
     */
    public Collidable getRightSide() {
        return this.collidablesList.get(INDEX_OF_RIGHT_SIDE);
    }

    /**
     * @return the game.Game's left border block.
     */
    public Collidable getLeftSide() {
        return this.collidablesList.get(INDEX_OF_LEFT_SIDE);
    }

    /**
     * @return the game.Game's list of game.collidables.
     */
    public List<Collidable> getCollidablesList() {
        return this.collidablesList;
    }
}
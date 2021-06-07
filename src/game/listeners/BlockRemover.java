//318509700
package game.listeners;

import game.GameLevel;
import game.elements.Ball;
import game.elements.Block;
import game.elements.Counter;

/**
 * This class implements block remover listener which s in charge of removing
 * blocks from the game, as well as keeping count of the number of blocks that remain.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class BlockRemover implements HitListener {

    //the fields are final until setters will be needed.
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Main constructor.
     * creates new BlockRemover using the game and counter given.
     *
     * @param game          given game.Game object.
     * @param removedBlocks given counter of the remainingBlocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decreaseBy1();
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
    }
}
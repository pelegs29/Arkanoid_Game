//318509700
package game.listeners;

import game.GameLevel;
import game.elements.Ball;
import game.elements.Block;
import game.elements.Counter;

/**
 * This class implements ball remover listener which remove a ball from the game
 * when it get notified about an event of crossing the "death" block in the lower part
 * of the screen.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class BallRemover implements HitListener {

    //the fields are final until setters will be needed.
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Main constructor.
     * creates new BallRemover using the game and counter given.
     *
     * @param game         given game.Game object.
     * @param ballsCounter given counter of the remainingBalls.
     */
    public BallRemover(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.remainingBalls = ballsCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decreaseBy1();
        hitter.removeFromGame(game);
    }
}
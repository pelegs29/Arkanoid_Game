//318509700
package game.listeners;

import game.elements.Ball;
import game.elements.Block;
import game.elements.Counter;

/**
 * This class implements score tracking listener which s in charge
 * of keeping count of the score in the game.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class ScoreTrackingListener implements HitListener {

    //the currentScore for this time can be final.
    private final Counter currentScore;

    /**
     * Main constructor.
     * creates a ScoreTrackingListener with the counter given.
     *
     * @param scoreCounter given score Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @return the current score Counter object.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
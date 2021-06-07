//318509700
package game.elements;

/**
 * This class handles all the methods related
 * to the Counter Block.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class Counter {
    private int count;

    /**
     * Main constructor 1.
     * creates and initialize the counter to 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Main constructor 2.
     * creates and initialize the counter to the given number.
     *
     * @param startNum given starting number for the counter.
     */
    public Counter(int startNum) {
        this.count = startNum;
    }

    // add number to current count.

    /**
     * @param number to be added to the counter.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * increasing the counter by 1.
     */
    public void increaseBy1() {
        this.count++;
    }

    /**
     * @param number to be subtract from the counter.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * decreasing the counter by 1.
     */
    public void decreaseBy1() {
        this.count--;
    }

    /**
     * @return the current counter's value.
     */
    public int getValue() {
        return this.count;
    }

    @Override
    public String toString() {
        return Integer.toString(this.count);
    }
}
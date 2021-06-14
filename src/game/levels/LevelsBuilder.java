//318509700
package game.levels;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is in charge of building the levels
 * according to the inputString.
 *
 * @author Peleg Shlomo
 * @version 1.0
 * @since 1.0
 */
public class LevelsBuilder {
    private final String[] inputString;
    private final List<LevelInformation> levelsList;

    public static final int FALSE = -1;

    /**
     * Constructor which sets the inputString and
     * initiates the list of the levels available to choose from.
     *
     * @param input the user input of the levels requested.
     */
    public LevelsBuilder(String[] input) {
        this.inputString = input;
        this.levelsList = initLevelsList();
    }

    /**
     * this method extract from the input string the valid levels and return them as a list of levels.
     *
     * @return list of valid levels from the user input.
     */
    public List<LevelInformation> buildLevels() {

        //if the user didnt inputted any args -> play the levels by order.
        if (inputString.length == 0) {
            return levelsList;
        }

        /*
         * check each string of the input ->
         * if the string is valid add to the new levels list.
         * else, ignore it.
         */
        List<LevelInformation> gameLevels = new ArrayList<>();
        for (String level : inputString) {
            if (isValid(level) != FALSE) {
                gameLevels.add(this.levelsList.get(isValid(level) - 1));
            }
        }
        return gameLevels;
    }

    /**
     * this method return the level number only if it valid.
     *
     * @param str the string of the level inputted.
     * @return the level number if it valid OR -1 if it is not.
     */
    private int isValid(String str) {
        try {
            int givenNumber = Integer.parseInt(str);

            //check if the level is between 0 to the number of levels available.
            if (givenNumber >= 1 && givenNumber <= levelsList.size()) {
                return givenNumber;
            }
        } catch (NumberFormatException e) {
            return FALSE;
        }
        return FALSE;
    }

    /**
     * this method will initiate the levels available into a default list.
     *
     * @return default list of levles.
     */
    private List<LevelInformation> initLevelsList() {
        List<LevelInformation> listOfLevels = new ArrayList<>();
        LevelInformation level1 = new FirstLevel();
        LevelInformation level2 = new SecondLevel();
        LevelInformation level3 = new ThirdLevel();
        LevelInformation level4 = new ForthLevel();
        listOfLevels.add(level1);
        listOfLevels.add(level2);
        listOfLevels.add(level3);
        listOfLevels.add(level4);
        return listOfLevels;
    }
}
package game.levels;

import java.util.ArrayList;
import java.util.List;

public class LevelsBuilder {
    private final String[] inputString;
    private final List<LevelInformation> levels_list;

    public static final int FALSE = -1;

    public LevelsBuilder(String[] input) {
        this.inputString = input;
        this.levels_list = initLevelsList();
    }

    public List<LevelInformation> buildLevels() {
        if (inputString.length == 0) {
            return levels_list;
        }
        List<LevelInformation> gameLevels = new ArrayList<>();
        for (String level : inputString) {
            if (isValid(level) != FALSE) {
                gameLevels.add(this.levels_list.get(isValid(level) - 1));
            }
        }
        return gameLevels;
    }

    private int isValid(String str) {
        try {
            int givenNumber = Integer.parseInt(str);
            if (givenNumber >= 1 && givenNumber <= 4) {
                return givenNumber;
            }
        } catch (NumberFormatException e) {
            return FALSE;
        }
        return FALSE;
    }

    private List<LevelInformation> initLevelsList() {
        List<LevelInformation> levels_list = new ArrayList<>();
        LevelInformation level_1 = new FirstLevel();
        LevelInformation level_2 = new SecondLevel();
        LevelInformation level_3 = new ThirdLevel();
        LevelInformation level_4 = new ForthLevel();
        levels_list.add(level_1);
        levels_list.add(level_2);
        levels_list.add(level_3);
        levels_list.add(level_4);
        return levels_list;
    }
}

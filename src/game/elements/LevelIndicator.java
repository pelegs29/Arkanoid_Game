package game.elements;

import biuoop.DrawSurface;
import game.GameFlow;
import game.GameLevel;
import game.sprites.Sprite;

public class LevelIndicator implements Sprite {
    private final String levelName;

    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int textXVal = (GameFlow.GUI_WIDTH / 2) + 150;
        int textYVal = 17;
        d.drawText(textXVal, textYVal, "Level Name: " + this.levelName, textYVal);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

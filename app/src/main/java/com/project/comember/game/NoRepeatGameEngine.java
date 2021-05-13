package com.project.comember.game;

import com.project.comember.ui.GameFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoRepeatGameEngine extends GameEngine {

    public NoRepeatGameEngine(GameFragment gameController) {
        super(gameController);
    }

    @Override
    protected void startHighlighting(List<GameColor> colorList, int highlightTime, int pauseTime) {
        GameColor newColor = colorList.get(colorList.size() - 1);
        super.startHighlighting(Arrays.asList(newColor), highlightTime, pauseTime);
    }
}

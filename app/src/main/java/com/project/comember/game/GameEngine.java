package com.project.comember.game;

import com.project.comember.ui.GameFragment;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    private int STANDARD_HIGHLIGHT_TIME = 400;

    private GameFragment mGameController;
    private GameStatus mGameStatus;

    private ArrayList<GameColor> mColorList = new ArrayList<>();


    public GameEngine(GameFragment gameController) {
        this.mGameController = gameController;
        this.mGameStatus = GameStatus.IDLE;
    }

    public void start() {
        startNextRound();
    }

    public void startNextRound() {
        GameColor newColor = getRandomColor();
        mColorList.add(newColor);
        mGameController.highlightColorSequence(mColorList, STANDARD_HIGHLIGHT_TIME);
    }

    private GameColor getRandomColor() {
        //inclusive values
        int max = 3;
        int min = 0;

        Random r = new Random();
        int randomInt = r.nextInt((max - min) + 1) + min;
        return GameColor.valueOf(randomInt);
    }

    public void colorClicked() {
    }

    public GameStatus getGameStatus() {
        return mGameStatus;
    }


}

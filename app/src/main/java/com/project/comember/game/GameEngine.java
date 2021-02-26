package com.project.comember.game;

import android.util.Log;

import com.project.comember.ui.GameFragment;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    private int STANDARD_HIGHLIGHT_TIME = 600;
    private int STANDARD_HIGHLIGHT_PAUSE_TIME = 200;

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
        for (int i = 0; i < 100; i++) {
            GameColor newColor = GameColor.getRandomColor();
            mColorList.add(newColor);
        }
        mGameController.highlightColorSequence(mColorList, STANDARD_HIGHLIGHT_TIME, STANDARD_HIGHLIGHT_PAUSE_TIME);
    }

    public void highlightingFinished() {
        Log.d("Done", "highlightingFinished: Donenenene");
    }

    public void colorClicked() {
    }

    public GameStatus getGameStatus() {
        return mGameStatus;
    }


}

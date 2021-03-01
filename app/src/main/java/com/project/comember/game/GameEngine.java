package com.project.comember.game;

import com.project.comember.ui.GameFragment;

import java.util.ArrayList;

public class GameEngine {

    private static final int STANDARD_HIGHLIGHT_TIME = 600;
    private static final int STANDARD_HIGHLIGHT_PAUSE_TIME = 200;

    private final GameFragment mGameController;
    private GameStatus mGameStatus;
    private int mGameScore;

    private int checkColorIndex = 0;
    private final ArrayList<GameColor> mColorList = new ArrayList<>();


    public GameEngine(GameFragment gameController) {
        this.mGameController = gameController;
        this.mGameStatus = GameStatus.IDLE;
    }

    public void start() {
        if (mGameStatus == GameStatus.IDLE)
            startNextRound();
    }

    private void startNextRound() {
        checkColorIndex = 0;
        GameColor newColor = GameColor.getRandomColor();
        mColorList.add(newColor);

        this.mGameStatus = GameStatus.HIGHLIGHTING;
        mGameController.highlightColorSequence(mColorList, STANDARD_HIGHLIGHT_TIME, STANDARD_HIGHLIGHT_PAUSE_TIME);
    }

    public void highlightingFinished() {
        this.mGameStatus = GameStatus.PLAYING;
    }

    public void checkColorClicked(GameColor clickedColor) {
        if (mGameStatus != GameStatus.PLAYING)
            return;

        if (mColorList.get(checkColorIndex) == clickedColor) {
            checkColorIndex++;
        } else {
            roundLost();
        }

        if (checkColorIndex == mColorList.size()) {
            roundWon();
        }
    }

    public void roundWon() {
        mGameScore++;
        mGameController.incrementGameScore();
        startNextRound();
    }

    public void roundLost() {
        mGameController.gameLost(mGameScore);
    }

    public GameStatus getGameStatus() {
        return mGameStatus;
    }
}

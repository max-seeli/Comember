package com.project.comember.game;

import com.project.comember.ui.GameFragment;
import com.project.comember.util.FutureCallback;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        new FutureCallback(wait(1000)) {
            @Override
            public void futureFinished() {
                checkColorIndex = 0;
                GameColor newColor = GameColor.getRandomColor();
                mColorList.add(newColor);

                mGameStatus = GameStatus.HIGHLIGHTING;
                mGameController.highlightColorSequence(mColorList, STANDARD_HIGHLIGHT_TIME, STANDARD_HIGHLIGHT_PAUSE_TIME);
            }
        };
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

    private Future<?> wait(int duration) {
        ExecutorService waitExecutor = Executors.newSingleThreadExecutor();

        return waitExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}

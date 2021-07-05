package com.project.comember.game;

import android.os.CountDownTimer;

import com.project.comember.ui.GameFragment;
import com.project.comember.util.FutureCallback;

import java.util.ArrayList;
import java.util.List;
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

    CountDownTimer timer;

    public GameEngine(GameFragment gameController) {
        this.mGameController = gameController;
        this.mGameStatus = GameStatus.IDLE;

        timer = getTimer();
    }

    private CountDownTimer getTimer() {
        int millisTimerDuration = 3000;
        return new CountDownTimer(millisTimerDuration, 30) {
            @Override
            public void onTick(long millisUntilFinished) {
                mGameController.setClickableTimePercentRemaining((int)((millisUntilFinished * 100) / millisTimerDuration));
            }

            @Override
            public void onFinish() {
                roundLost();
            }
        };
    }

    public void start() {
        if (mGameStatus == GameStatus.IDLE)
            startNextRound();
    }

    private void startNextRound() {
        mGameStatus = GameStatus.HIGHLIGHTING;
        mGameController.setClickable(false);
        mGameController.unhighlightAll();
        new FutureCallback(wait(1000)) {
            @Override
            public void futureFinished() {
                checkColorIndex = 0;
                GameColor newColor = GameColor.getRandomColor();
                mColorList.add(newColor);

                startHighlighting(mColorList, STANDARD_HIGHLIGHT_TIME, STANDARD_HIGHLIGHT_PAUSE_TIME);
            }
        };
    }

    protected void startHighlighting(List<GameColor> colorList, int highlightTime, int pauseTime) {
        mGameController.highlightColorSequence(colorList, highlightTime, pauseTime);
    }

    public void highlightingFinished() {
        this.mGameStatus = GameStatus.PLAYING;
        mGameController.setClickable(true);
        timer.start();
    }

    public void checkColorClicked(GameColor clickedColor) {
        if (mGameStatus != GameStatus.PLAYING)
            return;

        if (mColorList.get(checkColorIndex) == clickedColor) {
            checkColorIndex++;
            resetTimer();
        } else {
            roundLost();
        }

        if (checkColorIndex == mColorList.size()) {
            roundWon();
        }
    }

    public void roundWon() {
        cancelTimer();

        mGameScore++;
        mGameController.incrementGameScore();
        startNextRound();
    }

    public void roundLost() {
        cancelTimer();

        mGameController.gameLost(mGameScore);
    }

    private void startTimer() {
        timer.start();
        mGameController.setClickableTimePercentRemaining(100);
    }

    private void resetTimer() {
        timer.cancel();
        timer.start();
        mGameController.setClickableTimePercentRemaining(100);
    }

    private void cancelTimer() {
        timer.cancel();
        mGameController.setClickableTimePercentRemaining(100);
    }

    private Future<?> wait(int duration) {
        ExecutorService waitExecutor = Executors.newSingleThreadExecutor();

        return waitExecutor.submit(() -> {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });
    }
}

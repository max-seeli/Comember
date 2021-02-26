package com.project.comember.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.comember.FutureCallback;
import com.project.comember.R;
import com.project.comember.game.GameColor;
import com.project.comember.game.GameEngine;
import com.project.comember.ui.widgets.GameButton;
import com.project.comember.ui.widgets.GameLayout;
import com.project.comember.ui.widgets.GameScoreCounter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameFragment extends Fragment {

    private GameEngine gameEngine;

    private GameLayout gameLayout;
    private GameScoreCounter gameScoreCounter;
    private GameButton[] gameButtons;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        gameScoreCounter = view.findViewById(R.id.game_score_counter);

        gameLayout = view.findViewById(R.id.game_button_layout);
        gameButtons = gameLayout.getGameButtons();

        setClickable(false);

        for (int i = 0; i < 4; i++) {
            gameButtons[i].setGameColor(GameColor.valueOf(i));
        }
        gameEngine = new GameEngine(this);

        gameEngine.start();
    }

    public void highlightColorSequence(List<GameColor> gameColorSequence, int highlightMillis, int highlightPauseMillis) {


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> highlightFuture = null;
        for(GameColor gameColor : gameColorSequence) {
             highlightFuture = executor.submit(new HighlightButtonRunnable(
                    gameButtons[gameColor.getValue()],
                    highlightMillis,
                    highlightPauseMillis
            ));
        }

        new FutureCallback(highlightFuture) {
            @Override
            public void futureFinished() {
                gameEngine.highlightingFinished();
            }
        };
    }

    private void setClickable(boolean clickable) {
        gameLayout.setTouchable(clickable);
    }

    private class HighlightButtonRunnable implements Runnable {

        private GameButton mGameButton;
        private int mHighlightMillis;
        private int mHighlightPauseMillis;

        HighlightButtonRunnable(GameButton gameButton, int highlightMillis, int highlightPauseMillis) {
            mGameButton = gameButton;
            mHighlightMillis = highlightMillis;
            mHighlightPauseMillis = highlightPauseMillis;
        }

        @Override
        public void run() {
            try {
                mGameButton.highlight();
                Thread.sleep(mHighlightMillis);
                mGameButton.unhighlight();
                Thread.sleep(mHighlightPauseMillis);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                mGameButton.unhighlight();
            }
        }


    }
}
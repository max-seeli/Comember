package com.project.comember.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.comember.R;
import com.project.comember.game.GameColor;
import com.project.comember.game.GameEngine;
import com.project.comember.ui.widgets.GameButton;
import com.project.comember.ui.widgets.GameLayout;
import com.project.comember.ui.widgets.GameScoreCounter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameFragment extends Fragment {

    private GameEngine gameEngine;

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

        GameLayout gameLayout = view.findViewById(R.id.game_button_layout);
        gameButtons = gameLayout.getGameButtons();

        for (int i = 0; i < 4; i++) {
            gameButtons[i].setGameColor(GameColor.valueOf(i));
        }
        gameEngine = new GameEngine(this);

        gameEngine.start();
    }

    public void highlightColorSequence(List<GameColor> gameColorSequence, int highlightMillis) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for(GameColor gameColor : gameColorSequence) {
            executor.execute(new HighlightButtonRunnable(
                    gameButtons[gameColor.getValue()],
                    highlightMillis
            ));
        }
    }


    private class HighlightButtonRunnable implements Runnable {

        private GameButton mGameButton;
        private int mHighlightMillis;

        HighlightButtonRunnable(GameButton gameButton, int highlightMillis) {
            mGameButton = gameButton;
            mHighlightMillis = highlightMillis;
        }

        @Override
        public void run() {
            try {
                mGameButton.highlight();
                Thread.sleep(mHighlightMillis);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                mGameButton.unhighlight();
            }
        }
    }
}
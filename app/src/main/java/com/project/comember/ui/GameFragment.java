package com.project.comember.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.project.comember.R;
import com.project.comember.game.GameColor;
import com.project.comember.game.GameEngine;
import com.project.comember.misc.ClickableGroup;
import com.project.comember.misc.HighlightButtonRunnable;
import com.project.comember.ui.widgets.GameButton;
import com.project.comember.ui.widgets.GameLayout;
import com.project.comember.ui.widgets.GameScoreCounter;
import com.project.comember.util.FutureCallback;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameFragment extends Fragment {

    private GameEngine gameEngine;

    private GameLayout gameLayout;
    private GameScoreCounter gameScoreCounter;
    private GameButton[] gameButtons;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        gameEngine = new GameEngine(this);

        ClickableGroup clickToStartGroup = view.findViewById(R.id.click_to_start_group);
        clickToStartGroup.setOnClickListener(clickedView -> {
            clickToStartGroup.setVisibility(View.INVISIBLE);
            startGame();
        });

        gameScoreCounter = view.findViewById(R.id.game_score_counter);

        gameLayout = view.findViewById(R.id.game_button_layout);
        gameButtons = gameLayout.getGameButtons();
        for (int i = 0; i < 4; i++) {
            initializeGameButton(i);
        }
    }

    protected void startGame() {
        gameEngine.start();
    }

    private void initializeGameButton(int index) {
        final GameColor buttonColor = GameColor.valueOf(index);

        gameButtons[index].setGameColor(buttonColor);
        gameButtons[index].setOnClickListener(view -> gameEngine.checkColorClicked(buttonColor));
    }

    public void highlightColorSequence(List<GameColor> gameColorSequence, int highlightMillis, int highlightPauseMillis) {
        Future<?> waitForExecution = null;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (GameColor gameColor : gameColorSequence) {
            waitForExecution = executor.submit(new HighlightButtonRunnable(
                    gameButtons[gameColor.getValue()],
                    highlightMillis,
                    highlightPauseMillis
            ));
        }

        new FutureCallback(waitForExecution) {
            @Override
            public void futureFinished() {
                gameEngine.highlightingFinished();
            }
        };
    }

    public void incrementGameScore() {
        gameScoreCounter.increment();
    }

    public void gameLost(int gameScore) {
        GameFragmentDirections.GameFragmentToGameOverFragment action = GameFragmentDirections.gameFragmentToGameOverFragment();
        action.setGameScore(gameScore);
        action.setPlayAgainActionId(R.id.gameOverFragment_to_gameFragment);
        Navigation.findNavController(getView()).navigate(action);
    }

    public void setClickable(boolean clickable) {
        gameLayout.setTouchable(clickable);
    }


}
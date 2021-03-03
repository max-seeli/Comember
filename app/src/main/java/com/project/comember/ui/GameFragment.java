package com.project.comember.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.project.comember.util.FutureCallback;
import com.project.comember.misc.HighlightButtonRunnable;
import com.project.comember.R;
import com.project.comember.game.GameColor;
import com.project.comember.game.GameEngine;
import com.project.comember.ui.widgets.GameButton;
import com.project.comember.ui.widgets.GameLayout;
import com.project.comember.ui.widgets.GameScoreCounter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GameFragment extends Fragment {

    private GameEngine gameEngine;

    private View gameStartButton;
    private TextView clickToStartTextView;

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

        gameStartButton = view.findViewById(R.id.game_start_button);
        clickToStartTextView = view.findViewById(R.id.text_view_click_to_start);


        gameLayout = view.findViewById(R.id.game_button_layout);
        gameScoreCounter = view.findViewById(R.id.game_score_counter);
        gameButtons = gameLayout.getGameButtons();

        gameEngine = new GameEngine(this);

        for (int i = 0; i < 4; i++) {
            initializeGameButton(i);
        }

        gameStartButton.setOnClickListener(v -> {
            gameStartButton.setVisibility(View.GONE);
            gameEngine.start();
        });
    }

    private void initializeGameButton(int index) {
        final GameColor buttonColor = GameColor.valueOf(index);

        gameButtons[index].setGameColor(buttonColor);
        gameButtons[index].setOnClickListener(view -> {
            gameEngine.checkColorClicked(buttonColor);
        });
    }

    public void highlightColorSequence(List<GameColor> gameColorSequence, int highlightMillis, int highlightPauseMillis) {

        setClickable(false);

        Future<?> waitForExecution = null;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(GameColor gameColor : gameColorSequence) {
             waitForExecution = executor.submit(new HighlightButtonRunnable(
                    gameButtons[gameColor.getValue()],
                    highlightMillis,
                    highlightPauseMillis
            ));
        }

        new FutureCallback(waitForExecution) {
            @Override
            public void futureFinished() {
                setClickable(true);
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
        Navigation.findNavController(getView()).navigate(action);
    }

    private void setClickable(boolean clickable) {
        gameLayout.setTouchable(clickable);
    }

}
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
import com.project.comember.ui.widgets.GameButton;
import com.project.comember.ui.widgets.GameLayout;
import com.project.comember.ui.widgets.GameScoreCounter;

public class GameFragment extends Fragment {

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
            gameButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GameButton) v).highlight();
                }
            });
            gameButtons[i].setGameColor(GameColor.valueOf(i));
        }
    }
}
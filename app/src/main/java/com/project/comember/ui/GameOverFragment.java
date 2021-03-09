package com.project.comember.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.project.comember.R;

public class GameOverFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.game_over_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int mGameScore = GameOverFragmentArgs.fromBundle(getArguments()).getGameScore();

        Button playAgainButton = view.findViewById(R.id.button_play_again);
        Button mainMenuButton = view.findViewById(R.id.button_main_menu);
        TextView gameScoreTextView = view.findViewById(R.id.text_view_game_score_counter);

        playAgainButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.gameOverFragment_to_gameFragment));
        mainMenuButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.gameOverFragment_to_mainFragment));

        gameScoreTextView.setText(Integer.toString(mGameScore));
    }
}
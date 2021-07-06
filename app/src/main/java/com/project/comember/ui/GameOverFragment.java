package com.project.comember.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.project.comember.R;

@SuppressLint("ClickableViewAccessibility")
public class GameOverFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.game_over_fragment, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int gameScore = GameOverFragmentArgs.fromBundle(getArguments()).getGameScore();
        int playAgainId = GameOverFragmentArgs.fromBundle(getArguments()).getPlayAgainActionId();

        Button playAgainButton = view.findViewById(R.id.button_play_again);
        Button mainMenuButton = view.findViewById(R.id.button_main_menu);
        TextView gameScoreTextView = view.findViewById(R.id.text_view_game_score_counter);

        gameScoreTextView.setText(Integer.toString(gameScore));

        playAgainButton.setOnTouchListener(onTouchAfterAnimationNavigateTo(playAgainId));
        mainMenuButton.setOnTouchListener(onTouchAfterAnimationNavigateTo(R.id.gameOverFragment_to_mainFragment));

    }

    private View.OnTouchListener onTouchAfterAnimationNavigateTo(@IdRes int actionId) {
        MotionLayout motionController = (MotionLayout) getView();

        return (v, event) -> {
            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                motionController.setTransitionListener(afterAnimationNavigateTo(actionId));
                motionController.transitionToEnd();
            }
            return true;
        };
    }

    private MotionLayout.TransitionListener afterAnimationNavigateTo(@IdRes int actionId) {
        return new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                Navigation.findNavController(motionLayout).navigate(actionId);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        };
    }
}
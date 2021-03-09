package com.project.comember.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.project.comember.R;

@SuppressLint("ClickableViewAccessibility")
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button playButton = view.findViewById(R.id.button_play);
        Button helpButton = view.findViewById(R.id.button_help);

        playButton.setOnTouchListener(onTouchAfterAnimationNavigateTo(R.id.mainFragment_to_gameFragment));
    }

    private View.OnTouchListener onTouchAfterAnimationNavigateTo(@IdRes int actionId) {
        MotionLayout motionController = (MotionLayout) getView();

        return (v, event) -> {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_UP:
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
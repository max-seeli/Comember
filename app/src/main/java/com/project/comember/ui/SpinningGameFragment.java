package com.project.comember.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.navigation.Navigation;

import com.project.comember.R;
import com.project.comember.game.GameMode;
import com.project.comember.game.GameStatistics;

public class SpinningGameFragment extends GameFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Layer gameButtonLayer = getView().findViewById(R.id.game_button_layer);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 360f);
        animator.addUpdateListener(animation -> gameButtonLayer.setRotation((float) animation.getAnimatedValue()));
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    @Override
    public void gameLost(int gameScore) {
        if (gameLost) return;
        gameLost = true;

        GameStatistics.setNewScore(getContext(), GameMode.SPINNING, gameScore);

        SpinningGameFragmentDirections.SpinningGameFragmentToGameOverFragment action = SpinningGameFragmentDirections.spinningGameFragmentToGameOverFragment();
        action.setGameScore(gameScore);
        action.setPlayAgainActionId(R.id.gameOverFragment_to_spinningGameFragment);
        Navigation.findNavController(getView()).navigate(action);
    }
}
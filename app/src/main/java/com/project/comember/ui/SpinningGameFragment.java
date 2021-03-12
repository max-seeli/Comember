package com.project.comember.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

import androidx.constraintlayout.helper.widget.Layer;
import androidx.navigation.Navigation;

import com.project.comember.R;

public class SpinningGameFragment extends GameFragment {
    @Override
    protected void startGame() {
        super.startGame();
        Layer gameButtonLayer = getView().findViewById(R.id.game_button_layer);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 360f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                gameButtonLayer.setRotation((float)animation.getAnimatedValue());
            }
        });
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    @Override
    public void gameLost(int gameScore) {
        SpinningGameFragmentDirections.SpinningGameFragmentToGameOverFragment action = SpinningGameFragmentDirections.spinningGameFragmentToGameOverFragment();
        action.setGameScore(gameScore);
        action.setPlayAgainActionId(R.id.gameOverFragment_to_spinningGameFragment);
        Navigation.findNavController(getView()).navigate(action);
    }
}
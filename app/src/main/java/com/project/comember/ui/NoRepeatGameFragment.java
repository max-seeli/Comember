package com.project.comember.ui;

import androidx.navigation.Navigation;

import com.project.comember.R;
import com.project.comember.game.GameEngine;
import com.project.comember.game.NoRepeatGameEngine;

public class NoRepeatGameFragment extends GameFragment {

    @Override
    protected GameEngine getGameEngine(GameFragment gameController) {
        return new NoRepeatGameEngine(this);
    }

    @Override
    public void gameLost(int gameScore) {
        NoRepeatGameFragmentDirections.ActionNoRepeatGameFragmentToGameOverFragment action = NoRepeatGameFragmentDirections.actionNoRepeatGameFragmentToGameOverFragment();
        action.setGameScore(gameScore);
        action.setPlayAgainActionId(R.id.gameOverFragment_to_noRepeatGameFragment);
        Navigation.findNavController(getView()).navigate(action);
    }
}
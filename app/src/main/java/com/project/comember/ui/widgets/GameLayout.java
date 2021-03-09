package com.project.comember.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;


public class GameLayout extends MotionLayout {

    private boolean mTouchable;

    public GameLayout(Context context) {
        this(context, null);
    }

    public GameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return !mTouchable;
//    }

    public GameButton[] getGameButtons() {
        int childCount = getChildCount();

        List<GameButton> gameButtons = new ArrayList<GameButton>();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof GameButton)
                gameButtons.add((GameButton)child);
        }

        GameButton[] gameButtonsArray = new GameButton[gameButtons.size()];

        for (int i = 0; i < gameButtons.size(); i ++)
            gameButtonsArray[i] = gameButtons.get(i);

        return gameButtonsArray;
    }

    public void setTouchable(boolean touchable) {
        this.mTouchable = touchable;
    }
}

package com.project.comember.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;



public class GameLayout extends ViewGroup {

    private static final int MAX_CHILD_COUNT = 4;

    public GameLayout(Context context) {
        this(context, null);
    }

    public GameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        //TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GameLayout, 0, 0);
    }

    public GameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public GameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //Only true for scrollable layouts
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int blockSize = Integer.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));

        setMeasuredDimension(blockSize, blockSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int leftPos = getPaddingLeft();
        final int rightPos = right - left - getPaddingRight();
        final int topPos = getPaddingTop();
        final int bottomPos = bottom - top - getPaddingBottom();

        for (int i = 0; i < MAX_CHILD_COUNT; i++) {
            View child = getChildAt(i);
            child.layout(leftPos, topPos, rightPos / 2, bottomPos / 2);
            child.setPivotX((float) rightPos / 2);
            child.setPivotY((float) bottomPos / 2);
            child.setRotation(90f * i);
        }
    }


}

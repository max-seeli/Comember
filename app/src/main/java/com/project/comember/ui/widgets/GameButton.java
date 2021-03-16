package com.project.comember.ui.widgets;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.project.comember.R;
import com.project.comember.game.GameColor;

public class GameButton extends View {

    private static final int STANDARD_STROKE_SIZE = 10;
    private static final int HIGHLIGHT_STROKE_SIZE = 20;

    private GameColor mGameColor;

    private Paint mCircleColor;
    private int mStrokeSize = STANDARD_STROKE_SIZE;

    private final Paint mMainColor;
    private final Paint mHighlightColor;
    private final Paint mBorderColor;

    public GameButton(Context context) {
        this(context, null);
    }

    public GameButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public GameButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mMainColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighlightColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderColor = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBorderColor.setColor(Color.WHITE);

        mCircleColor = mMainColor;

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameButton, defStyleAttr, defStyleRes);

        try {
            mMainColor.setColor(attributes.getColor(R.styleable.GameButton_mainColor, 0));
            mHighlightColor.setColor(attributes.getColor(R.styleable.GameButton_highlightColor, 0));
        } finally {
            attributes.recycle();
        }

        setOnTouchListener((v, event) -> {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    highlight();
                    break;
                case MotionEvent.ACTION_UP:
                    unhighlight();
                    break;
            }

            return false;
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int maxX = getWidth();
        int maxY = getHeight();
        float rectWidth = mStrokeSize - STANDARD_STROKE_SIZE / 2f;
        canvas.drawCircle(maxX, maxY, maxX, mBorderColor);
        canvas.drawCircle(maxX, maxY, maxX - mStrokeSize, mCircleColor);
        canvas.drawRect(maxX - rectWidth, 0, maxX, maxY, mBorderColor);
        canvas.drawRect(0, maxY - rectWidth, maxX, maxY, mBorderColor);
    }


    public void highlight() {
        changeActiveColor(mHighlightColor, false);
        changeStrokeSize(HIGHLIGHT_STROKE_SIZE);
    }

    public void unhighlight() {
        changeActiveColor(mMainColor, false);
        changeStrokeSize(STANDARD_STROKE_SIZE);
    }

    private void changeActiveColor(Paint color) {
        changeActiveColor(color, true);
    }

    private void changeActiveColor(Paint color, boolean inval) {
        mCircleColor = color;
        if (inval)
            invalidate();
    }

    private void changeStrokeSize(int strokeSize) {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ValueAnimator strokeAnimator = ValueAnimator.ofInt(mStrokeSize, strokeSize);
                strokeAnimator.setDuration(100);
                strokeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mStrokeSize = (int) animation.getAnimatedValue();
                        postInvalidate();
                    }
                });
                strokeAnimator.start();
            }
        });
    }

    public void setGameColor(GameColor gameColor) {
        this.mGameColor = gameColor;
    }

    public GameColor getGameColor() {
        return this.mGameColor;
    }

}

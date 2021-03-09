package com.project.comember.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.project.comember.R;
import com.project.comember.game.GameColor;

public class GameButton extends View {

    private GameColor mGameColor;

    private Paint mCircleColor;

    private Paint mMainColor;
    private Paint mHighlightColor;
    private Paint mBorderColor;

    public GameButton(Context context) {
        this(context, null);
    }

    public GameButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public GameButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mMainColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighlightColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderColor = new Paint(Paint.ANTI_ALIAS_FLAG);

        mCircleColor = mMainColor;

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameButton, defStyleAttr, defStyleRes);

        try {

            mMainColor.setColor(attributes.getColor(R.styleable.GameButton_mainColor, 0));
            mHighlightColor.setColor(attributes.getColor(R.styleable.GameButton_highlightColor, 0));
            mBorderColor.setColor(attributes.getColor(R.styleable.GameButton_borderColor, 0));
        } finally {
            attributes.recycle();
        }

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        highlight();
                        break;
                    case MotionEvent.ACTION_UP:
                        unhighlight();
                        break;
                }

                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth(), getHeight(), getWidth(), mCircleColor);
    }


    public void highlight() {
        changeActiveColor(mHighlightColor);
    }

    public void unhighlight() {
        changeActiveColor(mMainColor);
    }

    private void changeActiveColor(Paint color) {
        mCircleColor = color;
        invalidate();
    }

    public void setGameColor(GameColor gameColor) {
        this.mGameColor = gameColor;
    }

    public GameColor getGameColor() {return this.mGameColor;}

}

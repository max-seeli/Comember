package com.project.comember.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.project.comember.R;
import com.project.comember.ThreadExecutor;
import com.project.comember.game.GameColor;

import java.util.concurrent.Executor;

public class GameButton extends View {

    private static final long HIGHLIGHT_STANDARD_MILLIS = 650;

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getRight(), getBottom(), getWidth(), mCircleColor);
    }

    public void highlight() {
        Executor ex = ThreadExecutor.getExecutor();

        ex.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    changeActiveColor(mHighlightColor);
                    Thread.sleep(HIGHLIGHT_STANDARD_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    changeActiveColor(mMainColor);
                }
            }
        });
    }

    private void changeActiveColor(Paint color) {
        mCircleColor = color;
        invalidate();
    }

    public void setGameColor(GameColor gameColor) {
        this.mGameColor = gameColor;
    }
}

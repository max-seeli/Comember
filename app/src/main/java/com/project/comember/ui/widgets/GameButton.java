package com.project.comember.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.project.comember.R;

public class GameButton extends View {

    Paint mMainColor;
    Paint mHighlightColor;
    Paint mBorderColor;

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

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameButton, defStyleAttr, defStyleRes);

        try {
            mMainColor.setColor(attributes.getColor(R.styleable.GameButton_color, 0));
            mHighlightColor.setColor(attributes.getColor(R.styleable.GameButton_highlightColor, 0));
            mBorderColor.setColor(attributes.getColor(R.styleable.GameButton_borderColor, 0));
        } finally {
            attributes.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getRight(), getBottom(), getWidth(), mMainColor);
    }
}

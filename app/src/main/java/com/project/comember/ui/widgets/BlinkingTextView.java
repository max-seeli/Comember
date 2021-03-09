package com.project.comember.ui.widgets;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.Nullable;

public class BlinkingTextView extends androidx.appcompat.widget.AppCompatTextView {

    private static final int DEFAULT_BLINKING_DURATION = 600;
    private int mDuration = DEFAULT_BLINKING_DURATION;

    public BlinkingTextView(Context context) {
        this(context, null);
    }

    public BlinkingTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlinkingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startBlinking();
    }

    private void startBlinking() {
        ObjectAnimator blinkingAnimation = ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f);
        blinkingAnimation.setDuration(mDuration);
        blinkingAnimation.setRepeatMode(ValueAnimator.REVERSE);
        blinkingAnimation.setRepeatCount(ValueAnimator.INFINITE);
        blinkingAnimation.start();
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }
}

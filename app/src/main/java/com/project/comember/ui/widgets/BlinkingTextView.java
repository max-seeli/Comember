package com.project.comember.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
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


        Animation blinkingAnimation = createAnimation();

        startAnimation(blinkingAnimation);
    }

    private Animation createAnimation() {
        AlphaAnimation blinkAnimationFadeIn = new AlphaAnimation(0f, 1f);
        AlphaAnimation blinkAnimationFadeOut = new AlphaAnimation(1f, 0f);

        blinkAnimationFadeIn.setDuration(mDuration);
        blinkAnimationFadeOut.setDuration(mDuration);

        blinkAnimationFadeIn.setAnimationListener(createAlternateAnimationListener(blinkAnimationFadeOut));
        blinkAnimationFadeOut.setAnimationListener(createAlternateAnimationListener(blinkAnimationFadeIn));
        return blinkAnimationFadeOut;
    }

    private Animation.AnimationListener createAlternateAnimationListener(Animation otherAnimation) {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                startAnimation(otherAnimation);
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }
}

package com.project.comember.ui.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.project.comember.R;

import java.util.Arrays;

@SuppressLint("SetTextI18n")
public class GameScoreCounter extends AppCompatTextView {

    private int mScore = 0;

    private int colorNormal;
    private int colorProgress;

    public GameScoreCounter(@NonNull Context context) {
        this(context, null);
    }

    public GameScoreCounter(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameScoreCounter(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public GameScoreCounter(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameScoreCounter, defStyleAttr, defStyleRes);

        colorNormal = getCurrentTextColor();
        setColorProgress(attributes.getColor(R.styleable.GameScoreCounter_progressColor, Color.argb(255, 71, 71, 71)));

        this.setText(Integer.toString(mScore));
    }

    public void increment() {
        this.setText(Integer.toString(++mScore));
    }

    public void setProgress(int percent) {

        int lineHeight = getLineHeight();
        int progressPixel = percent * lineHeight / 100;

        int colorSwitchPixelIndex = progressPixel > 0 ? progressPixel - 1 : 0;

        int[] colors = new int[lineHeight];
        Arrays.fill(colors, 0, colorSwitchPixelIndex, colorProgress);
        Arrays.fill(colors, colorSwitchPixelIndex, lineHeight - 1, colorNormal);

        Bitmap progress = Bitmap.createBitmap(colors, 1, lineHeight, Bitmap.Config.ARGB_4444);
        Shader shader = new BitmapShader(progress, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        getPaint().setShader(shader);
        invalidate();
    }

    public void setColorProgress(int colorProgress) {
        this.colorProgress = colorProgress;
    }
}

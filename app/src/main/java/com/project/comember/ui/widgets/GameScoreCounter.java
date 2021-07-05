package com.project.comember.ui.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Arrays;

@SuppressLint("SetTextI18n")
public class GameScoreCounter extends AppCompatTextView {

    private int mScore = 0;

    private static final int COLOR_NORMAL = Color.WHITE;
    private static final int COLOR_PROGRESS = Color.argb(255, 71, 71, 71);

    public GameScoreCounter(@NonNull Context context) {
        this(context, null);
    }

    public GameScoreCounter(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameScoreCounter(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

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
        Arrays.fill(colors, 0, colorSwitchPixelIndex, COLOR_PROGRESS);
        Arrays.fill(colors, colorSwitchPixelIndex, lineHeight - 1, COLOR_NORMAL);

        Bitmap progress = Bitmap.createBitmap(colors, 1, lineHeight, Bitmap.Config.ARGB_4444);
        Shader shader = new BitmapShader(progress, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        getPaint().setShader(shader);
        invalidate();
    }
}

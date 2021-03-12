package com.project.comember.misc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import androidx.constraintlayout.helper.widget.Layer;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PreserveRotationLayer extends Layer {

    float[] baseRotations;

    public PreserveRotationLayer(Context context) {
        super(context);
    }

    public PreserveRotationLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreserveRotationLayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setRotation(float angle) {
        ViewParent parent = this.getParent();
        if (parent instanceof ConstraintLayout) {
            this.setRotation(angle, (ConstraintLayout) parent);
        }
    }

    private void setRotation(float angle, ConstraintLayout parent) {
        int[] childIds = getReferencedIds();
        int childCount = childIds.length;

        if (baseRotations == null) {
            baseRotations = new float[childCount];
            for (int i = 0; i < childCount; i++) {
                baseRotations[i] = parent.getViewById(childIds[i]).getRotation();
            }
            return;
        }

        super.setRotation(angle);

        for (int i = 0; i < childIds.length; i++) {
            View child = parent.getViewById(childIds[i]);
            child.setRotation(child.getRotation() + baseRotations[i]);
        }
    }
}

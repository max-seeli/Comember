package com.project.comember.misc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewParent;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

public class ClickableGroup extends Group {
    public ClickableGroup(Context context) {
        super(context);
    }

    public ClickableGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener clickListener) {
        ViewParent parent = this.getParent();
        if (parent instanceof ConstraintLayout) {
            this.setOnClickListener(clickListener, (ConstraintLayout) parent);
        }
    }

    private void setOnClickListener(OnClickListener clickListener, ConstraintLayout parent) {
        int[] childIds = getReferencedIds();

        for (int childId : childIds) {
            parent.getViewById(childId).setOnClickListener(clickListener);
        }
    }
}

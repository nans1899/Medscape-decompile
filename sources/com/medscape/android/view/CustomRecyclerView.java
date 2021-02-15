package com.medscape.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerView extends RecyclerView {
    private float lastX;
    private float lastY;
    private float xDistance;
    private float yDistance;

    public CustomRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.yDistance = 0.0f;
            this.xDistance = 0.0f;
            this.lastX = motionEvent.getX();
            this.lastY = motionEvent.getY();
        } else if (action == 2) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.xDistance += Math.abs(x - this.lastX);
            float abs = this.yDistance + Math.abs(y - this.lastY);
            this.yDistance = abs;
            this.lastX = x;
            this.lastY = y;
            if (this.xDistance > abs) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}

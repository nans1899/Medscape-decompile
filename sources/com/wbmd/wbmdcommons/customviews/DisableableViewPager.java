package com.wbmd.wbmdcommons.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class DisableableViewPager extends ViewPager {
    private float downActionX;
    private float downActionY;
    private boolean enabled = true;
    private float mXEnd;
    private float mXStart;
    private float mYEnd;
    private float mYStart;

    public DisableableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.enabled || !wasSwipeHorizontal(motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.enabled || !wasSwipeHorizontal(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public void setPagingEnabled(boolean z) {
        this.enabled = z;
    }

    private boolean wasSwipeHorizontal(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downActionX = motionEvent.getX();
            this.downActionY = motionEvent.getY();
            return false;
        } else if ((action == 1 || action == 2) && Math.abs(this.downActionY - motionEvent.getY()) < Math.abs(this.downActionX - motionEvent.getX())) {
            return true;
        } else {
            return false;
        }
    }
}

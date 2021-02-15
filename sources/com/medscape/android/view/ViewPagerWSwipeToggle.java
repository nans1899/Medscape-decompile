package com.medscape.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerWSwipeToggle extends ViewPager {
    static final int MIN_DISTANCE = 150;
    private boolean mShouldAllowSwipe = true;
    private float x1;
    private float x2;

    public ViewPagerWSwipeToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ViewPagerWSwipeToggle(Context context) {
        super(context);
    }

    public void enableSwipe() {
        this.mShouldAllowSwipe = true;
    }

    public void disableSwipe() {
        this.mShouldAllowSwipe = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mShouldAllowSwipe) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.x1 = motionEvent.getX();
        } else if (action == 1) {
            float x = motionEvent.getX();
            this.x2 = x;
            if (Math.abs(x - this.x1) > 150.0f) {
                return true;
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mShouldAllowSwipe) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.x1 = motionEvent.getX();
        } else if (action == 1) {
            float x = motionEvent.getX();
            this.x2 = x;
            if (Math.abs(x - this.x1) > 150.0f) {
                return true;
            }
        }
        return false;
    }
}

package com.appboy.ui.inappmessage.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SimpleSwipeDismissTouchListener implements View.OnTouchListener {
    private final GestureDetector mSwipeGestureListener;

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public SimpleSwipeDismissTouchListener(Context context) {
        this.mSwipeGestureListener = new GestureDetector(context, new SwipeGestureListener());
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.mSwipeGestureListener.onTouchEvent(motionEvent);
    }

    private final class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_DISTANCE_THRESHOLD = 120;
        private static final int SWIPE_VELOCITY_THRESHOLD = 90;

        private SwipeGestureListener() {
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            float x = motionEvent2.getX() - motionEvent.getX();
            if (Math.abs(x) <= Math.abs(motionEvent2.getY() - motionEvent.getY()) || Math.abs(x) <= 120.0f || Math.abs(f) <= 90.0f) {
                return false;
            }
            if (x > 0.0f) {
                SimpleSwipeDismissTouchListener.this.onSwipeRight();
                return true;
            }
            SimpleSwipeDismissTouchListener.this.onSwipeLeft();
            return true;
        }
    }
}

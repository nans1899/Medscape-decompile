package com.google.firebase.inappmessaging.display.internal;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class OnSwipeUpListener extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public boolean onSwipeUp() {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 250.0f && motionEvent.getY() - motionEvent2.getY() > 120.0f && Math.abs(f2) > 200.0f) {
            return onSwipeUp();
        }
        return false;
    }
}

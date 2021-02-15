package com.webmd.webmdrx.util;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }
}

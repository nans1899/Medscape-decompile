package com.epapyrus.plugpdf.core.gesture;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;

public interface GestureProcessorListener {
    void onAnnotLongPress(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType);

    void onAnnotSingleTapUp(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType);

    void onAnnotTouchEvent(MotionEvent motionEvent);

    void onDoubleTapUp(MotionEvent motionEvent);

    void onDown(MotionEvent motionEvent);

    void onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

    void onLongPress(MotionEvent motionEvent);

    void onScale(ScaleGestureDetector scaleGestureDetector);

    void onScaleBegin();

    void onScaleEnd();

    void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

    void onSingleTapConfirmed(MotionEvent motionEvent);
}

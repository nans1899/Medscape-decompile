package com.epapyrus.plugpdf.core.gesture;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public abstract class BaseGestureProcessor implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, ScaleGestureDetector.OnScaleGestureListener {
    protected GestureDetector mGestureDetector;
    protected GestureProcessorListener mGestureListener;
    protected ScaleGestureDetector mScaleGestureDetector;
    private GestureType mType;

    public enum GestureType {
        VIEW,
        EDIT
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public BaseGestureProcessor(Context context, BasePlugPDFDisplay basePlugPDFDisplay, GestureType gestureType) {
        this.mType = gestureType;
        this.mGestureDetector = new GestureDetector(context, this);
        this.mScaleGestureDetector = new ScaleGestureDetector(context, this);
    }

    public GestureType getType() {
        return this.mType;
    }

    public void setGestureListener(GestureProcessorListener gestureProcessorListener) {
        this.mGestureListener = gestureProcessorListener;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.mGestureListener.onDown(motionEvent);
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mGestureListener.onAnnotSingleTapUp(motionEvent, getType());
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || motionEvent2 == null) {
            return true;
        }
        this.mGestureListener.onScroll(motionEvent, motionEvent2, f, f2);
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.mGestureListener.onLongPress(motionEvent);
        this.mGestureListener.onAnnotLongPress(motionEvent, getType());
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.mGestureListener.onFling(motionEvent, motionEvent2, f, f2);
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.mGestureListener.onSingleTapConfirmed(motionEvent);
        return false;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.mGestureListener.onDoubleTapUp(motionEvent);
        return true;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        this.mGestureListener.onScale(scaleGestureDetector);
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        this.mGestureListener.onScaleBegin();
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        this.mGestureListener.onScaleEnd();
    }
}

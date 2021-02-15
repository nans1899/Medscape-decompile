package com.epapyrus.plugpdf.core.gesture;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class EditGestureProcessor extends BaseGestureProcessor {
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    public EditGestureProcessor(Context context, BasePlugPDFDisplay basePlugPDFDisplay) {
        super(context, basePlugPDFDisplay, BaseGestureProcessor.GestureType.EDIT);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        this.mGestureDetector.onTouchEvent(motionEvent);
        this.mGestureListener.onAnnotTouchEvent(motionEvent);
        return true;
    }
}

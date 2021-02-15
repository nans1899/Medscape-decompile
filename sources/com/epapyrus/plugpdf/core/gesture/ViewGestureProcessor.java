package com.epapyrus.plugpdf.core.gesture;

import android.content.Context;
import android.view.MotionEvent;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class ViewGestureProcessor extends BaseGestureProcessor {
    public ViewGestureProcessor(Context context, BasePlugPDFDisplay basePlugPDFDisplay) {
        super(context, basePlugPDFDisplay, BaseGestureProcessor.GestureType.VIEW);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        this.mGestureDetector.onTouchEvent(motionEvent);
        this.mGestureListener.onAnnotTouchEvent(motionEvent);
        return false;
    }
}

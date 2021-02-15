package com.wbmd.qxcalculator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PassThruView extends View {
    public PassThruView(Context context) {
        super(context);
    }

    public PassThruView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PassThruView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return false;
    }
}

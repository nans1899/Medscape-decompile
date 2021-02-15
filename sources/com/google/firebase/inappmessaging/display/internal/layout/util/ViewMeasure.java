package com.google.firebase.inappmessaging.display.internal.layout.util;

import android.view.View;
import android.widget.ScrollView;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class ViewMeasure {
    private boolean flex;
    private int maxHeight;
    private int maxWidth;
    private View view;

    public ViewMeasure(View view2, boolean z) {
        this.view = view2;
        this.flex = z;
    }

    public void preMeasure(int i, int i2) {
        MeasureUtils.measureAtMost(this.view, i, i2);
    }

    public View getView() {
        return this.view;
    }

    public boolean isFlex() {
        return this.flex;
    }

    public int getDesiredHeight() {
        if (this.view.getVisibility() == 8) {
            return 0;
        }
        View view2 = this.view;
        if (!(view2 instanceof ScrollView)) {
            return view2.getMeasuredHeight();
        }
        ScrollView scrollView = (ScrollView) view2;
        return scrollView.getPaddingBottom() + scrollView.getPaddingTop() + scrollView.getChildAt(0).getMeasuredHeight();
    }

    public int getDesiredWidth() {
        if (this.view.getVisibility() == 8) {
            return 0;
        }
        return this.view.getMeasuredHeight();
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setMaxDimens(int i, int i2) {
        this.maxWidth = i;
        this.maxHeight = i2;
    }
}

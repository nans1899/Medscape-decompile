package com.medscape.android.consult.models;

import android.graphics.Paint;

public class GraphicLine {
    private float mDownx = 0.0f;
    private float mDowny = 0.0f;
    private Paint mPaint;
    private float mUpx = 0.0f;
    private float mUpy = 0.0f;

    public GraphicLine(int i, float f, float f2, float f3, float f4, float f5) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(i);
        this.mPaint.setStrokeWidth(f);
        this.mDownx = f2;
        this.mDowny = f3;
        this.mUpx = f4;
        this.mUpy = f5;
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public float getDownX() {
        return this.mDownx;
    }

    public float getDownY() {
        return this.mDowny;
    }

    public float getUpX() {
        return this.mUpx;
    }

    public float getUpY() {
        return this.mUpy;
    }
}

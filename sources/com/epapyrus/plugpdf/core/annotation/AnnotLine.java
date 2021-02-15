package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class AnnotLine extends BaseAnnot {
    private PointF EndPoint = null;
    private PointF StartPoint = null;
    private int mB;
    private int mG;
    private int mLineWidth;
    private Paint mPaint = new Paint();
    private int mR;

    public AnnotLine(Context context) {
        super(context, "LINE");
        setScale(1.0f);
    }

    public void setStartPoint(PointF pointF) {
        this.StartPoint = pointF;
    }

    public void setSrartPoint(float f, float f2) {
        this.StartPoint = new PointF(f, f2);
    }

    public PointF getStartPoint() {
        return this.StartPoint;
    }

    public void setEndPoint(PointF pointF) {
        this.EndPoint = pointF;
    }

    public void setEndPoint(float f, float f2) {
        this.EndPoint = new PointF(f, f2);
    }

    public PointF getEndPoint() {
        return this.EndPoint;
    }

    public void setARGB(int i, int i2, int i3, int i4) {
        this.mAlpha = i;
        this.mR = i2;
        this.mG = i3;
        this.mB = i4;
    }

    public int getARGB() {
        return Color.argb(this.mAlpha, this.mR, this.mG, this.mB);
    }

    public void setLineWidth(int i) {
        this.mLineWidth = i;
    }

    public int getLineWidth() {
        return this.mLineWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawLine(canvas);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawLine(Canvas canvas) {
        if (this.EndPoint != null && this.StartPoint != null) {
            loadPaint();
            canvas.drawLine(this.StartPoint.x * this.mScale, this.StartPoint.y * this.mScale, this.EndPoint.x * this.mScale, this.EndPoint.y * this.mScale, this.mPaint);
        }
    }

    private void loadPaint() {
        this.mPaint.setARGB(this.mAlpha, this.mR, this.mG, this.mB);
        this.mPaint.setStrokeWidth((float) this.mLineWidth);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }
}

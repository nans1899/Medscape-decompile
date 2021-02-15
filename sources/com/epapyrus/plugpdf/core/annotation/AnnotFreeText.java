package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class AnnotFreeText extends BaseAnnot {
    public int mAlpha;
    public int mB;
    private String mFont = "Arial";
    public int mG;
    private Paint mPaint = new Paint();
    private PointF mPosition = new PointF();
    public int mR;
    private String mText;
    private int mTextSize;

    /* access modifiers changed from: protected */
    public void drawText(Canvas canvas) {
    }

    public AnnotFreeText(Context context) {
        super(context, "FREE_TEXT");
        setScale(1.0f);
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

    public void setTextSize(int i) {
        this.mTextSize = i;
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setPosition(PointF pointF) {
        this.mPosition = pointF;
    }

    public void setPosition(int i, int i2) {
        this.mPosition.x = (float) i;
        this.mPosition.y = (float) i2;
    }

    public PointF getPosition() {
        return this.mPosition;
    }

    public void setText(String str) {
        this.mText = str;
    }

    public String getText() {
        return this.mText;
    }

    public void setFont(String str) {
        this.mFont = str;
    }

    public String getFont() {
        return this.mFont;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawText(canvas);
        super.onDraw(canvas);
    }

    private void loadPaint() {
        this.mPaint.setARGB(this.mAlpha, this.mR, this.mG, this.mB);
        this.mPaint.setStrokeWidth((float) this.mTextSize);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }
}

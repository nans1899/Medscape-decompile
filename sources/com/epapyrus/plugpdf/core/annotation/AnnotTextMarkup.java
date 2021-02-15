package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

public abstract class AnnotTextMarkup extends BaseAnnot {
    protected int mB;
    protected int mG;
    protected Paint mPaint = new Paint();
    protected List<Object> mQtps = new ArrayList();
    protected int mR;
    protected RectF mRect = new RectF();

    /* access modifiers changed from: protected */
    public abstract void drawTextMarkup(Canvas canvas);

    public AnnotTextMarkup(Context context, String str) {
        super(context, str);
    }

    public void setQuadPoints(double[] dArr) {
        for (double d : dArr) {
            this.mQtps.add(Float.valueOf((float) d));
        }
    }

    public RectF[] getRects() {
        RectF[] rectFArr = new RectF[(this.mQtps.size() / 8)];
        for (int i = 0; i < this.mQtps.size(); i += 8) {
            rectFArr[i / 8] = new RectF(((Float) this.mQtps.get(i)).floatValue(), ((Float) this.mQtps.get(i + 7)).floatValue(), ((Float) this.mQtps.get(i + 6)).floatValue(), ((Float) this.mQtps.get(i + 1)).floatValue());
        }
        return rectFArr;
    }

    public void setARGB(int i, int i2, int i3, int i4) {
        this.mAlpha = i;
        this.mR = i2;
        this.mG = i3;
        this.mB = i4;
    }

    public int[] getARGB() {
        return new int[]{this.mAlpha, this.mR, this.mG, this.mB};
    }

    /* access modifiers changed from: protected */
    public void loadPaint() {
        this.mPaint.setARGB(this.mAlpha, this.mR, this.mG, this.mB);
        if (this.mRect.height() > 0.0f) {
            this.mPaint.setStrokeWidth(this.mRect.height() / 10.0f);
        } else {
            this.mPaint.setStrokeWidth((-this.mRect.height()) / 10.0f);
        }
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }
}

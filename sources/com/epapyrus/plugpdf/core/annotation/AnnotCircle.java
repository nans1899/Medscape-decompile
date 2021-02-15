package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.ArrayList;

public class AnnotCircle extends BaseAnnot {
    private int mB;
    private ArrayList<Integer> mDashPattern = new ArrayList<>();
    private Paint mFillPaint = new Paint();
    private int mG;
    private int mICB = -1;
    private int mICG = -1;
    private int mICR = -1;
    private boolean mIsInnerTransparent;
    private int mLineWidth;
    private int mR;
    private RectF mRect = new RectF();
    private Paint mStrokePaint = new Paint();

    public AnnotCircle(Context context) {
        super(context, "CIRCLE");
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

    public void setInteriorARGB(int i, int i2, int i3, int i4) {
        this.mAlpha = i;
        this.mICR = i2;
        this.mICG = i3;
        this.mICB = i4;
    }

    public int getInteriorARGB() {
        return Color.argb(this.mAlpha, this.mICR, this.mICG, this.mICB);
    }

    public void setLineWidth(int i) {
        this.mLineWidth = i;
    }

    public int getLineWidth() {
        return this.mLineWidth;
    }

    public void addDashedPattern(int i) {
        this.mDashPattern.add(Integer.valueOf(i));
    }

    public void setOvalRect(RectF rectF) {
        this.mRect = new RectF(Math.min(rectF.left, rectF.right), Math.min(rectF.top, rectF.bottom), Math.max(rectF.left, rectF.right), Math.max(rectF.top, rectF.bottom));
    }

    public RectF getOvalRect() {
        return this.mRect;
    }

    public void setInnerTransparent(boolean z) {
        this.mIsInnerTransparent = z;
    }

    public boolean isInnerTransparent() {
        return this.mIsInnerTransparent;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawOval(canvas);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawOval(Canvas canvas) {
        if (!(this.mICR == -1 || this.mICG == -1 || this.mICB == -1 || this.mIsInnerTransparent)) {
            RectF rectF = new RectF((this.mRect.left + ((float) this.mLineWidth)) * this.mScale, (this.mRect.top + ((float) this.mLineWidth)) * this.mScale, (this.mRect.right - ((float) this.mLineWidth)) * this.mScale, (this.mRect.bottom - ((float) this.mLineWidth)) * this.mScale);
            this.mFillPaint.setARGB(this.mAlpha, this.mICR, this.mICG, this.mICB);
            this.mFillPaint.setAntiAlias(true);
            this.mFillPaint.setStyle(Paint.Style.FILL);
            canvas.drawOval(rectF, this.mFillPaint);
        }
        RectF rectF2 = new RectF((this.mRect.left + ((float) this.mLineWidth)) * this.mScale, (this.mRect.top + ((float) this.mLineWidth)) * this.mScale, (this.mRect.right - ((float) this.mLineWidth)) * this.mScale, (this.mRect.bottom - ((float) this.mLineWidth)) * this.mScale);
        this.mStrokePaint.setARGB(this.mAlpha, this.mR, this.mG, this.mB);
        this.mStrokePaint.setStrokeWidth(((float) this.mLineWidth) * this.mScale);
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        if (this.mDashPattern.size() > 0) {
            float[] fArr = new float[this.mDashPattern.size()];
            for (int i = 0; i < this.mDashPattern.size(); i++) {
                fArr[i] = ((float) this.mDashPattern.get(i).intValue()) * this.mScale;
            }
            this.mStrokePaint.setPathEffect(new DashPathEffect(fArr, 0.0f));
        }
        canvas.drawOval(rectF2, this.mStrokePaint);
    }
}

package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolEraser;

public abstract class BaseAnnot extends View {
    private boolean isMovable;
    protected int mAlpha;
    protected Bitmap mApBitmap = null;
    protected RectF mBbox = null;
    protected RectF mLayout = new RectF();
    protected int mOid = -1;
    protected int mPageIdx;
    protected float mScale;
    protected boolean mShowBBox;
    protected String mType;

    public BaseAnnot(Context context, String str) {
        super(context);
        setBackgroundColor(0);
        this.mShowBBox = false;
        this.mType = str;
        setObjID(-1);
    }

    public int getObjID() {
        return this.mOid;
    }

    public void setObjID(int i) {
        this.mOid = i;
    }

    public boolean isValid() {
        return getObjID() != -1;
    }

    public int getPageIdx() {
        return this.mPageIdx;
    }

    public void setPageIdx(int i) {
        this.mPageIdx = i;
    }

    public String getType() {
        return this.mType;
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public float getScale() {
        return this.mScale;
    }

    public void setOpacity(int i) {
        this.mAlpha = i;
    }

    public int getOpacity() {
        return this.mAlpha;
    }

    public void setBBox(float f, float f2, float f3, float f4) {
        this.mBbox = new RectF(f, f2, f3, f4);
    }

    public RectF getBBox() {
        return this.mBbox;
    }

    public void setMovable(boolean z) {
        this.isMovable = z;
    }

    public boolean isMovable() {
        return this.isMovable;
    }

    public void showBBox(boolean z) {
        this.mShowBBox = z;
    }

    public boolean isContains(float f, float f2) {
        if (this.mBbox == null) {
            return false;
        }
        RectF rectF = new RectF(this.mBbox);
        if (rectF.left > rectF.right) {
            float f3 = rectF.left;
            rectF.left = rectF.right;
            rectF.right = f3;
        }
        if (rectF.top > rectF.bottom) {
            float f4 = rectF.top;
            rectF.top = rectF.bottom;
            rectF.bottom = f4;
        }
        return rectF.contains(f, f2);
    }

    private void drawBBox(Canvas canvas) {
        if (this.mBbox != null) {
            Paint selectedItemPaint = AnnotToolEraser.getSelectedItemPaint();
            if (selectedItemPaint == null) {
                selectedItemPaint = new Paint();
                selectedItemPaint.setStrokeWidth(2.0f);
                selectedItemPaint.setStyle(Paint.Style.STROKE);
                selectedItemPaint.setColor(-16711936);
            }
            Canvas canvas2 = canvas;
            canvas2.drawRect(this.mBbox.left * this.mScale, this.mBbox.top * this.mScale, this.mBbox.right * this.mScale, this.mBbox.bottom * this.mScale, selectedItemPaint);
        }
    }

    public void debugLog() {
        Log.d("PlugPDF", "[DEBUG] Annot BBox(" + (this.mBbox.left * this.mScale) + "," + (this.mBbox.top * this.mScale) + "," + (this.mBbox.right * this.mScale) + "," + (this.mBbox.bottom * this.mScale) + ") scale(" + this.mScale + ")");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        RectF rectF;
        if (this.mShowBBox) {
            drawBBox(canvas);
        }
        if (getApBitmap() != null && (rectF = this.mBbox) != null) {
            this.mLayout.left = rectF.left * this.mScale;
            this.mLayout.top = this.mBbox.top * this.mScale;
            this.mLayout.right = this.mBbox.right * this.mScale;
            this.mLayout.bottom = this.mBbox.bottom * this.mScale;
            canvas.drawBitmap(getApBitmap(), (Rect) null, this.mLayout, (Paint) null);
        }
    }

    public void setApBitmap(Bitmap bitmap) {
        this.mApBitmap = bitmap;
    }

    public Bitmap getApBitmap() {
        return this.mApBitmap;
    }

    public void setApIcon(Bitmap bitmap) {
        this.mApBitmap = bitmap;
    }

    public Bitmap getIcon() {
        return this.mApBitmap;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.mApBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mApBitmap.recycle();
        }
    }
}

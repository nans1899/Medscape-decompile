package com.medscape.android.slideshow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import com.medscape.android.R;

public class ToolTipDrawable extends Drawable {
    private int mArrowHeight;
    private int mArrowStartPosition = -1;
    private int mArrowWidth;
    private Path mBubbleArrow;
    private int mContainerHeight;
    private RectF mContainerRect;
    private int mContainerWidth;
    private Context mContext;
    private float mCornerRadius;
    private boolean mIsBottomBubble;
    private boolean mIsPaintBorderVisible;
    private int mMarginRight = 0;
    private Rect mPadding = new Rect();
    private Paint mPaint;
    private Paint mPaintBorder;

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public ToolTipDrawable(Context context) {
        this.mContext = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(ContextCompat.getColor(this.mContext, R.color.white_tool_bgr));
        Paint paint2 = new Paint();
        this.mPaintBorder = paint2;
        paint2.setAntiAlias(true);
        this.mPaintBorder.setStyle(Paint.Style.STROKE);
        this.mPaintBorder.setStrokeWidth(1.0f);
        this.mPaintBorder.setColor(ContextCompat.getColor(this.mContext, R.color.medscape_blue));
        this.mCornerRadius = 0.0f;
        setArrowWidth(20);
        setArrowHeight(20);
        this.mIsBottomBubble = true;
        this.mArrowStartPosition = -1;
        this.mMarginRight = 0;
    }

    public ToolTipDrawable(Context context, int i) {
        this.mContext = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        try {
            context.getResources().getValue(i, new TypedValue(), true);
            this.mPaint.setColor(ContextCompat.getColor(this.mContext, i));
        } catch (Exception unused) {
            this.mPaint.setColor(ContextCompat.getColor(this.mContext, R.color.white_tool_bgr));
        }
        Paint paint2 = new Paint();
        this.mPaintBorder = paint2;
        paint2.setAntiAlias(true);
        this.mPaintBorder.setStyle(Paint.Style.STROKE);
        this.mPaintBorder.setStrokeWidth(1.0f);
        this.mPaintBorder.setColor(ContextCompat.getColor(this.mContext, R.color.medscape_blue));
        this.mCornerRadius = 0.0f;
        setArrowWidth(20);
        setArrowHeight(20);
        this.mIsBottomBubble = true;
        this.mArrowStartPosition = -1;
        this.mMarginRight = 0;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPadding.left = i;
        this.mPadding.top = i2;
        this.mPadding.right = i3;
        this.mPadding.bottom = i4;
    }

    public void setCornerRadius(float f) {
        this.mCornerRadius = f;
    }

    public void setArrowWidth(int i) {
        this.mArrowWidth = i;
    }

    public void setArrowHeight(int i) {
        this.mArrowHeight = i;
    }

    public void setPaintBorderVisibility(boolean z) {
        this.mIsPaintBorderVisible = z;
    }

    public void setIsBottomBubble(boolean z) {
        this.mIsBottomBubble = z;
    }

    public void setArrowStartPosition(int i) {
        this.mArrowStartPosition = i;
    }

    public void setMarginRight(int i) {
        this.mMarginRight = i;
    }

    public void draw(Canvas canvas) {
        int i;
        RectF rectF = new RectF(0.0f, (float) this.mArrowHeight, (float) (this.mContainerWidth - this.mMarginRight), (float) this.mContainerHeight);
        this.mContainerRect = rectF;
        int i2 = (int) this.mCornerRadius;
        int i3 = this.mArrowStartPosition;
        if (i3 == -1) {
            i = (((int) rectF.width()) / 2) - this.mArrowWidth;
        } else {
            i = i3 - this.mArrowWidth;
        }
        if (this.mIsBottomBubble) {
            drawBottomBubbleArrow(i2, i);
        } else {
            drawTopBubbleArrow(i2, i);
        }
        canvas.drawPath(this.mBubbleArrow, this.mPaint);
        if (this.mIsPaintBorderVisible) {
            canvas.drawPath(this.mBubbleArrow, this.mPaintBorder);
        }
    }

    private void drawBottomBubbleArrow(int i, int i2) {
        Path path = new Path();
        this.mBubbleArrow = path;
        float f = (float) i;
        path.moveTo(f, f);
        this.mBubbleArrow.lineTo((float) i2, f);
        this.mBubbleArrow.lineTo((float) ((this.mArrowWidth / 2) + i2), 0.0f);
        this.mBubbleArrow.lineTo((float) (i2 + this.mArrowWidth), f);
        this.mBubbleArrow.lineTo(this.mContainerRect.width() - f, f);
        float f2 = (float) (i * 2);
        float f3 = (float) (i * 3);
        this.mBubbleArrow.arcTo(new RectF(this.mContainerRect.right - f2, f, this.mContainerRect.right, f3), 270.0f, 90.0f);
        this.mBubbleArrow.lineTo(this.mContainerRect.width(), this.mContainerRect.height() - f);
        this.mBubbleArrow.arcTo(new RectF(this.mContainerRect.right - f2, this.mContainerRect.bottom - f2, this.mContainerRect.right, this.mContainerRect.bottom), 0.0f, 90.0f);
        this.mBubbleArrow.lineTo(f, this.mContainerRect.bottom);
        this.mBubbleArrow.arcTo(new RectF(0.0f, this.mContainerRect.bottom - f2, f2, this.mContainerRect.bottom), 90.0f, 90.0f);
        this.mBubbleArrow.lineTo(0.0f, f2);
        this.mBubbleArrow.arcTo(new RectF(0.0f, f, f2, f3), 180.0f, 90.0f);
        this.mBubbleArrow.close();
    }

    private void drawTopBubbleArrow(int i, int i2) {
        Path path = new Path();
        this.mBubbleArrow = path;
        float f = (float) i;
        path.moveTo(f, f);
        this.mBubbleArrow.lineTo(this.mContainerRect.width() - f, f);
        float f2 = (float) (i * 2);
        float f3 = (float) (i * 3);
        this.mBubbleArrow.arcTo(new RectF(this.mContainerRect.right - f2, f, this.mContainerRect.right, f3), 270.0f, 90.0f);
        this.mBubbleArrow.lineTo(this.mContainerRect.width(), this.mContainerRect.height() - f);
        this.mBubbleArrow.arcTo(new RectF(this.mContainerRect.right - f2, this.mContainerRect.bottom - f2, this.mContainerRect.right, this.mContainerRect.bottom), 0.0f, 90.0f);
        this.mBubbleArrow.lineTo(f, this.mContainerRect.bottom);
        this.mBubbleArrow.arcTo(new RectF(0.0f, this.mContainerRect.bottom - f2, f2, this.mContainerRect.bottom), 90.0f, 90.0f);
        this.mBubbleArrow.lineTo(0.0f, f2);
        this.mBubbleArrow.arcTo(new RectF(0.0f, f, f2, f3), 180.0f, 90.0f);
        this.mBubbleArrow.moveTo((float) i2, this.mContainerRect.bottom);
        this.mBubbleArrow.lineTo((float) ((this.mArrowWidth / 2) + i2), this.mContainerRect.bottom + f);
        this.mBubbleArrow.lineTo((float) (i2 + this.mArrowWidth), this.mContainerRect.bottom);
        this.mBubbleArrow.close();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.mContainerHeight = getBounds().height() - this.mArrowHeight;
        this.mContainerWidth = rect.width();
        super.onBoundsChange(rect);
    }

    public boolean getPadding(Rect rect) {
        rect.set(this.mPadding);
        rect.bottom += this.mArrowHeight;
        return true;
    }
}

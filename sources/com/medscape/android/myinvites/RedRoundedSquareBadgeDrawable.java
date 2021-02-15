package com.medscape.android.myinvites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.medscape.android.R;

public class RedRoundedSquareBadgeDrawable extends Drawable {
    private Paint mBadgePaint;
    private String mCount;
    private BadgeDrawableState mState;
    private Paint mTextPaint;
    private float mTextSizeDefault;
    private float mTextSizeSmaller;
    private float mTextSizeSmallest;
    private Rect mTxtRect;
    private boolean mWillDraw;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public RedRoundedSquareBadgeDrawable(BadgeDrawableState badgeDrawableState) {
        this.mTxtRect = new Rect();
        this.mCount = "";
        this.mWillDraw = false;
        this.mState = badgeDrawableState;
        this.mTextSizeDefault = 18.0f;
        this.mTextSizeSmaller = 14.0f;
        this.mTextSizeSmallest = 12.0f;
        Paint paint = new Paint();
        this.mBadgePaint = paint;
        paint.setColor(Color.parseColor("#d33a34"));
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mTextPaint = paint2;
        paint2.setColor(-1);
        this.mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mTextPaint.setTextSize(this.mTextSizeDefault);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public RedRoundedSquareBadgeDrawable(Context context) {
        this.mTxtRect = new Rect();
        this.mCount = "";
        this.mWillDraw = false;
        this.mState = new BadgeDrawableState();
        this.mTextSizeDefault = context.getResources().getDimension(R.dimen.myinvites_badge_text_height_default);
        this.mTextSizeSmaller = context.getResources().getDimension(R.dimen.myinvites_badge_text_height_small);
        this.mTextSizeSmallest = context.getResources().getDimension(R.dimen.myinvites_badge_text_height_verysmall);
        Paint paint = new Paint();
        this.mBadgePaint = paint;
        paint.setColor(Color.parseColor("#d33a34"));
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mTextPaint = paint2;
        paint2.setColor(-1);
        this.mTextPaint.setTypeface(Typeface.DEFAULT);
        this.mTextPaint.setTextSize(this.mTextSizeSmaller);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw(Canvas canvas) {
        if (this.mWillDraw) {
            Rect copyBounds = copyBounds();
            float f = (float) (copyBounds.right - copyBounds.left);
            float f2 = (float) (copyBounds.bottom - copyBounds.top);
            Paint paint = this.mTextPaint;
            String str = this.mCount;
            paint.getTextBounds(str, 0, str.length(), this.mTxtRect);
            int i = this.mTxtRect.bottom;
            int i2 = this.mTxtRect.top;
            float f3 = this.mCount.length() == 2 ? 20.0f : 15.0f;
            if (this.mCount.length() > 2) {
                this.mTextPaint.setTextSize(this.mTextSizeSmallest);
                this.mCount = "99+";
                f3 = 23.0f;
            }
            float min = (Math.min(f, f2) / 4.0f) + f3;
            float f4 = (f - min) + 6.2f + 10.0f;
            float f5 = (min - 9.5f) - 15.0f;
            canvas.drawCircle(f4, f5, min, this.mBadgePaint);
            canvas.drawText(this.mCount, f4, f5 + (((float) (this.mTxtRect.bottom - this.mTxtRect.top)) / 2.0f), this.mTextPaint);
        }
    }

    public void setCount(int i) {
        this.mCount = Integer.toString(i);
        this.mWillDraw = i > 0;
        invalidateSelf();
    }

    public Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mState.mChangingConfigurations;
    }

    class BadgeDrawableState extends Drawable.ConstantState {
        int mChangingConfigurations;

        BadgeDrawableState() {
        }

        public Drawable newDrawable() {
            return new RedRoundedSquareBadgeDrawable(this);
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }
    }
}

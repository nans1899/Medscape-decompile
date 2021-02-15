package com.medscape.android.myinvites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.medscape.android.R;

public class RedRoundBadgeDrawable extends Drawable {
    private Paint mBadgePaint;
    private String mCount;
    private BadgeDrawableState mState;
    private Paint mTextPaint;
    private float mTextSize;
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

    public RedRoundBadgeDrawable(BadgeDrawableState badgeDrawableState) {
        this.mTxtRect = new Rect();
        this.mCount = "";
        this.mWillDraw = false;
        this.mState = badgeDrawableState;
        this.mTextSize = 18.0f;
        this.mTextSizeSmaller = 14.0f;
        this.mTextSizeSmallest = 12.0f;
        Paint paint = new Paint();
        this.mBadgePaint = paint;
        paint.setColor(-65536);
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mTextPaint = paint2;
        paint2.setColor(-1);
        this.mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public RedRoundBadgeDrawable(Context context) {
        this.mTxtRect = new Rect();
        this.mCount = "";
        this.mWillDraw = false;
        this.mState = new BadgeDrawableState();
        this.mTextSize = context.getResources().getDimension(R.dimen.myinvites_badge_text_height_default);
        this.mTextSizeSmaller = context.getResources().getDimension(R.dimen.myinvites_badge_text_height_small);
        this.mTextSizeSmallest = context.getResources().getDimension(R.dimen.myinvites_badge_text_height_verysmall);
        Paint paint = new Paint();
        this.mBadgePaint = paint;
        paint.setColor(-65536);
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mTextPaint = paint2;
        paint2.setColor(-1);
        this.mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw(Canvas canvas) {
        if (this.mWillDraw) {
            Rect bounds = getBounds();
            float f = (float) (bounds.right - bounds.left);
            float min = ((Math.min(f, (float) (bounds.bottom - bounds.top)) / 2.0f) - 1.0f) / 1.8f;
            float f2 = (f - min) - 1.0f;
            float f3 = 1.0f + min;
            canvas.drawCircle(f2, f3, min, this.mBadgePaint);
            Paint paint = this.mTextPaint;
            String str = this.mCount;
            paint.getTextBounds(str, 0, str.length(), this.mTxtRect);
            float f4 = f3 + (((float) (this.mTxtRect.bottom - this.mTxtRect.top)) / 2.0f);
            if (this.mCount.length() > 3) {
                this.mTextPaint.setTextSize(this.mTextSizeSmallest);
                this.mCount = "999+";
            } else if (this.mCount.length() > 2) {
                this.mTextPaint.setTextSize(this.mTextSizeSmaller);
            }
            canvas.drawText(this.mCount, f2, f4, this.mTextPaint);
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
            return new RedRoundBadgeDrawable(this);
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }
    }
}

package com.facebook.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.facebook.shimmer.Shimmer;

public class ShimmerFrameLayout extends FrameLayout {
    private final Paint mContentPaint = new Paint();
    private final ShimmerDrawable mShimmerDrawable = new ShimmerDrawable();
    private boolean mShowShimmer = true;

    public ShimmerFrameLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public ShimmerFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ShimmerFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public ShimmerFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        this.mShimmerDrawable.setCallback(this);
        if (attributeSet == null) {
            setShimmer(new Shimmer.AlphaHighlightBuilder().build());
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerFrameLayout, 0, 0);
        try {
            setShimmer(((!obtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_shimmer_colored) || !obtainStyledAttributes.getBoolean(R.styleable.ShimmerFrameLayout_shimmer_colored, false)) ? new Shimmer.AlphaHighlightBuilder() : new Shimmer.ColorHighlightBuilder()).consumeAttributes(obtainStyledAttributes).build());
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public ShimmerFrameLayout setShimmer(Shimmer shimmer) {
        this.mShimmerDrawable.setShimmer(shimmer);
        if (shimmer == null || !shimmer.clipToChildren) {
            setLayerType(0, (Paint) null);
        } else {
            setLayerType(2, this.mContentPaint);
        }
        return this;
    }

    public void startShimmer() {
        this.mShimmerDrawable.startShimmer();
    }

    public void stopShimmer() {
        this.mShimmerDrawable.stopShimmer();
    }

    public boolean isShimmerStarted() {
        return this.mShimmerDrawable.isShimmerStarted();
    }

    public void showShimmer(boolean z) {
        if (!this.mShowShimmer) {
            this.mShowShimmer = true;
            if (z) {
                startShimmer();
            }
        }
    }

    public void hideShimmer() {
        if (this.mShowShimmer) {
            stopShimmer();
            this.mShowShimmer = false;
            invalidate();
        }
    }

    public boolean isShimmerVisible() {
        return this.mShowShimmer;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mShimmerDrawable.setBounds(0, 0, getWidth(), getHeight());
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mShimmerDrawable.maybeStartShimmer();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopShimmer();
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mShowShimmer) {
            this.mShimmerDrawable.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mShimmerDrawable;
    }
}

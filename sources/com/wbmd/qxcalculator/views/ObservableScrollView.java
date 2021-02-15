package com.wbmd.qxcalculator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    /* access modifiers changed from: private */
    public boolean mIsScrolling;
    /* access modifiers changed from: private */
    public boolean mIsTouching;
    /* access modifiers changed from: private */
    public OnScrollListener mOnScrollListener;
    /* access modifiers changed from: private */
    public Runnable mScrollingRunnable;

    public interface OnScrollListener {
        void onEndScroll(ObservableScrollView observableScrollView);

        void onScrollChanged(ObservableScrollView observableScrollView, int i, int i2, int i3, int i4);
    }

    public ObservableScrollView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        OnScrollListener onScrollListener;
        int action = motionEvent.getAction();
        if (action == 2) {
            this.mIsTouching = true;
            this.mIsScrolling = true;
        } else if (action == 1) {
            if (this.mIsTouching && !this.mIsScrolling && (onScrollListener = this.mOnScrollListener) != null) {
                onScrollListener.onEndScroll(this);
            }
            this.mIsTouching = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (Math.abs(i3 - i) > 0) {
            Runnable runnable = this.mScrollingRunnable;
            if (runnable != null) {
                removeCallbacks(runnable);
            }
            AnonymousClass1 r0 = new Runnable() {
                public void run() {
                    if (ObservableScrollView.this.mIsScrolling && !ObservableScrollView.this.mIsTouching && ObservableScrollView.this.mOnScrollListener != null) {
                        ObservableScrollView.this.mOnScrollListener.onEndScroll(ObservableScrollView.this);
                    }
                    boolean unused = ObservableScrollView.this.mIsScrolling = false;
                    Runnable unused2 = ObservableScrollView.this.mScrollingRunnable = null;
                }
            };
            this.mScrollingRunnable = r0;
            postDelayed(r0, 200);
        }
        OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(this, i, i2, i3, i4);
        }
    }

    public OnScrollListener getOnScrollListener() {
        return this.mOnScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }
}

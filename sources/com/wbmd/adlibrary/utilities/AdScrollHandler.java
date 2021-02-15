package com.wbmd.adlibrary.utilities;

import android.app.Activity;
import android.os.Handler;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.wbmdcommons.callbacks.IScrollEvent;

public class AdScrollHandler {
    private static final String TAG = AdScrollHandler.class.getSimpleName();
    /* access modifiers changed from: private */
    public int mCurrentScrollY = 0;
    private int mDeviceHeight;
    private int mDeviceHeightIntervals;
    private int mDeviceHeightsReached = 1;
    private boolean mIsAdInFirstSection = false;
    private float mPreCacheBuffer = 0.33f;
    private float mPreviousDeviceHeight = 0.0f;
    /* access modifiers changed from: private */
    public IScrollEvent mScrollEvent;
    private Handler mScrollPauseHandler;
    private boolean mShouldFirePreCache = true;
    private int mStatusBarHeight = 63;

    public void isAdInFirstSection(boolean z) {
        this.mIsAdInFirstSection = z;
    }

    public AdScrollHandler(Activity activity, RecyclerView recyclerView, int i, float f, IScrollEvent iScrollEvent) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mDeviceHeight = displayMetrics.heightPixels;
        this.mPreCacheBuffer = f;
        this.mScrollEvent = iScrollEvent;
        this.mDeviceHeightIntervals = i;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                AdScrollHandler adScrollHandler = AdScrollHandler.this;
                int unused = adScrollHandler.mCurrentScrollY = adScrollHandler.mCurrentScrollY + i2;
                AdScrollHandler adScrollHandler2 = AdScrollHandler.this;
                adScrollHandler2.onScrolledAction(adScrollHandler2.mCurrentScrollY);
            }
        });
    }

    public AdScrollHandler(RecyclerView recyclerView, final int i, IScrollEvent iScrollEvent) {
        this.mScrollEvent = iScrollEvent;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    AdScrollHandler.this.stopScrollPauseHandler();
                    AdScrollHandler.this.startScrollPauseHandler(i);
                    return;
                }
                AdScrollHandler.this.stopScrollPauseHandler();
            }
        });
    }

    /* access modifiers changed from: private */
    public void onScrolledAction(int i) {
        int i2 = this.mDeviceHeight;
        int i3 = this.mDeviceHeightIntervals;
        if (i2 > 0) {
            if (i > i2 * this.mDeviceHeightsReached * i3) {
                this.mPreviousDeviceHeight = (float) i;
                this.mScrollEvent.onScrollThresholdReached();
                this.mShouldFirePreCache = true;
                this.mDeviceHeightsReached++;
            }
            if (this.mShouldFirePreCache && this.mIsAdInFirstSection && this.mDeviceHeightsReached == 1) {
                this.mShouldFirePreCache = false;
            }
            if (((float) i) > this.mPreviousDeviceHeight + (((float) (this.mDeviceHeight * this.mDeviceHeightIntervals)) * this.mPreCacheBuffer) && this.mShouldFirePreCache) {
                this.mScrollEvent.onPreCacheEvent();
                this.mShouldFirePreCache = false;
            }
        }
    }

    public void setStatusBarHeight(int i) {
        this.mStatusBarHeight = i;
    }

    /* access modifiers changed from: private */
    public void stopScrollPauseHandler() {
        Handler handler = this.mScrollPauseHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
            this.mScrollPauseHandler = null;
        }
    }

    /* access modifiers changed from: private */
    public void startScrollPauseHandler(int i) {
        Handler handler = new Handler();
        this.mScrollPauseHandler = handler;
        handler.postDelayed(new Runnable() {
            public void run() {
                AdScrollHandler.this.mScrollEvent.onScrollThresholdReached();
            }
        }, (long) i);
    }
}

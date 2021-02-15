package com.webmd.wbmdomnituremanager;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.wbmdcommons.customviews.OffSetScrollView;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.HashMap;

public class WBMDOmniturePaginationHandler {
    private static final String ON_SCROLL_PAGE_NAME_SUFFIX = "pg-n-swipe";
    private static final String TAG = WBMDOmniturePaginationHandler.class.getSimpleName();
    /* access modifiers changed from: private */
    public boolean debugCalled = false;
    CountDownTimer delayCounter = new CountDownTimer(1000, 1000) {
        public void onTick(long j) {
        }

        public void onFinish() {
            if (WBMDOmniturePaginationHandler.this.mListener != null) {
                WBMDOmniturePaginationHandler.access$1308(WBMDOmniturePaginationHandler.this);
                WBMDOmniturePaginationHandler wBMDOmniturePaginationHandler = WBMDOmniturePaginationHandler.this;
                int unused = wBMDOmniturePaginationHandler.sendPosition = wBMDOmniturePaginationHandler.mCurrentScrollY;
                boolean unused2 = WBMDOmniturePaginationHandler.this.debugCalled = false;
                WBMDOmniturePaginationHandler.this.mListener.sendOmniture(WBMDOmniturePaginationHandler.this.medscapePageCount, WBMDOmniturePaginationHandler.this.mScrollCount);
            }
        }
    };
    private final Object lock = new Object();
    private Activity mActivity;
    /* access modifiers changed from: private */
    public int mCurrentScrollY = 0;
    private HashMap<String, String> mData;
    /* access modifiers changed from: private */
    public int mDeviceHeight;
    private int mDeviceHeightOffset = 0;
    /* access modifiers changed from: private */
    public WBMDPaginationListener mListener;
    private NestedScrollView mNestedScrollView;
    private int mNextSectionHeight = 0;
    private String mPageName;
    /* access modifiers changed from: private */
    public int mPageNoSent;
    private double mPageSize = 1.0d;
    private int mPreviousSectionHeight = 0;
    private RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public int mScrollCount;
    private int mScrollState = -1;
    /* access modifiers changed from: private */
    public OffSetScrollView mScrollView;
    private String mSectionName;
    private String mTopic;
    /* access modifiers changed from: private */
    public int medscapePageCount = 0;
    /* access modifiers changed from: private */
    public int sendPosition = 0;

    static /* synthetic */ int access$1308(WBMDOmniturePaginationHandler wBMDOmniturePaginationHandler) {
        int i = wBMDOmniturePaginationHandler.medscapePageCount;
        wBMDOmniturePaginationHandler.medscapePageCount = i + 1;
        return i;
    }

    public WBMDOmniturePaginationHandler(Activity activity, RecyclerView recyclerView, String str, String str2) {
        new WBMDOmniturePaginationHandler(activity, recyclerView, str, str2, (String) null);
    }

    public WBMDOmniturePaginationHandler(Activity activity, RecyclerView recyclerView, String str, String str2, String str3) {
        this.mActivity = activity;
        this.mSectionName = str2;
        this.mPageName = str;
        this.mRecyclerView = recyclerView;
        this.mTopic = str3;
        init();
    }

    public WBMDOmniturePaginationHandler(Activity activity, RecyclerView recyclerView, double d, WBMDPaginationListener wBMDPaginationListener) {
        this.mActivity = activity;
        this.mRecyclerView = recyclerView;
        this.mListener = wBMDPaginationListener;
        this.mPageSize = d;
        initWithListener();
    }

    public WBMDOmniturePaginationHandler(Activity activity, OffSetScrollView offSetScrollView, String str, String str2, int i) {
        this.mActivity = activity;
        this.mScrollView = offSetScrollView;
        this.mPageName = str;
        this.mSectionName = str2;
        this.mDeviceHeightOffset = i;
        init();
    }

    public WBMDOmniturePaginationHandler(Activity activity, NestedScrollView nestedScrollView, String str, String str2, int i) {
        this.mActivity = activity;
        this.mNestedScrollView = nestedScrollView;
        this.mPageName = str;
        this.mSectionName = str2;
        this.mDeviceHeightOffset = i;
        init();
    }

    private void init() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = (int) (((double) displayMetrics.heightPixels) * this.mPageSize);
            this.mDeviceHeight = i;
            this.mDeviceHeight = i - this.mDeviceHeightOffset;
            if (this.mNextSectionHeight < ((int) (((double) displayMetrics.heightPixels) * this.mPageSize))) {
                this.mNextSectionHeight = (int) (((double) displayMetrics.heightPixels) * this.mPageSize);
            }
            if (this.mRecyclerView != null) {
                this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        WBMDOmniturePaginationHandler wBMDOmniturePaginationHandler = WBMDOmniturePaginationHandler.this;
                        int unused = wBMDOmniturePaginationHandler.mCurrentScrollY = wBMDOmniturePaginationHandler.mCurrentScrollY + i2;
                        WBMDOmniturePaginationHandler wBMDOmniturePaginationHandler2 = WBMDOmniturePaginationHandler.this;
                        wBMDOmniturePaginationHandler2.makeScrollDistanceCheck(wBMDOmniturePaginationHandler2.mCurrentScrollY);
                    }

                    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                        super.onScrollStateChanged(recyclerView, i);
                        WBMDOmniturePaginationHandler.this.setScrollState(i);
                        if (i == 0) {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    if (WBMDOmniturePaginationHandler.this.mPageNoSent != WBMDOmniturePaginationHandler.this.mScrollCount && WBMDOmniturePaginationHandler.this.mPageNoSent < WBMDOmniturePaginationHandler.this.mScrollCount && WBMDOmniturePaginationHandler.this.getScrollState() == 0) {
                                        WBMDOmniturePaginationHandler.this.sendOmniturePing();
                                    }
                                }
                            }, 1000);
                        }
                    }
                });
            }
            if (this.mNestedScrollView != null) {
                this.mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                        Trace.d("omniscroll", "height          : " + WBMDOmniturePaginationHandler.this.mDeviceHeight);
                        Trace.d("omniscroll", "offset scroll y : " + i2);
                        WBMDOmniturePaginationHandler.this.makeScrollDistanceCheck(i2);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                if (WBMDOmniturePaginationHandler.this.mPageNoSent != WBMDOmniturePaginationHandler.this.mScrollCount && WBMDOmniturePaginationHandler.this.mPageNoSent < WBMDOmniturePaginationHandler.this.mScrollCount) {
                                    WBMDOmniturePaginationHandler.this.sendOmniturePing();
                                }
                            }
                        }, 1000);
                    }
                });
            }
            if (this.mScrollView != null) {
                this.mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    public void onScrollChanged() {
                        WBMDOmniturePaginationHandler.this.makeScrollDistanceCheck(WBMDOmniturePaginationHandler.this.mScrollView.getComputedVerticleScrollOffset());
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                if (WBMDOmniturePaginationHandler.this.mPageNoSent != WBMDOmniturePaginationHandler.this.mScrollCount && WBMDOmniturePaginationHandler.this.mPageNoSent < WBMDOmniturePaginationHandler.this.mScrollCount) {
                                    WBMDOmniturePaginationHandler.this.sendOmniturePing();
                                }
                            }
                        }, 1000);
                    }
                });
            }
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void makeScrollDistanceCheck(int i) {
        int i2 = this.mDeviceHeight;
        if (i2 > 0) {
            int i3 = this.mNextSectionHeight;
            if (i > i3) {
                this.mPreviousSectionHeight += i2;
                this.mNextSectionHeight = i3 + i2;
                this.mScrollCount++;
                return;
            }
            int i4 = this.mPreviousSectionHeight;
            if (i4 > 0 && i < i4) {
                this.mScrollCount--;
                this.mPreviousSectionHeight = i4 - i2;
                this.mNextSectionHeight = i3 - i2;
            }
        }
    }

    private void initWithListener() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mDeviceHeight = (int) (((double) displayMetrics.heightPixels) * this.mPageSize);
        if (this.mNextSectionHeight < ((int) (((double) displayMetrics.heightPixels) * this.mPageSize))) {
            this.mNextSectionHeight = (int) (((double) displayMetrics.heightPixels) * this.mPageSize);
        }
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                WBMDOmniturePaginationHandler wBMDOmniturePaginationHandler = WBMDOmniturePaginationHandler.this;
                int unused = wBMDOmniturePaginationHandler.mCurrentScrollY = wBMDOmniturePaginationHandler.mCurrentScrollY + i2;
                WBMDOmniturePaginationHandler wBMDOmniturePaginationHandler2 = WBMDOmniturePaginationHandler.this;
                wBMDOmniturePaginationHandler2.makeScrollDistanceCheckMedscape(wBMDOmniturePaginationHandler2.mCurrentScrollY);
                if (Math.abs(WBMDOmniturePaginationHandler.this.mCurrentScrollY - WBMDOmniturePaginationHandler.this.sendPosition) > WBMDOmniturePaginationHandler.this.mDeviceHeight && !WBMDOmniturePaginationHandler.this.debugCalled) {
                    WBMDOmniturePaginationHandler.this.mListener.onDebugOptions(-1);
                    boolean unused2 = WBMDOmniturePaginationHandler.this.debugCalled = true;
                }
                WBMDOmniturePaginationHandler.this.delayCounter.cancel();
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                WBMDOmniturePaginationHandler.this.setScrollState(i);
                if (i == 0 && Math.abs(WBMDOmniturePaginationHandler.this.mCurrentScrollY - WBMDOmniturePaginationHandler.this.sendPosition) > WBMDOmniturePaginationHandler.this.mDeviceHeight && WBMDOmniturePaginationHandler.this.getScrollState() == 0) {
                    WBMDOmniturePaginationHandler.this.delayCounter.start();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void makeScrollDistanceCheckMedscape(int i) {
        int i2 = this.mDeviceHeight;
        if (i2 > 0) {
            int i3 = this.mNextSectionHeight;
            if (i > i3) {
                this.mPreviousSectionHeight += i2;
                this.mNextSectionHeight = i3 + i2;
                this.mScrollCount++;
                return;
            }
            int i4 = this.mPreviousSectionHeight;
            if (i4 > 0 && i < i4) {
                this.mScrollCount--;
                this.mPreviousSectionHeight = i4 - i2;
                this.mNextSectionHeight = i3 - i2;
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendOmniturePing() {
        HashMap<String, String> hashMap = this.mData;
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put("wapp.pagination", Integer.toString(this.mScrollCount));
        hashMap.put("wapp.section", this.mSectionName);
        String str = this.mTopic;
        if (str != null) {
            hashMap.put("wapp.topic", str);
        }
        WBMDOmnitureManager.sendPageView(this.mPageName, hashMap, new WBMDOmnitureModule(ON_SCROLL_PAGE_NAME_SUFFIX, (String) null, this.mPageName));
        this.mPageNoSent = this.mScrollCount;
    }

    /* access modifiers changed from: private */
    public void setScrollState(int i) {
        synchronized (this.lock) {
            this.mScrollState = i;
        }
    }

    /* access modifiers changed from: private */
    public int getScrollState() {
        int i;
        synchronized (this.lock) {
            i = this.mScrollState;
            this.mScrollState = -1;
        }
        return i;
    }

    public void resetPagination(String str, String str2) {
        this.mPageName = str;
        this.mTopic = str2;
        resetVariableValues();
    }

    private void resetVariableValues() {
        this.mScrollCount = 0;
        this.mPageNoSent = 0;
        this.mPreviousSectionHeight = 0;
        this.mNextSectionHeight = 0;
        this.mCurrentScrollY = 0;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mDeviceHeight = displayMetrics.heightPixels;
        if (this.mNextSectionHeight < displayMetrics.heightPixels) {
            this.mNextSectionHeight = displayMetrics.heightPixels;
        }
    }

    public void setData(HashMap<String, String> hashMap) {
        this.mData = hashMap;
    }
}

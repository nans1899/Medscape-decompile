package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.OverScroller;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.Register;
import com.epapyrus.plugpdf.core.SearchInfo;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolNav;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolTransform;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.PageAdapter;
import java.util.HashMap;
import java.util.NoSuchElementException;

public abstract class SinglePageDisplay extends BasePlugPDFDisplay implements Runnable {
    private boolean mIsMinScale;
    protected int mLastScrollX;
    protected int mLastScrollY;
    private OrientationEventListener mOrientationListener;
    protected final SparseArray<PageView> mPageViews = new SparseArray<>(5);
    protected boolean mResetLayout;
    protected double mScale = 1.0d;
    protected int mScrollPosX;
    protected int mScrollPosY;
    protected OverScroller mScroller;
    private int mSwipeMargin = 0;
    protected boolean mUserInteracting;

    /* access modifiers changed from: protected */
    public abstract boolean fling(View view, float f, float f2);

    /* access modifiers changed from: protected */
    public abstract int getCurrentPageIndexByScrollPosition();

    /* access modifiers changed from: protected */
    public abstract void nearPageLayout(Point point, int i, int i2, int i3, int i4);

    /* access modifiers changed from: protected */
    public abstract void pageLayout(View view, int i, int i2, int i3, int i4, Point point);

    public SinglePageDisplay(Context context, BasePlugPDFDisplay.PageDisplayMode pageDisplayMode) {
        super(context, pageDisplayMode);
        OverScroller overScroller = new OverScroller(context);
        this.mScroller = overScroller;
        overScroller.setFriction(ViewConfiguration.getScrollFriction() * PropertyManager.getScrollFrictionCoef());
        AnonymousClass1 r3 = new OrientationEventListener(getContext(), 3) {
            public void onOrientationChanged(int i) {
                if (i % 90 < 30 && i != -1) {
                    SinglePageDisplay.this.getPageView();
                }
            }
        };
        this.mOrientationListener = r3;
        r3.enable();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!wasFinishedLoad() && this.mImmediatelyClear) {
            Log.w("PlugPDF", "[WARNING] if detached View when complete all task before, Can't clear. you should detach ReaderListener.onLoadFinish(ALL_TASK_COMPLATE) after.");
        }
    }

    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        ((PageAdapter) this.mAdapter).setAdapterListener(new PageAdapter.PageAdapterListener() {
            public void onLoadedPage(PageView pageView) {
                if (pageView.getPageIdx() == SinglePageDisplay.this.getPageIdx()) {
                    SinglePageDisplay.this.keepMinimumZoomLevel();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void keepMinimumZoomLevel() {
        if ((this.mIsMinScale && PropertyManager.isKeepMinimumZoomLevel()) || getScale() < calcMinScale(this.mFitType)) {
            setScale(calcMinScale(this.mFitType));
            if (!this.mUserInteracting) {
                this.mScroller.forceFinished(true);
                PageView pageView = getPageView();
                if (pageView != null) {
                    slideViewOntoScreen(measureView(pageView));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:4|(3:5|6|(6:7|8|9|10|11|12))|13|14|47) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002f */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0051 A[SYNTHETIC, Splitter:B:28:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005c A[SYNTHETIC, Splitter:B:34:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0063 A[SYNTHETIC, Splitter:B:38:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onWindowVisibilityChanged(int r6) {
        /*
            r5 = this;
            super.onWindowVisibilityChanged(r6)
            r0 = 8
            if (r6 != r0) goto L_0x0067
            boolean r6 = com.epapyrus.plugpdf.core.viewer.ReaderView.isEnableUseRecentPage()
            if (r6 == 0) goto L_0x0067
            r6 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            android.content.Context r1 = r5.getContext()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            java.io.File r1 = r1.getFilesDir()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            java.lang.String r2 = "pgInfo.ser"
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x003a, all -> 0x0035 }
            java.util.Map r6 = r5.mRecentPageMap     // Catch:{ Exception -> 0x0033 }
            r0.writeObject(r6)     // Catch:{ Exception -> 0x0033 }
            r0.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            r1.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x0067
        L_0x0033:
            r6 = move-exception
            goto L_0x0048
        L_0x0035:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x005a
        L_0x003a:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x0048
        L_0x003f:
            r0 = move-exception
            r1 = r6
            r6 = r0
            r0 = r1
            goto L_0x005a
        L_0x0044:
            r0 = move-exception
            r1 = r6
            r6 = r0
            r0 = r1
        L_0x0048:
            java.lang.String r2 = "PlugPDF"
            java.lang.String r3 = "[ERROR] SinglePageDisplay.onWindowVisibilityChanged "
            android.util.Log.e(r2, r3, r6)     // Catch:{ all -> 0x0059 }
            if (r0 == 0) goto L_0x0056
            r0.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0056
        L_0x0055:
        L_0x0056:
            if (r1 == 0) goto L_0x0067
            goto L_0x002f
        L_0x0059:
            r6 = move-exception
        L_0x005a:
            if (r0 == 0) goto L_0x0061
            r0.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0061
        L_0x0060:
        L_0x0061:
            if (r1 == 0) goto L_0x0066
            r1.close()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            throw r6
        L_0x0067:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.SinglePageDisplay.onWindowVisibilityChanged(int):void");
    }

    public boolean hasNextPageView() {
        return this.mCurPageIdx + 1 < this.mAdapter.getCount();
    }

    public boolean hasPrevPageView() {
        return this.mCurPageIdx > 0;
    }

    /* access modifiers changed from: protected */
    public void slideViewOntoScreen(View view) {
        Point correctionPt = getCorrectionPt(getScrollBounds(view));
        if (correctionPt.x != 0 || correctionPt.y != 0) {
            this.mLastScrollX = 0;
            this.mLastScrollY = 0;
            this.mScroller.startScroll(0, 0, correctionPt.x, correctionPt.y, 400);
            post(this);
        }
    }

    /* access modifiers changed from: protected */
    public Point getCorrectionPt(Rect rect) {
        return new Point(Math.min(Math.max(0, rect.left), rect.right), Math.min(Math.max(0, rect.top), rect.bottom));
    }

    /* access modifiers changed from: protected */
    public Rect getScrollBounds(int i, int i2, int i3, int i4) {
        int width = getWidth() - i3;
        int i5 = -i;
        int height = getHeight() - i4;
        int i6 = -i2;
        if (width > i5) {
            width = (width + i5) / 2;
            i5 = width;
        }
        if (height > i6) {
            height = (height + i6) / 2;
            i6 = height;
        }
        return new Rect(width, height, i5, i6);
    }

    /* access modifiers changed from: protected */
    public void onUserInteractionComplete(View view) {
        if (PropertyManager.isEnableAdjustpatch()) {
            ((PageView) view).adjustPatch();
        }
    }

    /* access modifiers changed from: protected */
    public void onUserInteractionBegin(View view) {
        ((PageView) view).cancelAdjustPatch();
    }

    /* access modifiers changed from: protected */
    public View measureView(View view) {
        view.measure(0, 0);
        float calcMeasureScale = calcMeasureScale(view);
        view.measure(((int) (((double) (((float) view.getMeasuredWidth()) * calcMeasureScale)) * getScale())) | 1073741824, ((int) (((double) (((float) view.getMeasuredHeight()) * calcMeasureScale)) * getScale())) | 1073741824);
        return view;
    }

    /* access modifiers changed from: protected */
    public Rect getScrollBounds(View view) {
        return getScrollBounds(view.getLeft() + this.mScrollPosX, view.getTop() + this.mScrollPosY, view.getLeft() + view.getMeasuredWidth() + this.mScrollPosX, view.getTop() + view.getMeasuredHeight() + this.mScrollPosY);
    }

    /* access modifiers changed from: protected */
    public Point getSubScreenSizeOffset(View view) {
        return new Point(Math.max((getWidth() - view.getMeasuredWidth()) / 2, 0), Math.max((getHeight() - view.getMeasuredHeight()) / 2, 0));
    }

    /* access modifiers changed from: protected */
    public View createPageView(int i) {
        PageView pageView = this.mPageViews.get(i);
        if (pageView == null) {
            pageView = (PageView) this.mAdapter.getView(i, (View) null, this);
            if (pageView == null) {
                return null;
            }
            registPageView(i, pageView);
        }
        setupPageView(i, pageView);
        if (i == this.mCurPageIdx) {
            this.mAnnotTool.enter();
            this.mAnnotTool.setPageView(pageView);
        }
        return pageView;
    }

    /* access modifiers changed from: protected */
    public BasePlugPDFDisplay.Direction getDirection(float f, float f2) {
        if (Math.abs(f) > Math.abs(f2) * 2.0f) {
            return f > 0.0f ? BasePlugPDFDisplay.Direction.RIGHT : BasePlugPDFDisplay.Direction.LEFT;
        }
        if (Math.abs(f2) > Math.abs(f) * 2.0f) {
            return f2 > 0.0f ? BasePlugPDFDisplay.Direction.DOWN : BasePlugPDFDisplay.Direction.UP;
        }
        return BasePlugPDFDisplay.Direction.DIAGONALLY;
    }

    /* access modifiers changed from: protected */
    public boolean rectInDirection(Rect rect, float f, float f2) {
        int i = AnonymousClass6.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction[getDirection(f, f2).ordinal()];
        if (i == 1) {
            return rect.contains(0, 0);
        }
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        throw new NoSuchElementException();
                    } else if (rect.bottom >= 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (rect.top <= 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (rect.right >= 0) {
                return true;
            } else {
                return false;
            }
        } else if (rect.left <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return PropertyManager.getPageGap() * 4;
    }

    /* access modifiers changed from: private */
    public void setScale(double d) {
        double calcMinScale = calcMinScale(this.mFitType);
        if (calcMinScale >= d) {
            this.mIsMinScale = true;
            d = calcMinScale;
        } else {
            this.mIsMinScale = false;
        }
        this.mScale = d;
        if (this.mListener != null) {
            this.mListener.onChangeZoom(getScale());
        }
    }

    /* access modifiers changed from: private */
    public double getScale() {
        return this.mScale;
    }

    private int getScrollPosX() {
        return this.mScrollPosX;
    }

    public void setScrollPosX(int i) {
        this.mScrollPosX = i;
    }

    private int getScrollPosY() {
        return this.mScrollPosY;
    }

    public void setScrollPosY(int i) {
        this.mScrollPosY = i;
    }

    private boolean isScrollDisabled() {
        return this.mScrollDisabled;
    }

    /* access modifiers changed from: private */
    public void postUserInteractionComplete(final View view) {
        if (view != null) {
            post(new Runnable() {
                public void run() {
                    SinglePageDisplay.this.onUserInteractionComplete(view);
                }
            });
        }
    }

    private void postUserInteractionBegin(final View view) {
        if (view != null) {
            post(new Runnable() {
                public void run() {
                    SinglePageDisplay.this.onUserInteractionBegin(view);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void relayout() {
        this.mResetLayout = false;
        this.mLoadFinish = false;
        this.mScrollPosX = 0;
        this.mScrollPosY = 0;
        for (int i = 0; i < this.mPageViews.size(); i++) {
            PageView valueAt = this.mPageViews.valueAt(i);
            valueAt.clean(valueAt.getPageIdx());
            valueAt.removeAllViews();
            removeViewInLayout(valueAt);
        }
        this.mPageViews.clear();
        post(this);
    }

    /* access modifiers changed from: protected */
    public void layout() {
        PageView pageView = getPageView();
        if (pageView != null && this.mUserInteracting) {
            Point subScreenSizeOffset = getSubScreenSizeOffset(pageView);
            Log.d("PlugPDF", "cvOffset, cvOffset.x : " + subScreenSizeOffset.x + " cvOffset.y : " + subScreenSizeOffset.y);
            Log.d("PlugPDF", "cv, cv.getRight() : " + pageView.getRight() + " cv.getBottom() : " + pageView.getBottom() + "cv.getLeft() : " + pageView.getLeft() + "cv.getTop() : " + pageView.getTop());
            StringBuilder sb = new StringBuilder();
            sb.append("getWidth() : ");
            sb.append(getWidth());
            sb.append(" getHeight() : ");
            sb.append(getHeight());
            Log.d("PlugPDF", sb.toString());
            if (hasNextPageView()) {
                int right = pageView.getRight() + subScreenSizeOffset.x + (getOffset() / 2) + this.mSwipeMargin;
                int bottom = pageView.getBottom() + subScreenSizeOffset.y + (getOffset() / 2) + this.mSwipeMargin;
                Log.d("PlugPDF", this.mCurPageIdx + " Page hasNextPageView, slideValue_X : " + right + " slideValue_Y : " + bottom);
                boolean z = bottom < getHeight() / 2;
                boolean z2 = right < getWidth() / 2;
                if (z2 || z) {
                    postUserInteractionBegin(pageView);
                    this.mCurPageIdx++;
                    onGoToPage(this.mCurPageIdx);
                    pageView = getPageView();
                    if (pageView == null) {
                        Log.w("PlugPDF", "Next page was not prepared");
                    } else {
                        int left = pageView.getLeft();
                        int top = pageView.getTop();
                        pageView.layout(left, top, left + measureView(pageView).getMeasuredWidth(), top + pageView.getMeasuredHeight());
                        if (z2) {
                            this.mSwipeMargin = left - (getWidth() / 2);
                        } else if (z) {
                            this.mSwipeMargin = top - (getHeight() / 2);
                        }
                    }
                }
            }
            Log.d("PlugPDF", "cv, cv.getRight() : " + pageView.getRight() + " cv.getBottom() : " + pageView.getBottom() + "cv.getLeft() : " + pageView.getLeft() + "cv.getTop() : " + pageView.getTop());
            if (hasPrevPageView()) {
                int left2 = ((pageView.getLeft() - subScreenSizeOffset.x) - (getOffset() / 2)) - this.mSwipeMargin;
                int top2 = ((pageView.getTop() - subScreenSizeOffset.y) - (getOffset() / 2)) - this.mSwipeMargin;
                Log.d("PlugPDF", this.mCurPageIdx + " Page hasPrevPageView, slideValue_X : " + left2 + " slideValue_Y : " + top2);
                boolean z3 = left2 >= getWidth() / 2;
                boolean z4 = top2 >= getHeight() / 2;
                if (z3 || z4) {
                    postUserInteractionBegin(pageView);
                    post(this);
                    this.mCurPageIdx--;
                    onGoToPage(this.mCurPageIdx);
                    PageView pageView2 = getPageView();
                    if (pageView2 == null) {
                        Log.w("PlugPDF", "Next page was not prepared");
                    } else {
                        int right2 = pageView2.getRight() - measureView(pageView2).getMeasuredWidth();
                        int top3 = pageView2.getTop();
                        int right3 = pageView2.getRight();
                        int measuredHeight = pageView2.getMeasuredHeight() + top3;
                        pageView2.layout(right2, top3, right3, measuredHeight);
                        if (z3) {
                            this.mSwipeMargin = (getWidth() / 2) - right3;
                        } else if (z4) {
                            this.mSwipeMargin = (getHeight() / 2) - measuredHeight;
                        }
                    }
                }
            }
        }
        int i = 0;
        int keyAt = this.mPageViews.keyAt(0);
        PageView pageView3 = this.mPageViews.get(keyAt);
        PageView pageView4 = this.mPageViews.get(keyAt);
        while (pageView4 != null) {
            int keyAt2 = this.mPageViews.keyAt(i);
            if (pageView4.getPageIdx() >= this.mCurPageIdx - 2) {
                SparseArray<PageView> sparseArray = this.mPageViews;
                keyAt2 = sparseArray.keyAt(sparseArray.size() - 1);
                pageView4 = this.mPageViews.get(keyAt2);
                if (pageView4.getPageIdx() <= this.mCurPageIdx + 2) {
                    return;
                }
            }
            int pageIdx = pageView4.getPageIdx();
            removeViewInLayout(pageView4);
            pageView4.clean(pageIdx);
            this.mPageViews.remove(keyAt2);
            i = 0;
            pageView4 = this.mPageViews.get(this.mPageViews.keyAt(0));
        }
    }

    /* access modifiers changed from: protected */
    public void setupPageView(int i, View view) {
        PageView pageView = (PageView) view;
        pageView.enableSearchHighlight((RectF[]) null);
        SearchInfo searchInfo = Register.getSearchInfo();
        if (searchInfo != null && pageView.mDoc.getFilePath() == searchInfo.getFilePath() && searchInfo != null && searchInfo.getPageIdx() == i) {
            pageView.enableSearchHighlight(searchInfo.getAreaList());
        }
    }

    /* access modifiers changed from: protected */
    public void registPageView(int i, PageView pageView) {
        ViewGroup.LayoutParams layoutParams = pageView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        addViewInLayout(pageView, 0, layoutParams, true);
        PageView pageView2 = this.mPageViews.get(i);
        if (pageView2 != null) {
            pageView2.clean(pageView2.getPageIdx());
        }
        this.mPageViews.append(i, pageView);
        measureView(pageView);
    }

    private void onGoToPage(int i) {
        SearchInfo searchInfo = Register.getSearchInfo();
        this.mDoc.getRecentPageIdxList().clear();
        this.mDoc.insertRecentPageIdx(i);
        if (!(searchInfo == null || searchInfo.getPageIdx() == i)) {
            Register.setSearchInfo((SearchInfo) null);
            setupPageViews();
        }
        if (this.mListener != null) {
            this.mListener.onGoToPage(i + 1, pageCount());
        }
        if (ReaderView.isEnableUseRecentPage()) {
            if (this.mRecentPageMap == null) {
                this.mRecentPageMap = new HashMap();
            }
            this.mRecentPageMap.put(this.mDoc.getFilePath(), Integer.valueOf(i));
        }
        keepMinimumZoomLevel();
    }

    public void goToPage(int i) {
        if (this.mDoc != null && i >= 0 && i < this.mAdapter.getCount() && this.mCurPageIdx != i) {
            this.mCurPageIdx = i;
            onGoToPage(i);
            this.mResetLayout = true;
            requestLayout();
            post(this);
        }
    }

    public void clear() {
        super.clear();
        if (wasFinishedLoad()) {
            for (int i = 0; i < this.mPageViews.size(); i++) {
                PageView valueAt = this.mPageViews.valueAt(i);
                valueAt.clean(valueAt.getPageIdx());
                valueAt.removeAllViews();
                removeViewInLayout(valueAt);
            }
        }
        this.mOrientationListener.disable();
        this.mPageViews.clear();
    }

    public double calculatePageViewScaleInReaderView() {
        return getPageViewScale(this.mCurPageIdx) * getScale();
    }

    public void refreshLayout() {
        Log.d("PlugPDF", "[DEBUG] refreshLayout");
        this.mResetLayout = true;
        requestLayout();
    }

    public PageView getPageView() {
        return this.mPageViews.get(this.mCurPageIdx);
    }

    public PageView getPageView(int i) {
        return this.mPageViews.get(i);
    }

    public void setupPageViews() {
        for (int i = 0; i < this.mPageViews.size(); i++) {
            setupPageView(this.mPageViews.keyAt(i), this.mPageViews.valueAt(i));
        }
    }

    /* access modifiers changed from: protected */
    public double getPageViewScale(int i) {
        PageView pageView = this.mPageViews.get(i);
        if (pageView != null) {
            return (double) pageView.mSourceScale;
        }
        return 1.0d;
    }

    public SparseArray<PageView> getChildViewList() {
        return this.mPageViews;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0058, code lost:
        if ((((double) getWidth()) / ((double) getHeight())) < ((double) (r0.x / r0.y))) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0070, code lost:
        if ((((double) getWidth()) / ((double) getHeight())) > ((double) (r0.x / r0.y))) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008a, code lost:
        if ((((double) getWidth()) / ((double) getHeight())) < ((double) (r0.x / r0.y))) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a2, code lost:
        if ((((double) getWidth()) / ((double) getHeight())) > ((double) (r0.x / r0.y))) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ba, code lost:
        if ((((double) getWidth()) / ((double) getHeight())) < ((double) (r0.x / r0.y))) goto L_0x00bf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double calcMinScale(com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType r13) {
        /*
            r12 = this;
            com.epapyrus.plugpdf.core.viewer.PageView r0 = r12.getPageView()
            if (r0 == 0) goto L_0x00c6
            com.epapyrus.plugpdf.core.viewer.PageView r0 = r12.getPageView()
            android.graphics.PointF r0 = r0.getOriginPageSize()
            if (r0 != 0) goto L_0x0012
            goto L_0x00c6
        L_0x0012:
            android.content.res.Resources r1 = r12.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.orientation
            int r2 = r12.getWidth()
            float r2 = (float) r2
            float r3 = r0.x
            float r2 = r2 / r3
            int r3 = r12.getHeight()
            float r3 = (float) r3
            float r4 = r0.y
            float r3 = r3 / r4
            int[] r4 = com.epapyrus.plugpdf.core.viewer.SinglePageDisplay.AnonymousClass6.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType
            int r13 = r13.ordinal()
            r13 = r4[r13]
            r4 = 2
            r5 = 1
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r13 == r5) goto L_0x008d
            if (r13 == r4) goto L_0x005b
            r4 = 3
            if (r13 == r4) goto L_0x0041
            goto L_0x00bf
        L_0x0041:
            if (r1 != r5) goto L_0x0045
            goto L_0x00bf
        L_0x0045:
            int r13 = r12.getWidth()
            double r4 = (double) r13
            int r13 = r12.getHeight()
            double r6 = (double) r13
            double r4 = r4 / r6
            float r13 = r0.x
            float r0 = r0.y
            float r13 = r13 / r0
            double r0 = (double) r13
            int r13 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r13 >= 0) goto L_0x00bd
            goto L_0x0072
        L_0x005b:
            if (r1 != r5) goto L_0x0075
            int r13 = r12.getWidth()
            double r8 = (double) r13
            int r13 = r12.getHeight()
            double r10 = (double) r13
            double r8 = r8 / r10
            float r13 = r0.x
            float r5 = r0.y
            float r13 = r13 / r5
            double r10 = (double) r13
            int r13 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r13 <= 0) goto L_0x0075
        L_0x0072:
            float r2 = r2 / r3
            double r6 = (double) r2
            goto L_0x00bf
        L_0x0075:
            if (r1 != r4) goto L_0x00bf
            int r13 = r12.getWidth()
            double r4 = (double) r13
            int r13 = r12.getHeight()
            double r8 = (double) r13
            double r4 = r4 / r8
            float r13 = r0.x
            float r0 = r0.y
            float r13 = r13 / r0
            double r0 = (double) r13
            int r13 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r13 >= 0) goto L_0x00bf
            goto L_0x0072
        L_0x008d:
            if (r1 != r5) goto L_0x00a5
            int r13 = r12.getWidth()
            double r8 = (double) r13
            int r13 = r12.getHeight()
            double r10 = (double) r13
            double r8 = r8 / r10
            float r13 = r0.x
            float r5 = r0.y
            float r13 = r13 / r5
            double r10 = (double) r13
            int r13 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r13 <= 0) goto L_0x00a5
            goto L_0x00bf
        L_0x00a5:
            if (r1 != r4) goto L_0x00bd
            int r13 = r12.getWidth()
            double r4 = (double) r13
            int r13 = r12.getHeight()
            double r8 = (double) r13
            double r4 = r4 / r8
            float r13 = r0.x
            float r0 = r0.y
            float r13 = r13 / r0
            double r0 = (double) r13
            int r13 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r13 >= 0) goto L_0x00bd
            goto L_0x00bf
        L_0x00bd:
            float r3 = r3 / r2
            double r6 = (double) r3
        L_0x00bf:
            double r0 = com.epapyrus.plugpdf.core.PropertyManager.getMinimumZoomLevel()
            double r6 = r6 * r0
            return r6
        L_0x00c6:
            double r0 = r12.getScale()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.SinglePageDisplay.calcMinScale(com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType):double");
    }

    /* renamed from: com.epapyrus.plugpdf.core.viewer.SinglePageDisplay$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction;
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        static {
            /*
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType[] r0 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType = r0
                r1 = 1
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType r2 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.HEIGHT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType r3 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.WIDTH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType r4 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.MINIMUM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$Direction[] r3 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.Direction.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction = r3
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$Direction r4 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.Direction.DIAGONALLY     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$Direction r3 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.Direction.LEFT     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction     // Catch:{ NoSuchFieldError -> 0x004d }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$Direction r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.Direction.RIGHT     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$Direction r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.Direction.UP     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction     // Catch:{ NoSuchFieldError -> 0x0063 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$Direction r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.Direction.DOWN     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.SinglePageDisplay.AnonymousClass6.<clinit>():void");
        }
    }

    public void setFitType(BasePlugPDFDisplay.FitType fitType) {
        this.mFitType = fitType;
        setScale(calcMinScale(this.mFitType));
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            measureView(getChildAt(i3));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        if (this.mCurPageIdx < 0) {
            goToPage(0);
        } else if (this.mCurPageIdx > this.mDoc.getPageCount() - 1) {
            goToPage(this.mDoc.getPageCount() - 1);
        }
        if (this.mAdapter != null) {
            if (this.mResetLayout) {
                relayout();
            } else {
                layout();
            }
            View createPageView = createPageView(this.mCurPageIdx);
            if (createPageView != null) {
                int left = createPageView.getLeft() + this.mScrollPosX;
                int top = createPageView.getTop() + this.mScrollPosY;
                this.mScrollPosX = 0;
                this.mScrollPosY = 0;
                pageLayout(createPageView, left, top, left + createPageView.getMeasuredWidth(), top + createPageView.getMeasuredHeight(), getSubScreenSizeOffset(createPageView));
                invalidate();
                if (!wasFinishedLoad()) {
                    setScale(calcMinScale(this.mFitType));
                    int size = this.mPageViews.size();
                    while (i5 < size) {
                        if (this.mPageViews.valueAt(i5).isLoadedPage()) {
                            i5++;
                        } else {
                            return;
                        }
                    }
                    if (this.mImmediatelyClear) {
                        this.mDoc.release();
                        clear();
                    }
                    this.mLoadFinish = true;
                    this.mDisplayListener.allTaskCompleted();
                    postUserInteractionComplete(getPageView());
                }
            }
        }
    }

    public void run() {
        int currentPageIndexByScrollPosition;
        if (!this.mScroller.isFinished()) {
            this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            this.mScrollPosX += currX - this.mLastScrollX;
            this.mScrollPosY += currY - this.mLastScrollY;
            this.mLastScrollX = currX;
            this.mLastScrollY = currY;
            if (!(this.mListener == null || this.mScrollPosX == 0 || this.mScrollPosY == 0)) {
                this.mListener.onScroll(this.mScrollPosX, this.mScrollPosY);
            }
            requestLayout();
            post(this);
            return;
        }
        if (!this.mUserInteracting && (currentPageIndexByScrollPosition = getCurrentPageIndexByScrollPosition()) != this.mCurPageIdx) {
            this.mCurPageIdx = currentPageIndexByScrollPosition;
            onGoToPage(this.mCurPageIdx);
        }
        postUserInteractionComplete(getPageView());
    }

    public void onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.mListener != null && this.mAnnotTool.getSelectedAnnot(new Point((int) motionEvent.getX(), (int) motionEvent.getY())) == null) {
            this.mListener.onSingleTapUp(motionEvent);
        }
    }

    public void onDown(MotionEvent motionEvent) {
        this.mScroller.forceFinished(true);
    }

    public void onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        PageView pageView;
        float scrollVelocityCoef = f * PropertyManager.getScrollVelocityCoef();
        float scrollVelocityCoef2 = f2 * PropertyManager.getScrollVelocityCoef();
        if (!this.mScrollDisabled && (pageView = getPageView()) != null) {
            if (this.mOuterFocusInterception) {
                PointF returnValidScrollPoint = returnValidScrollPoint(scrollVelocityCoef, scrollVelocityCoef2);
                scrollVelocityCoef = returnValidScrollPoint.x;
                scrollVelocityCoef2 = returnValidScrollPoint.y;
            }
            if (fling(pageView, scrollVelocityCoef, scrollVelocityCoef2)) {
                this.mUserInteracting = false;
                onGoToPage(this.mCurPageIdx);
                return;
            }
            this.mLastScrollX = 0;
            this.mLastScrollY = 0;
            Rect scrollBounds = getScrollBounds(pageView);
            Rect rect = new Rect(scrollBounds);
            rect.inset(-100, -100);
            if (rectInDirection(scrollBounds, scrollVelocityCoef, scrollVelocityCoef2) && rect.contains(0, 0)) {
                this.mScroller.fling(0, 0, (int) scrollVelocityCoef, (int) scrollVelocityCoef2, scrollBounds.left, scrollBounds.right, scrollBounds.top, scrollBounds.bottom);
                post(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        if (r0.getBottom() <= getHeight()) goto L_0x004e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.PointF returnValidScrollPoint(float r5, float r6) {
        /*
            r4 = this;
            com.epapyrus.plugpdf.core.viewer.PageView r0 = r4.getPageView()
            int r1 = r4.getPageIdx()
            r2 = 0
            if (r1 != 0) goto L_0x0021
            int r1 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0016
            int r1 = r0.getLeft()
            if (r1 < 0) goto L_0x0016
            r5 = 0
        L_0x0016:
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0021
            int r1 = r0.getTop()
            if (r1 < 0) goto L_0x0021
            r6 = 0
        L_0x0021:
            int r1 = r4.getPageIdx()
            android.widget.Adapter r3 = r4.mAdapter
            int r3 = r3.getCount()
            int r3 = r3 + -1
            if (r1 != r3) goto L_0x004d
            int r1 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x003e
            int r1 = r0.getRight()
            int r3 = r4.getWidth()
            if (r1 > r3) goto L_0x003e
            r5 = 0
        L_0x003e:
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x004d
            int r0 = r0.getBottom()
            int r1 = r4.getHeight()
            if (r0 > r1) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r2 = r6
        L_0x004e:
            android.graphics.PointF r6 = new android.graphics.PointF
            r6.<init>(r5, r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.SinglePageDisplay.returnValidScrollPoint(float, float):android.graphics.PointF");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mGesture.onTouchEvent(motionEvent)) {
            if (motionEvent.getActionMasked() == 0) {
                this.mSwipeMargin = 0;
                this.mUserInteracting = true;
            }
            if (motionEvent.getActionMasked() == 1) {
                this.mUserInteracting = false;
                PageView pageView = getPageView();
                if (pageView != null && this.mScroller.isFinished()) {
                    slideViewOntoScreen(pageView);
                }
            }
            requestLayout();
        }
        return true;
    }

    public void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!isScrollDisabled()) {
            int scrollPosX = (int) (((float) getScrollPosX()) - f);
            int scrollPosY = (int) (((float) getScrollPosY()) - f2);
            if (this.mOuterFocusInterception) {
                PointF returnValidScrollPoint = returnValidScrollPoint((float) scrollPosX, (float) scrollPosY);
                int i = (int) returnValidScrollPoint.x;
                scrollPosY = (int) returnValidScrollPoint.y;
                scrollPosX = i;
            }
            setScrollPosX(scrollPosX);
            setScrollPosY(scrollPosY);
            requestLayout();
        }
        if (this.mListener != null) {
            this.mListener.onScroll(this.mScrollPosX, this.mScrollPosY);
        }
    }

    public void onDoubleTapUp(MotionEvent motionEvent) {
        PageView pageView;
        double d;
        if (!PropertyManager.isIgnoreZoomGesture() && (pageView = getPageView()) != null) {
            double scale = getScale();
            double calcMinScale = calcMinScale(this.mFitType);
            if (scale < PropertyManager.getDoubleTapZoomLevel() * calcMinScale) {
                if (PropertyManager.getMaximumZoomLevel() < PropertyManager.getDoubleTapZoomLevel()) {
                    d = PropertyManager.getMaximumZoomLevel();
                } else {
                    d = PropertyManager.getDoubleTapZoomLevel();
                }
                calcMinScale *= d;
            }
            double d2 = calcMinScale / scale;
            setScale(calcMinScale);
            int scrollPosX = getScrollPosX();
            int scrollPosY = getScrollPosY();
            int x = ((int) motionEvent.getX()) - (pageView.getLeft() + scrollPosX);
            int y = ((int) motionEvent.getY()) - (pageView.getTop() + scrollPosY);
            setScrollPosX((int) (((double) (scrollPosX + x)) - (((double) x) * d2)));
            setScrollPosY((int) (((double) (scrollPosY + y)) - (((double) y) * d2)));
            requestLayout();
            getPageView().redrawAP();
            postUserInteractionComplete(pageView);
            if (this.mListener != null) {
                this.mListener.onDoubleTapUp(motionEvent);
            }
        }
    }

    public void onScaleBegin() {
        setScrollPosX(0);
        setScrollPosY(0);
        setScrollDisabled(true);
    }

    public void onScaleEnd() {
        setScrollDisabled(false);
        getPageView().redrawAP();
        post(this);
    }

    public void onScale(ScaleGestureDetector scaleGestureDetector) {
        if (!PropertyManager.isIgnoreZoomGesture()) {
            double scale = getScale();
            double calcMinScale = calcMinScale(this.mFitType);
            double min = Math.min(Math.max(((double) scaleGestureDetector.getScaleFactor()) * scale, calcMinScale), PropertyManager.getMaximumZoomLevel() * calcMinScale);
            double d = min / scale;
            setScale(min);
            PageView pageView = getPageView();
            if (pageView != null) {
                int focusY = ((int) scaleGestureDetector.getFocusY()) - pageView.getTop();
                double focusX = (double) (((int) scaleGestureDetector.getFocusX()) - pageView.getLeft());
                double d2 = (double) focusY;
                setScrollPosX((int) (focusX - (focusX * d)));
                setScrollPosY((int) (d2 - (d * d2)));
                requestLayout();
            }
        }
    }

    public void changeScale(double d, int i, int i2) {
        setScrollPosX(0);
        setScrollPosY(0);
        setScrollDisabled(true);
        double scale = getScale();
        double min = Math.min(Math.max(d * scale, 1.0d), PropertyManager.getMaximumZoomLevel() * calcMinScale(this.mFitType));
        double d2 = min / scale;
        setScale(min);
        PageView pageView = getPageView();
        if (pageView != null) {
            PageView pageView2 = pageView;
            setScrollPosX((int) (((double) (-((int) ((((float) i) * (((float) pageView.getHeight()) / pageView2.getDocument().getCurrentPageHeight())) - ((float) pageView.getLeft()))))) * d2));
            setScrollPosY((int) (((double) (-((int) ((((float) i2) * (((float) pageView.getWidth()) / pageView2.getDocument().getCurrentPageWidth())) - ((float) pageView.getTop()))))) * d2));
            requestLayout();
        }
        post(this);
        setScrollDisabled(false);
    }

    public void onAnnotTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mAnnotTool.setPageView(getPageView());
            this.mAnnotTool.onTouchBegin(x, y, this.mAnnotToolEventListener);
        } else if (action == 1) {
            this.mAnnotTool.onTouchEnd(x, y, this.mAnnotToolEventListener);
        } else if (action == 2) {
            this.mAnnotTool.onTouchMove(x, y, this.mAnnotToolEventListener);
        }
    }

    public void onAnnotSingleTapUp(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType) {
        if (getPageView() != null) {
            this.mAnnotTool.setPageView(getPageView());
            if (gestureType.equals(BaseGestureProcessor.GestureType.EDIT) && (this.mAnnotTool instanceof AnnotToolNav)) {
                Point point = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                if (this.mAnnotTool.singleTapUp(point.x, point.y, this.mAnnotToolEventListener) == null) {
                    changeGesture(BaseGestureProcessor.GestureType.VIEW);
                    setAnnotationTool((BaseAnnotTool.AnnotToolType) null);
                }
            }
            if (gestureType.equals(BaseGestureProcessor.GestureType.VIEW)) {
                Point point2 = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                BaseAnnot selectedAnnot = this.mAnnotTool.getSelectedAnnot(point2);
                if (selectedAnnot != null) {
                    if (PropertyManager.isEnableAnnotTransform()) {
                        changeGesture(BaseGestureProcessor.GestureType.EDIT);
                        setAnnotationTool(BaseAnnotTool.AnnotToolType.TRANSFORM);
                    }
                    if (this.mAnnotEventListener == null) {
                        this.mAnnotTool.singleTapUp(point2.x, point2.y, this.mAnnotToolEventListener);
                    } else if (!this.mAnnotEventListener.onTapUp(selectedAnnot)) {
                        this.mAnnotTool.singleTapUp(point2.x, point2.y, this.mAnnotToolEventListener);
                    }
                }
            } else if (gestureType.equals(BaseGestureProcessor.GestureType.EDIT) && this.mAnnotTool.singleTapUp((int) motionEvent.getX(), (int) motionEvent.getY(), this.mAnnotToolEventListener) == null && (this.mAnnotTool instanceof AnnotToolTransform)) {
                changeGesture(BaseGestureProcessor.GestureType.VIEW);
                setAnnotationTool((BaseAnnotTool.AnnotToolType) null);
            }
        }
    }

    public void onAnnotLongPress(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType) {
        this.mAnnotTool.setPageView(getPageView());
        if (gestureType.equals(BaseGestureProcessor.GestureType.VIEW)) {
            Point point = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
            BaseAnnot selectedAnnot = this.mAnnotTool.getSelectedAnnot(point);
            if (selectedAnnot != null) {
                if (PropertyManager.isEnableAnnotTransform()) {
                    changeGesture(BaseGestureProcessor.GestureType.EDIT);
                    setAnnotationTool(BaseAnnotTool.AnnotToolType.TRANSFORM);
                }
                if (this.mAnnotEventListener == null) {
                    this.mAnnotTool.longPress(point.x, point.y);
                } else if (!this.mAnnotEventListener.onLongPressDown(selectedAnnot)) {
                    this.mAnnotTool.setAnnotEventListener(this.mAnnotEventListener);
                    this.mAnnotTool.longPress(point.x, point.y);
                }
            }
        } else if (gestureType.equals(BaseGestureProcessor.GestureType.EDIT)) {
            this.mAnnotTool.longPress((int) motionEvent.getX(), (int) motionEvent.getY());
        }
    }

    /* access modifiers changed from: protected */
    public float calcMeasureScale(View view) {
        if (getResources().getConfiguration().orientation == 2) {
            return Math.max(((float) getWidth()) / ((float) view.getMeasuredWidth()), ((float) getHeight()) / ((float) view.getMeasuredHeight()));
        }
        return Math.min(((float) getWidth()) / ((float) view.getMeasuredWidth()), ((float) getHeight()) / ((float) view.getMeasuredHeight()));
    }

    public void scrollToView(int i, View view) {
        float f;
        float f2;
        boolean z = i != this.mCurPageIdx;
        goToPage(i);
        Point correctionPt = getCorrectionPt(getScrollBounds(view));
        if (correctionPt.x != 0 || correctionPt.y != 0) {
            this.mLastScrollX = 0;
            this.mLastScrollY = 0;
            PageView pageView = getPageView();
            if (z) {
                f2 = view.getX() - ((float) (getWidth() / 20));
                f = view.getY() - ((float) (getHeight() / 20));
            } else {
                f = (view.getY() + pageView.getY()) - ((float) (getHeight() / 20));
                f2 = (view.getX() + pageView.getX()) - ((float) (getWidth() / 20));
            }
            this.mScroller.startScroll(0, 0, -((int) f2), -((int) f), 400);
            post(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        postDelayed(new Runnable() {
            public void run() {
                SinglePageDisplay singlePageDisplay = SinglePageDisplay.this;
                double calcMinScale = singlePageDisplay.calcMinScale(singlePageDisplay.mFitType);
                if (calcMinScale > SinglePageDisplay.this.getScale()) {
                    SinglePageDisplay.this.setScale(calcMinScale);
                }
                SinglePageDisplay.this.requestLayout();
                SinglePageDisplay singlePageDisplay2 = SinglePageDisplay.this;
                singlePageDisplay2.postUserInteractionComplete(singlePageDisplay2.getPageView());
            }
        }, 100);
    }
}

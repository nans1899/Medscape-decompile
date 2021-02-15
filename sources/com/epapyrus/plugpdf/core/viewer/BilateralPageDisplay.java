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
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.Register;
import com.epapyrus.plugpdf.core.SearchInfo;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.PageAdapter;
import java.util.HashMap;
import java.util.NoSuchElementException;

public abstract class BilateralPageDisplay extends BasePlugPDFDisplay implements Runnable {
    protected boolean mIsMinScale;
    protected int mLastScrollX;
    protected int mLastScrollY;
    private OrientationEventListener mOrientationListener;
    protected final SparseArray<PageView> mPageViews = new SparseArray<>(6);
    protected boolean mResetLayout;
    protected double mScale = 1.0d;
    protected boolean mScrollDisabled;
    protected int mScrollPosX;
    protected int mScrollPosY;
    protected OverScroller mScroller;
    private boolean mUseCoverStyle;
    protected boolean mUserInteracting;

    /* access modifiers changed from: protected */
    public abstract boolean fling(Rect rect, float f, float f2);

    /* access modifiers changed from: protected */
    public abstract int getCurrentPageIndexByScrollPosition();

    /* access modifiers changed from: protected */
    public abstract void nearPageLayout(Point point, Rect rect, Rect rect2);

    /* access modifiers changed from: protected */
    public abstract void pageLayout(View view, Rect rect, View view2, Rect rect2, Point point);

    public BilateralPageDisplay(Context context, BasePlugPDFDisplay.PageDisplayMode pageDisplayMode, boolean z) {
        super(context, pageDisplayMode);
        OverScroller overScroller = new OverScroller(context);
        this.mScroller = overScroller;
        overScroller.setFriction(ViewConfiguration.getScrollFriction() * PropertyManager.getScrollFrictionCoef());
        AnonymousClass1 r3 = new OrientationEventListener(getContext(), 3) {
            public void onOrientationChanged(int i) {
                if (i % 90 < 30 && i != -1) {
                    BilateralPageDisplay.this.getPageView();
                }
            }
        };
        this.mOrientationListener = r3;
        r3.enable();
        this.mUseCoverStyle = z;
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
            public void onLoadedPage(final PageView pageView) {
                BilateralPageDisplay.this.postDelayed(new Runnable() {
                    public void run() {
                        if ((pageView.getPageIdx() % 2 != 0 || pageView.getPageIdx() == BilateralPageDisplay.this.mDoc.getPageCount()) && BilateralPageDisplay.this.mIsMinScale && BilateralPageDisplay.this.getScale() != BilateralPageDisplay.this.calcMinScale(BilateralPageDisplay.this.mFitType)) {
                            BilateralPageDisplay.this.setScale(BilateralPageDisplay.this.calcMinScale(BilateralPageDisplay.this.mFitType));
                        }
                    }
                }, 100);
            }
        });
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
            java.lang.String r3 = "[DEBUG] BilateralPageDisplayMode.onWindowVisibilityChanged "
            android.util.Log.d(r2, r3, r6)     // Catch:{ all -> 0x0059 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.BilateralPageDisplay.onWindowVisibilityChanged(int):void");
    }

    public boolean hasNextPageView() {
        return getPageIdx() + 1 < this.mAdapter.getCount();
    }

    public boolean hasPrevPageView() {
        return getPageIdx() > 0;
    }

    /* access modifiers changed from: protected */
    public void slideViewOntoScreen() {
        Point correctionPt = getCorrectionPt(getScrollBounds((View) this.mPageViews.get(getStandaredPage(getPageIdx())), (View) this.mPageViews.get(getStandaredPage(getPageIdx()) + 1)));
        if (correctionPt != null) {
            if (correctionPt.x != 0 || correctionPt.y != 0) {
                this.mLastScrollX = 0;
                this.mLastScrollY = 0;
                this.mScroller.startScroll(0, 0, correctionPt.x, correctionPt.y, 400);
                post(this);
            }
        }
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
    public Rect getScrollBounds(Rect rect, Rect rect2) {
        return getScrollBounds(rect.left, leftIsGreater() ? rect.top : rect2.top, rect2.right, leftIsGreater() ? rect.bottom : rect2.bottom);
    }

    /* access modifiers changed from: protected */
    public Rect getScrollBounds(View view) {
        return getScrollBounds(view.getLeft() + this.mScrollPosX, view.getTop() + this.mScrollPosY, view.getRight() + this.mScrollPosX, view.getBottom() + this.mScrollPosY);
    }

    /* access modifiers changed from: protected */
    public Rect getScrollBounds(View view, View view2) {
        if (view2 == null) {
            return getScrollBounds(view.getLeft(), view.getTop(), view.getRight() + ((int) (((double) getOffset()) * getScale())) + (view.getMeasuredWidth() / 2), view.getBottom());
        }
        if (view == null) {
            return getScrollBounds((view2.getLeft() - (view2.getMeasuredWidth() / 2)) - ((int) (((double) getOffset()) * getScale())), view2.getTop(), view2.getRight(), view2.getBottom());
        }
        return getScrollBounds(view.getLeft(), leftIsGreater(view, view2) ? view.getTop() : view2.getTop(), view2.getRight(), leftIsGreater(view, view2) ? view.getBottom() : view2.getBottom());
    }

    /* access modifiers changed from: protected */
    public void onUserInteractionComplete(PageView pageView, PageView pageView2) {
        if (pageView != null) {
            pageView.adjustPatch();
        }
        if (pageView2 != null) {
            pageView2.adjustPatch();
        }
    }

    /* access modifiers changed from: protected */
    public void onUserInteractionBegin(PageView pageView, PageView pageView2) {
        if (pageView != null) {
            pageView.cancelAdjustPatch();
        }
        if (pageView2 != null) {
            pageView2.cancelAdjustPatch();
        }
    }

    /* access modifiers changed from: protected */
    public View measureView(View view) {
        view.measure(0, 0);
        float calcMeasureScale = calcMeasureScale(view);
        view.measure(((int) (((double) (((float) view.getMeasuredWidth()) * calcMeasureScale)) * getScale())) | 1073741824, ((int) (((double) (((float) view.getMeasuredHeight()) * calcMeasureScale)) * getScale())) | 1073741824);
        return view;
    }

    /* access modifiers changed from: protected */
    public Point getSubScreenSizeOffset(View view, View view2) {
        Point point;
        Point point2;
        if (view == null && view2 == null) {
            return null;
        }
        if (view != null) {
            point = getSubScreenSizeOffset(view);
        } else {
            point = getSubScreenSizeOffset(view2);
        }
        if (view2 != null) {
            point2 = getSubScreenSizeOffset(view2);
        } else {
            point2 = getSubScreenSizeOffset(view);
        }
        return new Point(point.x + point2.x, point.y + point2.y);
    }

    /* access modifiers changed from: protected */
    public Point getSubScreenSizeOffset(View view) {
        return new Point(Math.max((getWidth() - view.getMeasuredWidth()) / 2, 0), Math.max((getHeight() - view.getMeasuredHeight()) / 2, 0));
    }

    /* access modifiers changed from: protected */
    public View createPageView(int i, boolean z) {
        int standaredPage = getStandaredPage(i);
        if (z) {
            standaredPage++;
        }
        if (standaredPage < 0 || standaredPage > this.mDoc.getPageCount() - 1) {
            return null;
        }
        PageView pageView = this.mPageViews.get(standaredPage);
        if (pageView == null) {
            pageView = (PageView) this.mAdapter.getView(standaredPage, (View) null, this);
            if (pageView == null) {
                return null;
            }
            registPageView(standaredPage, pageView);
        }
        setupPageView(standaredPage, pageView);
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
        int i = AnonymousClass5.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$Direction[getDirection(f, f2).ordinal()];
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
        return (int) PlugPDFUtility.convertDipToPx(this.mCtx, (float) PropertyManager.getPageGap());
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

    /* access modifiers changed from: protected */
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

    private void postUserInteractionComplete() {
        post(new Runnable() {
            public void run() {
                BilateralPageDisplay bilateralPageDisplay = BilateralPageDisplay.this;
                SparseArray<PageView> sparseArray = bilateralPageDisplay.mPageViews;
                BilateralPageDisplay bilateralPageDisplay2 = BilateralPageDisplay.this;
                SparseArray<PageView> sparseArray2 = BilateralPageDisplay.this.mPageViews;
                BilateralPageDisplay bilateralPageDisplay3 = BilateralPageDisplay.this;
                bilateralPageDisplay.onUserInteractionComplete(sparseArray.get(bilateralPageDisplay2.getStandaredPage(bilateralPageDisplay2.getPageIdx())), sparseArray2.get(bilateralPageDisplay3.getStandaredPage(bilateralPageDisplay3.getPageIdx()) + 1));
            }
        });
    }

    private void postUserInteractionBegin() {
        post(new Runnable() {
            public void run() {
                BilateralPageDisplay bilateralPageDisplay = BilateralPageDisplay.this;
                SparseArray<PageView> sparseArray = bilateralPageDisplay.mPageViews;
                BilateralPageDisplay bilateralPageDisplay2 = BilateralPageDisplay.this;
                SparseArray<PageView> sparseArray2 = BilateralPageDisplay.this.mPageViews;
                BilateralPageDisplay bilateralPageDisplay3 = BilateralPageDisplay.this;
                bilateralPageDisplay.onUserInteractionBegin(sparseArray.get(bilateralPageDisplay2.getStandaredPage(bilateralPageDisplay2.getPageIdx())), sparseArray2.get(bilateralPageDisplay3.getStandaredPage(bilateralPageDisplay3.getPageIdx()) + 1));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void relayout() {
        this.mResetLayout = false;
        this.mScrollPosX = 0;
        this.mScrollPosY = 0;
        int size = this.mPageViews.size();
        for (int i = 0; i < size; i++) {
            PageView valueAt = this.mPageViews.valueAt(i);
            postUserInteractionBegin();
            valueAt.clean(valueAt.getPageIdx());
            removeViewInLayout(valueAt);
        }
        this.mPageViews.clear();
        post(this);
    }

    /* access modifiers changed from: protected */
    public void layout() {
        int keyAt = this.mPageViews.keyAt(0);
        PageView pageView = this.mPageViews.get(keyAt);
        if (pageView != null) {
            int pageIdx = pageView.getPageIdx();
            while (pageIdx < getStandaredPage(getPageIdx()) - 2) {
                removeViewInLayout(pageView);
                pageView.clean(pageIdx);
                this.mPageViews.remove(keyAt);
                keyAt = this.mPageViews.keyAt(0);
                pageView = this.mPageViews.get(keyAt);
                pageIdx = pageView.getPageIdx();
            }
            SparseArray<PageView> sparseArray = this.mPageViews;
            int keyAt2 = sparseArray.keyAt(sparseArray.size() - 1);
            PageView pageView2 = this.mPageViews.get(keyAt2);
            int pageIdx2 = pageView2.getPageIdx();
            while (pageIdx2 > getStandaredPage(getPageIdx()) + 3) {
                removeViewInLayout(pageView2);
                pageView2.clean(pageIdx2);
                this.mPageViews.remove(keyAt2);
                SparseArray<PageView> sparseArray2 = this.mPageViews;
                keyAt2 = sparseArray2.keyAt(sparseArray2.size() - 1);
                pageView2 = this.mPageViews.get(keyAt2);
                pageIdx2 = pageView2.getPageIdx();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setupPageView(int i, View view) {
        PageView pageView = (PageView) view;
        pageView.enableSearchHighlight((RectF[]) null);
        SearchInfo searchInfo = Register.getSearchInfo();
        if (searchInfo != null && searchInfo.getPageIdx() == i) {
            pageView.enableSearchHighlight(searchInfo.getAreaList());
        }
    }

    /* access modifiers changed from: protected */
    public void registPageView(int i, View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        addViewInLayout(view, 0, layoutParams, true);
        if (view instanceof PageView) {
            PageView pageView = this.mPageViews.get(i);
            if (pageView != null) {
                pageView.clean(pageView.getPageIdx());
            }
            this.mPageViews.append(i, (PageView) view);
        }
        measureView(view);
    }

    private void onGoToPage(int i) {
        this.mDoc.getRecentPageIdxList().clear();
        this.mDoc.insertRecentPageIdx(getStandaredPage(i));
        this.mDoc.insertRecentPageIdx(getStandaredPage(i) + 1);
        if (i < 0) {
            i = 0;
        } else if (i > pageCount() - 1) {
            i = pageCount() - 1;
        }
        SearchInfo searchInfo = Register.getSearchInfo();
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
        if ((this.mIsMinScale && PropertyManager.isKeepMinimumZoomLevel()) || getScale() < calcMinScale(this.mFitType)) {
            setScale(calcMinScale(this.mFitType));
            if (!this.mUserInteracting) {
                this.mScroller.forceFinished(true);
                if (getPageView() != null) {
                    slideViewOntoScreen();
                }
            }
        }
    }

    public void goToPage(int i) {
        if (this.mDoc != null && i >= 0 && i < this.mAdapter.getCount() && getPageIdx() != i) {
            if (getStandaredPage(getPageIdx()) == getStandaredPage(i)) {
                this.mCurPageIdx = i;
                return;
            }
            this.mLoadFinish = false;
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
            }
            this.mOrientationListener.disable();
            this.mPageViews.clear();
        }
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
        PageView pageView = this.mPageViews.get(getStandaredPage(this.mCurPageIdx));
        return pageView == null ? this.mPageViews.get(getStandaredPage(this.mCurPageIdx) + 1) : pageView;
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
    public boolean isValidPage(int i) {
        return i >= 0 && i < this.mDoc.getPageCount();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b7, code lost:
        if ((((double) getWidth()) / ((double) getHeight())) < ((double) (r4 / r0))) goto L_0x00b9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double calcMinScale(com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType r13) {
        /*
            r12 = this;
            com.epapyrus.plugpdf.core.viewer.PageView r0 = r12.getPageView()
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r0 != 0) goto L_0x000f
            double r3 = com.epapyrus.plugpdf.core.PropertyManager.getMinimumZoomLevel()
            double r3 = r3 * r1
            return r3
        L_0x000f:
            r0 = 0
            int r3 = r12.mCurPageIdx
            int r3 = r12.getStandaredPage(r3)
            boolean r3 = r12.isValidPage(r3)
            r4 = 0
            r5 = 0
            r6 = 1
            if (r3 == 0) goto L_0x0037
            int r3 = r12.mCurPageIdx
            int r3 = r12.getStandaredPage(r3)
            com.epapyrus.plugpdf.core.viewer.PageView r3 = r12.getPageView(r3)
            android.graphics.PointF r3 = r3.getOriginPageSize()
            if (r3 == 0) goto L_0x0038
            float r0 = r3.x
            float r4 = r4 + r0
            float r0 = r3.x
            r7 = r0
            r0 = 1
            goto L_0x0039
        L_0x0037:
            r3 = r5
        L_0x0038:
            r7 = 0
        L_0x0039:
            int r8 = r12.mCurPageIdx
            int r8 = r12.getStandaredPage(r8)
            int r8 = r8 + r6
            boolean r8 = r12.isValidPage(r8)
            if (r8 == 0) goto L_0x005e
            int r5 = r12.mCurPageIdx
            int r5 = r12.getStandaredPage(r5)
            int r5 = r5 + r6
            com.epapyrus.plugpdf.core.viewer.PageView r5 = r12.getPageView(r5)
            android.graphics.PointF r5 = r5.getOriginPageSize()
            if (r5 == 0) goto L_0x005e
            float r7 = r5.x
            float r4 = r4 + r7
            r0 = r0 ^ 1
            float r7 = r5.x
        L_0x005e:
            if (r0 == 0) goto L_0x0061
            float r4 = r4 + r7
        L_0x0061:
            if (r3 != 0) goto L_0x006a
            if (r5 != 0) goto L_0x006a
            double r0 = r12.getScale()
            return r0
        L_0x006a:
            if (r3 != 0) goto L_0x006e
            r3 = r5
            goto L_0x0075
        L_0x006e:
            if (r5 != 0) goto L_0x0072
            r5 = r3
            goto L_0x0075
        L_0x0072:
            r11 = r5
            r5 = r3
            r3 = r11
        L_0x0075:
            boolean r0 = r12.leftIsGreater()
            if (r0 == 0) goto L_0x007e
            float r0 = r5.y
            goto L_0x0080
        L_0x007e:
            float r0 = r3.y
        L_0x0080:
            int r3 = r12.getWidth()
            int r5 = r12.getOffset()
            int r3 = r3 - r5
            float r3 = (float) r3
            float r3 = r3 / r4
            int r5 = r12.getHeight()
            float r5 = (float) r5
            r7 = 1073741824(0x40000000, float:2.0)
            float r7 = r7 * r0
            float r5 = r5 / r7
            int[] r7 = com.epapyrus.plugpdf.core.viewer.BilateralPageDisplay.AnonymousClass5.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType
            int r13 = r13.ordinal()
            r13 = r7[r13]
            r7 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r13 == r6) goto L_0x00bc
            r6 = 2
            if (r13 == r6) goto L_0x00b9
            r6 = 3
            if (r13 == r6) goto L_0x00a8
            goto L_0x00bd
        L_0x00a8:
            int r13 = r12.getWidth()
            double r1 = (double) r13
            int r13 = r12.getHeight()
            double r9 = (double) r13
            double r1 = r1 / r9
            float r4 = r4 / r0
            double r9 = (double) r4
            int r13 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r13 >= 0) goto L_0x00bc
        L_0x00b9:
            float r3 = r3 / r5
            double r1 = (double) r3
            goto L_0x00bd
        L_0x00bc:
            r1 = r7
        L_0x00bd:
            double r3 = com.epapyrus.plugpdf.core.PropertyManager.getMinimumZoomLevel()
            double r1 = r1 * r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.BilateralPageDisplay.calcMinScale(com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType):double");
    }

    /* renamed from: com.epapyrus.plugpdf.core.viewer.BilateralPageDisplay$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.BilateralPageDisplay.AnonymousClass5.<clinit>():void");
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
        Rect rect;
        Rect rect2;
        super.onLayout(z, i, i2, i3, i4);
        if (this.mAdapter != null) {
            if (this.mResetLayout) {
                relayout();
            } else {
                layout();
            }
            View createPageView = createPageView(getPageIdx(), true);
            int i5 = 0;
            View createPageView2 = createPageView(getPageIdx(), false);
            if (createPageView2 != null || createPageView != null) {
                new Rect();
                if (createPageView2 != null) {
                    int left = createPageView2.getLeft() + this.mScrollPosX;
                    int top = createPageView2.getTop() + this.mScrollPosY;
                    rect = new Rect(left, top, (createPageView2.getMeasuredWidth() / 2) + left, (createPageView2.getMeasuredHeight() / 2) + top);
                } else {
                    int left2 = ((createPageView.getLeft() - (createPageView.getMeasuredWidth() / 2)) - ((int) (((double) getOffset()) * getScale()))) + this.mScrollPosX;
                    int top2 = createPageView.getTop() + this.mScrollPosY;
                    rect = new Rect(left2, top2, (createPageView.getMeasuredWidth() / 2) + left2, (createPageView.getMeasuredHeight() / 2) + top2);
                }
                new Rect();
                if (createPageView != null) {
                    int offset = rect.right + ((int) (((double) getOffset()) * getScale()));
                    int top3 = createPageView.getTop() + this.mScrollPosY;
                    rect2 = new Rect(offset, top3, (createPageView.getMeasuredWidth() / 2) + offset, (createPageView.getMeasuredHeight() / 2) + top3);
                } else {
                    int offset2 = rect.right + ((int) (((double) getOffset()) * getScale()));
                    int top4 = createPageView2.getTop() + this.mScrollPosY;
                    rect2 = new Rect(offset2, top4, (createPageView2.getMeasuredWidth() / 2) + offset2, (createPageView2.getMeasuredHeight() / 2) + top4);
                }
                if (leftIsGreater(createPageView2, createPageView)) {
                    int height = (rect.height() / 2) + rect.top;
                    rect2.top = height - (rect2.height() / 2);
                    rect2.bottom = height + (rect2.height() / 2);
                } else {
                    int height2 = (rect2.height() / 2) + rect2.top;
                    rect.top = height2 - (rect.height() / 2);
                    rect.bottom = height2 + (rect.height() / 2);
                }
                this.mScrollPosX = 0;
                this.mScrollPosY = 0;
                pageLayout(createPageView2, rect, createPageView, rect2, getSubScreenSizeOffset(createPageView2, createPageView));
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
                        clear();
                    }
                    this.mLoadFinish = true;
                    this.mDisplayListener.allTaskCompleted();
                }
            }
        }
    }

    public void run() {
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
        } else if (!this.mUserInteracting) {
            int currentPageIndexByScrollPosition = getCurrentPageIndexByScrollPosition();
            if (currentPageIndexByScrollPosition != this.mCurPageIdx) {
                this.mCurPageIdx = currentPageIndexByScrollPosition;
                onGoToPage(this.mCurPageIdx);
            }
            postUserInteractionComplete();
        }
    }

    public void onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.mListener != null) {
            this.mListener.onSingleTapUp(motionEvent);
        }
    }

    public void onDown(MotionEvent motionEvent) {
        this.mScroller.forceFinished(true);
    }

    public void onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float scrollVelocityCoef = f * PropertyManager.getScrollVelocityCoef();
        float scrollVelocityCoef2 = f2 * PropertyManager.getScrollVelocityCoef();
        if (!this.mScrollDisabled) {
            View view = this.mPageViews.get(getStandaredPage(getPageIdx()));
            View view2 = this.mPageViews.get(getStandaredPage(getPageIdx()) + 1);
            if (view != null || view2 != null) {
                if (this.mOuterFocusInterception) {
                    PointF returnValidScrollPoint = returnValidScrollPoint(scrollVelocityCoef, scrollVelocityCoef2);
                    float f3 = returnValidScrollPoint.x;
                    scrollVelocityCoef2 = returnValidScrollPoint.y;
                    scrollVelocityCoef = f3;
                }
                Rect scrollBounds = getScrollBounds(view, view2);
                if (fling(scrollBounds, scrollVelocityCoef, scrollVelocityCoef2)) {
                    onGoToPage(getPageIdx());
                    return;
                }
                this.mLastScrollX = 0;
                this.mLastScrollY = 0;
                Rect rect = new Rect(scrollBounds);
                rect.inset(-100, -100);
                if (rectInDirection(scrollBounds, scrollVelocityCoef, scrollVelocityCoef2) && rect.contains(0, 0)) {
                    this.mScroller.fling(0, 0, (int) scrollVelocityCoef, (int) scrollVelocityCoef2, scrollBounds.left, scrollBounds.right, scrollBounds.top, scrollBounds.bottom);
                    post(this);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public PointF returnValidScrollPoint(float f, float f2) {
        return new PointF(f, f2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mGesture.onTouchEvent(motionEvent)) {
            if (motionEvent.getActionMasked() == 0) {
                this.mUserInteracting = true;
            }
            if (motionEvent.getActionMasked() == 1) {
                this.mUserInteracting = false;
                if (this.mScroller.isFinished()) {
                    slideViewOntoScreen();
                    postUserInteractionComplete();
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
        double d;
        PageView pageView = getPageView();
        if (pageView != null) {
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
    }

    public void onScale(ScaleGestureDetector scaleGestureDetector) {
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
        setScrollDisabled(false);
    }

    public void onAnnotTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            PageView pageView = this.mPageViews.get(getStandaredPage(getPageIdx()));
            PageView pageView2 = this.mPageViews.get(getStandaredPage(getPageIdx()) + 1);
            if (pageView2 == null || x <= pageView2.getLeft()) {
                this.mAnnotTool.setPageView(pageView);
            } else {
                this.mAnnotTool.setPageView(pageView2);
            }
            this.mAnnotTool.onTouchBegin(x, y, this.mAnnotToolEventListener);
        } else if (action == 1) {
            this.mAnnotTool.onTouchEnd(x, y, this.mAnnotToolEventListener);
        } else if (action == 2) {
            this.mAnnotTool.onTouchMove(x, y, this.mAnnotToolEventListener);
        }
    }

    public void onAnnotSingleTapUp(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType) {
        if (getPageView() != null) {
            PageView pageView = this.mPageViews.get(getStandaredPage(getPageIdx()));
            PageView pageView2 = this.mPageViews.get(getStandaredPage(getPageIdx()) + 1);
            if (pageView2 != null && motionEvent.getX() > ((float) pageView2.getLeft())) {
                this.mAnnotTool.setPageView(pageView2);
            } else if (pageView != null && ((float) pageView.getRight()) > motionEvent.getX()) {
                this.mAnnotTool.setPageView(pageView);
            } else {
                return;
            }
            if (gestureType.equals(BaseGestureProcessor.GestureType.VIEW)) {
                Point point = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                BaseAnnot selectedAnnot = this.mAnnotTool.getSelectedAnnot(point);
                if (selectedAnnot == null) {
                    return;
                }
                if (this.mAnnotEventListener == null) {
                    this.mAnnotTool.singleTapUp(point.x, point.y, this.mAnnotToolEventListener);
                } else if (!this.mAnnotEventListener.onTapUp(selectedAnnot)) {
                    this.mAnnotTool.singleTapUp(point.x, point.y, this.mAnnotToolEventListener);
                }
            } else if (gestureType.equals(BaseGestureProcessor.GestureType.EDIT)) {
                this.mAnnotTool.singleTapUp((int) motionEvent.getX(), (int) motionEvent.getY(), this.mAnnotToolEventListener);
            }
        }
    }

    public void onAnnotLongPress(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType) {
        PageView pageView = this.mPageViews.get(getStandaredPage(getPageIdx()));
        PageView pageView2 = this.mPageViews.get(getStandaredPage(getPageIdx()) + 1);
        if (pageView2 != null && motionEvent.getX() > ((float) pageView2.getLeft())) {
            this.mAnnotTool.setPageView(pageView2);
        } else if (pageView != null && ((float) pageView.getRight()) < motionEvent.getX()) {
            this.mAnnotTool.setPageView(pageView);
        } else {
            return;
        }
        if (gestureType.equals(BaseGestureProcessor.GestureType.VIEW)) {
            Point point = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
            BaseAnnot selectedAnnot = this.mAnnotTool.getSelectedAnnot(point);
            if (selectedAnnot != null && this.mAnnotEventListener != null && !this.mAnnotEventListener.onLongPressDown(selectedAnnot)) {
                this.mAnnotTool.longPress(point.x, point.y);
            }
        } else if (gestureType.equals(BaseGestureProcessor.GestureType.VIEW)) {
            this.mAnnotTool.longPress((int) motionEvent.getX(), (int) motionEvent.getY());
        }
    }

    /* access modifiers changed from: protected */
    public float calcMeasureScale(View view) {
        if (getResources().getConfiguration().orientation == 2) {
            return ((float) getHeight()) / ((float) view.getMeasuredHeight());
        }
        return 1.0f;
    }

    public void scrollToView(int i, View view) {
        float f;
        float f2;
        boolean z = i != getPageIdx();
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
    }

    /* access modifiers changed from: protected */
    public int getStandaredPage(int i) {
        int i2;
        if (this.mUseCoverStyle) {
            i2 = Math.abs((i + 1) % 2);
        } else {
            i2 = i % 2;
        }
        return i - i2;
    }

    /* access modifiers changed from: protected */
    public boolean leftIsGreater() {
        int standaredPage = getStandaredPage(getPageIdx());
        return leftIsGreater(this.mPageViews.get(standaredPage), this.mPageViews.get(standaredPage + 1));
    }

    /* access modifiers changed from: protected */
    public boolean leftIsGreater(View view, View view2) {
        if (view == null) {
            return false;
        }
        return view2 == null || view.getMeasuredHeight() > view2.getMeasuredHeight();
    }
}

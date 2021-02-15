package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.epapyrus.plugpdf.core.PlugPDF;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import fi.harism.curl.CurlPage;
import fi.harism.curl.CurlView;

public class BilateralRealisticDisplay extends BilateralPageDisplay implements Runnable {
    /* access modifiers changed from: private */
    public int curlPageIndex;
    private boolean isPageFiliping;
    /* access modifiers changed from: private */
    public SparseArray<Bitmap> mBitmapArray = new SparseArray<>(6);
    /* access modifiers changed from: private */
    public CurlView mCurlView;

    /* access modifiers changed from: protected */
    public boolean fling(Rect rect, float f, float f2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void nearPageLayout(Point point, Rect rect, Rect rect2) {
    }

    /* access modifiers changed from: protected */
    public void relayout() {
    }

    public BilateralRealisticDisplay(Context context) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.BILATERAL_REALISTIC, false);
        this.mCurPageIdx = 0;
        this.mOuterFocusInterception = true;
        initCurlView(getPageIdx());
    }

    public void setOuterFocusInterception(boolean z) {
        this.mOuterFocusInterception = true;
    }

    /* access modifiers changed from: protected */
    public PointF returnValidScrollPoint(float f, float f2) {
        View view = (View) this.mPageViews.get(1);
        View view2 = (View) this.mPageViews.get(0);
        if (view == null) {
            view = view2;
        } else if (view2 == null) {
            view2 = view;
        }
        View view3 = leftIsGreater(view, view2) ? view : view2;
        if ((f > 0.0f && view.getLeft() >= 0) || (f < 0.0f && view2.getRight() <= getWidth())) {
            f = 0.0f;
        }
        if ((f2 > 0.0f && view3.getTop() >= 0) || (f2 < 0.0f && view3.getBottom() <= getHeight())) {
            f2 = 0.0f;
        }
        return new PointF(f, f2);
    }

    /* access modifiers changed from: private */
    public Bitmap getBitmapFrom(int i) {
        PointF pageSize = this.mDoc.getPageSize(i);
        Bitmap bitmap = this.mBitmapArray.get(i);
        if (bitmap != null && !bitmap.isRecycled()) {
            return bitmap;
        }
        int height = getHeight() / 2;
        int i2 = (((int) pageSize.x) * height) / ((int) pageSize.y);
        Bitmap createBitmap = Bitmap.createBitmap(i2, height, PlugPDF.bitmapConfig());
        createBitmap.eraseColor(-1);
        this.mDoc.drawPage(i, createBitmap, i2, height, 0, 0, i2, height);
        return createBitmap;
    }

    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        CurlView curlView = this.mCurlView;
        if (curlView != null) {
            curlView.setBackgroundColor(i);
        }
    }

    /* access modifiers changed from: private */
    public void initCurlView(int i) {
        CurlView curlView = new CurlView(getContext());
        this.mCurlView = curlView;
        curlView.setPageProvider(new PageProvider());
        this.mCurlView.setViewMode(2);
        this.mCurlView.setCurrentIndex(i);
        this.mCurlView.setVisibility(0);
        if (getBackground() instanceof ColorDrawable) {
            this.mCurlView.setBackgroundColor(((ColorDrawable) getBackground()).getColor());
        } else {
            this.mCurlView.setBackgroundColor(-12303292);
        }
        this.mCurlView.setAllowLastPageCurl(false);
        this.mCurlView.setListener(new CurlView.CurlListener() {
            public void onAnimateFinish() {
                BilateralRealisticDisplay.this.post(new Runnable() {
                    public void run() {
                        BilateralRealisticDisplay.this.bringToFront();
                        BilateralRealisticDisplay.this.requestLayout();
                        BilateralRealisticDisplay.this.onUserInteractionComplete((PageView) BilateralRealisticDisplay.this.mPageViews.get(1), (PageView) BilateralRealisticDisplay.this.mPageViews.get(0));
                    }
                });
            }

            public void onChangePage(int i) {
                if (i != BilateralRealisticDisplay.this.getPageIdx() / 2) {
                    int unused = BilateralRealisticDisplay.this.curlPageIndex = i * 2;
                    BilateralRealisticDisplay.this.post(new Runnable() {
                        public void run() {
                            PageView pageView = (PageView) BilateralRealisticDisplay.this.mPageViews.get(1);
                            PageView pageView2 = (PageView) BilateralRealisticDisplay.this.mPageViews.get(0);
                            if (BilateralRealisticDisplay.this.getPageIdx() > 1) {
                                pageView.clean(BilateralRealisticDisplay.this.getPageIdx() - 1);
                                Bitmap access$200 = BilateralRealisticDisplay.this.getBitmapFrom(BilateralRealisticDisplay.this.getPageIdx() - 1);
                                BilateralRealisticDisplay.this.mBitmapArray.put(BilateralRealisticDisplay.this.getPageIdx() - 1, access$200);
                                pageView.setBitmap(BilateralRealisticDisplay.this.getPageIdx() - 1, BilateralRealisticDisplay.this.mDoc.getPageSize(BilateralRealisticDisplay.this.getPageIdx()), access$200);
                                pageView.prepareAnnot();
                                pageView.prepareField();
                            }
                            if (pageView2 != null && BilateralRealisticDisplay.this.getPageIdx() < BilateralRealisticDisplay.this.mDoc.getPageCount()) {
                                pageView2.clean(BilateralRealisticDisplay.this.getPageIdx());
                                Bitmap access$2002 = BilateralRealisticDisplay.this.getBitmapFrom(BilateralRealisticDisplay.this.getPageIdx());
                                BilateralRealisticDisplay.this.mBitmapArray.put(BilateralRealisticDisplay.this.getPageIdx(), access$2002);
                                pageView2.setBitmap(BilateralRealisticDisplay.this.getPageIdx(), BilateralRealisticDisplay.this.mDoc.getPageSize(BilateralRealisticDisplay.this.getPageIdx()), access$2002);
                                pageView2.prepareAnnot();
                                pageView2.prepareField();
                                if (BilateralRealisticDisplay.this.mListener != null) {
                                    BilateralRealisticDisplay.this.mListener.onGoToPage(BilateralRealisticDisplay.this.getPageIdx() + 1, BilateralRealisticDisplay.this.pageCount());
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        viewGroup.addView(this.mCurlView);
        viewGroup.bringChildToFront(this);
        super.onAttachedToWindow();
    }

    public int getPageIdx() {
        return this.curlPageIndex;
    }

    /* access modifiers changed from: protected */
    public void layout() {
        if (getScrollY() != 0 || getScrollX() != 0) {
            this.mScroller.startScroll(0, 0, getScrollX(), getScrollY());
        }
    }

    public void goToPage(int i) {
        if (i >= 0 && i < this.mAdapter.getCount() && getPageIdx() != i) {
            PageView pageView = (PageView) this.mPageViews.get(1);
            PageView pageView2 = (PageView) this.mPageViews.get(0);
            int round = (int) ((double) Math.round(((double) i) / 2.0d));
            int i2 = round * 2;
            if (pageView == null && pageView2 == null) {
                this.curlPageIndex = i2;
                this.mCurlView.setCurrentIndex(round);
                return;
            }
            if (Math.round((float) (getPageIdx() / 2)) != round) {
                if (i2 > 0) {
                    pageView.clean(i2 - 1);
                }
                if (i2 <= this.mDoc.getPageCount() - 1) {
                    pageView2.clean(i2);
                }
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    pageView.setPage(i3, this.mDoc.getPageSize(i3));
                }
                if (i2 <= this.mDoc.getPageCount() - 1) {
                    pageView2.setPage(i2, this.mDoc.getPageSize(i2));
                }
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(this.mCurlView);
                initCurlView(round);
                viewGroup.addView(this.mCurlView);
                bringToFront();
                requestLayout();
            }
            this.curlPageIndex = i2;
            if (this.mListener == null) {
                return;
            }
            if (getPageIdx() + 1 > pageCount()) {
                this.mListener.onGoToPage(getPageIdx(), pageCount());
            } else {
                this.mListener.onGoToPage(getPageIdx() + 1, pageCount());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void pageLayout(View view, Rect rect, View view2, Rect rect2, Point point) {
        Point correctionPt = getCorrectionPt(getScrollBounds(rect, rect2));
        rect.right += correctionPt.x;
        rect.left += correctionPt.x;
        rect.top += correctionPt.y;
        rect.bottom += correctionPt.y;
        rect2.right += correctionPt.x;
        rect2.left += correctionPt.x;
        rect2.top += correctionPt.y;
        rect2.bottom += correctionPt.y;
        if (view != null) {
            view.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
        if (view2 != null) {
            view2.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        }
        nearPageLayout(point, rect, rect2);
    }

    public PageView getPageView() {
        return (PageView) this.mPageViews.get(0);
    }

    public PageView getPageView(int i) {
        return (PageView) this.mPageViews.get(getStandaredPage(this.curlPageIndex) - i);
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.view.View] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View createPageView(int r4, boolean r5) {
        /*
            r3 = this;
            r0 = 1
            if (r4 <= 0) goto L_0x001c
            if (r5 != 0) goto L_0x0007
            int r4 = r4 + -1
        L_0x0007:
            com.epapyrus.plugpdf.core.PDFDocument r5 = r3.mDoc
            int r5 = r5.getPageCount()
            int r5 = r5 - r0
            if (r4 <= r5) goto L_0x0017
            int r5 = r3.getStandaredPage(r4)
            int r4 = r4 + -1
            goto L_0x0028
        L_0x0017:
            int r5 = r3.getStandaredPage(r4)
            goto L_0x0028
        L_0x001c:
            r4 = 0
            if (r5 != 0) goto L_0x0024
            int r5 = r3.getStandaredPage(r0)
            goto L_0x0028
        L_0x0024:
            int r5 = r3.getStandaredPage(r4)
        L_0x0028:
            r1 = 0
            if (r4 < 0) goto L_0x0052
            com.epapyrus.plugpdf.core.PDFDocument r2 = r3.mDoc
            int r2 = r2.getPageCount()
            int r2 = r2 - r0
            if (r4 <= r2) goto L_0x0035
            goto L_0x0052
        L_0x0035:
            android.util.SparseArray r0 = r3.mPageViews
            java.lang.Object r0 = r0.get(r5)
            com.epapyrus.plugpdf.core.viewer.PageView r0 = (com.epapyrus.plugpdf.core.viewer.PageView) r0
            if (r0 != 0) goto L_0x004e
            android.widget.Adapter r0 = r3.mAdapter
            android.view.View r4 = r0.getView(r4, r1, r3)
            r0 = r4
            com.epapyrus.plugpdf.core.viewer.PageView r0 = (com.epapyrus.plugpdf.core.viewer.PageView) r0
            if (r0 != 0) goto L_0x004b
            return r1
        L_0x004b:
            r3.registPageView(r5, r0)
        L_0x004e:
            r3.setupPageView(r5, r0)
            return r0
        L_0x0052:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.BilateralRealisticDisplay.createPageView(int, boolean):android.view.View");
    }

    /* access modifiers changed from: protected */
    public int getStandaredPage(int i) {
        return i % 2;
    }

    /* access modifiers changed from: protected */
    public double calcMinScale(BasePlugPDFDisplay.FitType fitType) {
        if (getPageView() == null) {
            return 1.0d;
        }
        View view = (View) this.mPageViews.get(1);
        int i = 0;
        View view2 = (View) this.mPageViews.get(0);
        int width = view != null ? view.getWidth() : 0;
        if (view2 != null) {
            i = view2.getWidth();
        }
        double offset = ((double) (width + i)) + (((double) getOffset()) * getScale());
        double height = (double) (leftIsGreater(view, view2) ? view.getHeight() : view2.getHeight());
        int i2 = AnonymousClass4.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType[fitType.ordinal()];
        if (i2 == 1) {
            return (((double) getHeight()) / height) * getScale();
        }
        if (i2 == 2) {
            return (getScale() * ((double) getWidth())) / offset;
        }
        if (i2 != 3) {
            return getScale() * 1.0d;
        }
        if (((double) getWidth()) / ((double) getHeight()) < offset / height) {
            return calcMinScale(BasePlugPDFDisplay.FitType.WIDTH);
        }
        return calcMinScale(BasePlugPDFDisplay.FitType.HEIGHT);
    }

    /* renamed from: com.epapyrus.plugpdf.core.viewer.BilateralRealisticDisplay$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType[] r0 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType = r0
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.HEIGHT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.WIDTH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$FitType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$FitType r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.FitType.MINIMUM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.BilateralRealisticDisplay.AnonymousClass4.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View view = (View) this.mPageViews.get(1);
        View view2 = (View) this.mPageViews.get(0);
        int i5 = this.curlPageIndex;
        if (i5 < 1) {
            view.setVisibility(4);
            view2.setVisibility(0);
        } else if (i5 >= this.mDoc.getPageCount()) {
            view.setVisibility(0);
            view2.setVisibility(4);
        } else {
            view.setVisibility(0);
            if (view2 != null) {
                view2.setVisibility(0);
            }
        }
        Rect rect = null;
        if (view != null && view2 != null) {
            rect = new Rect(view.getLeft(), view.getHeight() > view2.getHeight() ? view.getTop() : view2.getTop(), view2.getRight(), view.getHeight() > view2.getHeight() ? view.getBottom() : view2.getBottom());
        } else if (view2 == null) {
            rect = new Rect(view.getLeft(), view.getTop(), view.getRight() + view.getWidth(), view.getBottom());
        }
        if (this.mCurlView.getLeft() != rect.left || this.mCurlView.getTop() != rect.top || this.mCurlView.getRight() != rect.right || this.mCurlView.getBottom() != rect.bottom) {
            int width = rect.width();
            int height = rect.height();
            if (width != 0 && height != 0) {
                this.mCurlView.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getCurrentPageIndexByScrollPosition() {
        return getPageIdx();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!wasFinishedLoad()) {
            return false;
        }
        if (!this.mGesture.onTouchEvent(motionEvent)) {
            if (motionEvent.getActionMasked() == 0) {
                this.mUserInteracting = true;
            }
            if (motionEvent.getActionMasked() == 1) {
                this.mUserInteracting = false;
                post(new Runnable() {
                    public void run() {
                        BilateralRealisticDisplay bilateralRealisticDisplay = BilateralRealisticDisplay.this;
                        bilateralRealisticDisplay.onUserInteractionComplete((PageView) bilateralRealisticDisplay.mPageViews.get(1), (PageView) BilateralRealisticDisplay.this.mPageViews.get(0));
                    }
                });
            }
        }
        if (motionEvent.getAction() == 1 && this.isPageFiliping) {
            this.mCurlView.onTouch(this, getCorrentMotionEvent(motionEvent));
            this.isPageFiliping = false;
        }
        return true;
    }

    public void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if ((((double) motionEvent.getX()) < ((double) getWidth()) * 0.9d || this.curlPageIndex == pageCount() - 1) && (((double) motionEvent.getX()) > ((double) getWidth()) * 0.1d || this.curlPageIndex == 0)) {
            super.onScroll(motionEvent, motionEvent2, f, f2);
            return;
        }
        if (!this.isPageFiliping) {
            this.mCurlView.bringToFront();
            this.mCurlView.requestLayout();
            this.mCurlView.onTouch(this, getCorrentMotionEvent(motionEvent));
            this.isPageFiliping = true;
        }
        this.mCurlView.onTouch(this, getCorrentMotionEvent(motionEvent2));
    }

    private MotionEvent getCorrentMotionEvent(MotionEvent motionEvent) {
        Rect rect;
        View view = (View) this.mPageViews.get(1);
        View view2 = (View) this.mPageViews.get(0);
        if (view == null || view2 == null) {
            rect = view2 == null ? new Rect(view.getLeft(), view.getTop(), view.getRight() + view.getWidth(), view.getBottom()) : null;
        } else {
            rect = new Rect(view.getLeft(), view.getHeight() > view2.getHeight() ? view.getTop() : view2.getTop(), view2.getRight(), view.getHeight() > view2.getHeight() ? view.getBottom() : view2.getBottom());
        }
        motionEvent.setLocation(motionEvent.getX() - ((float) rect.left), motionEvent.getY() - ((float) rect.top));
        return motionEvent;
    }

    private class PageProvider implements CurlView.PageProvider {
        private PageProvider() {
        }

        public int getPageCount() {
            if (BilateralRealisticDisplay.this.mDoc == null || BilateralRealisticDisplay.this.mDoc.wasReleased()) {
                return 0;
            }
            return BilateralRealisticDisplay.this.mDoc.getPageCount();
        }

        private Bitmap loadBitmap(int i, int i2, int i3) {
            if (i3 >= BilateralRealisticDisplay.this.mDoc.getPageCount()) {
                Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
                BilateralRealisticDisplay.this.getBackground().draw(new Canvas(createBitmap));
                return createBitmap;
            }
            Bitmap access$200 = BilateralRealisticDisplay.this.getBitmapFrom(i3);
            BilateralRealisticDisplay.this.mBitmapArray.put(i3, access$200);
            return access$200;
        }

        public void updatePage(CurlPage curlPage, int i, int i2, int i3) {
            int i4 = i3 * 2;
            curlPage.setTexture(loadBitmap(i, i2, i4), 1);
            Bitmap loadBitmap = loadBitmap(i, i2, i4 + 1);
            Matrix matrix = new Matrix();
            matrix.setScale(-1.0f, 1.0f);
            curlPage.setTexture(Bitmap.createBitmap(loadBitmap, 0, 0, loadBitmap.getWidth(), loadBitmap.getHeight(), matrix, false), 2);
        }
    }

    public void refreshLayout() {
        postDelayed(new Runnable() {
            public void run() {
                PageView pageView = BilateralRealisticDisplay.this.getPageView();
                if (pageView != null) {
                    pageView.clean(BilateralRealisticDisplay.this.curlPageIndex);
                    pageView.setPage(BilateralRealisticDisplay.this.curlPageIndex, BilateralRealisticDisplay.this.mDoc.getPageSize(BilateralRealisticDisplay.this.curlPageIndex));
                    ViewGroup viewGroup = (ViewGroup) BilateralRealisticDisplay.this.getParent();
                    viewGroup.removeView(BilateralRealisticDisplay.this.mCurlView);
                    BilateralRealisticDisplay bilateralRealisticDisplay = BilateralRealisticDisplay.this;
                    bilateralRealisticDisplay.initCurlView(bilateralRealisticDisplay.curlPageIndex);
                    viewGroup.addView(BilateralRealisticDisplay.this.mCurlView);
                }
            }
        }, 100);
    }
}

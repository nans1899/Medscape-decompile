package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.PlugPDF;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import fi.harism.curl.CurlPage;
import fi.harism.curl.CurlView;

public class RealisticDisplay extends SinglePageDisplay implements Runnable {
    /* access modifiers changed from: private */
    public Bitmap backPageBm;
    /* access modifiers changed from: private */
    public int curlPageIndex;
    /* access modifiers changed from: private */
    public Bitmap frontPageBm;
    private boolean isPageFiliping;
    /* access modifiers changed from: private */
    public SparseArray<Bitmap> mBitmapArray = new SparseArray<>(3);
    /* access modifiers changed from: private */
    public SparseArray<CurlPage> mCurlPages = new SparseArray<>(3);
    /* access modifiers changed from: private */
    public CurlView mCurlView;

    /* access modifiers changed from: protected */
    public boolean fling(View view, float f, float f2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void relayout() {
    }

    public RealisticDisplay(Context context) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.REALISTIC);
        this.mCurPageIdx = 0;
        this.mOuterFocusInterception = true;
        initCurlView(this.mCurPageIdx);
    }

    public void setOuterFocusInterception(boolean z) {
        this.mOuterFocusInterception = true;
    }

    /* access modifiers changed from: protected */
    public PointF returnValidScrollPoint(float f, float f2) {
        PageView pageView = getPageView();
        if ((f > 0.0f && pageView.getLeft() >= 0) || (f < 0.0f && pageView.getRight() <= getWidth())) {
            f = 0.0f;
        }
        if ((f2 > 0.0f && pageView.getTop() >= 0) || (f2 < 0.0f && pageView.getBottom() <= getHeight())) {
            f2 = 0.0f;
        }
        return new PointF(f, f2);
    }

    /* access modifiers changed from: private */
    public Bitmap getBitmapFrom(int i) {
        return getBitmapFrom((CurlPage) null, i);
    }

    /* access modifiers changed from: private */
    public Bitmap getBitmapFrom(CurlPage curlPage, int i) {
        Bitmap bitmap = this.mBitmapArray.get(i);
        if (bitmap != null && !bitmap.isRecycled()) {
            return bitmap;
        }
        PointF pageSize = this.mDoc.getPageSize(i);
        final int height = (int) (((double) ((float) getHeight())) * PropertyManager.getPreviewQualityCoef());
        final int i2 = (((int) pageSize.x) * height) / ((int) pageSize.y);
        Bitmap createBitmap = Bitmap.createBitmap(i2, height, PlugPDF.bitmapConfig());
        createBitmap.eraseColor(-1);
        final int i3 = i;
        final CurlPage curlPage2 = curlPage;
        new AsyncTask<Bitmap, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Bitmap... bitmapArr) {
                PDFDocument pDFDocument = RealisticDisplay.this.mDoc;
                int i = i3;
                Bitmap bitmap = bitmapArr[0];
                int i2 = i2;
                int i3 = height;
                pDFDocument.drawPage(i, bitmap, i2, i3, 0, 0, i2, i3);
                CurlPage curlPage = curlPage2;
                if (curlPage == null) {
                    curlPage = (CurlPage) RealisticDisplay.this.mCurlPages.get(i3);
                }
                if (curlPage == null) {
                    return null;
                }
                curlPage.setTexture(bitmapArr[0], 1);
                return null;
            }
        }.execute(new Bitmap[]{createBitmap});
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
        this.mCurlView.setViewMode(1);
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
                RealisticDisplay.this.post(new Runnable() {
                    public void run() {
                        RealisticDisplay.this.bringToFront();
                        RealisticDisplay.this.requestLayout();
                        RealisticDisplay.this.onUserInteractionComplete(RealisticDisplay.this.getPageView());
                    }
                });
            }

            public void onChangePage(int i) {
                if (i != RealisticDisplay.this.getPageIdx()) {
                    int unused = RealisticDisplay.this.curlPageIndex = i;
                    RealisticDisplay.this.post(new Runnable() {
                        public void run() {
                            PageView pageView = RealisticDisplay.this.getPageView();
                            pageView.clean(RealisticDisplay.this.getPageIdx());
                            Bitmap access$300 = RealisticDisplay.this.getBitmapFrom(RealisticDisplay.this.getPageIdx());
                            RealisticDisplay.this.mBitmapArray.put(RealisticDisplay.this.getPageIdx(), access$300);
                            pageView.setBitmap(RealisticDisplay.this.getPageIdx(), RealisticDisplay.this.mDoc.getPageSize(RealisticDisplay.this.getPageIdx()), access$300);
                            pageView.prepareAnnot();
                            pageView.prepareField();
                            if (RealisticDisplay.this.mListener != null) {
                                RealisticDisplay.this.mListener.onGoToPage(RealisticDisplay.this.getPageIdx() + 1, RealisticDisplay.this.pageCount());
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
        Bitmap bitmap = this.mBitmapArray.get(this.mBitmapArray.keyAt(0));
        while (bitmap != null) {
            int keyAt = this.mBitmapArray.keyAt(0);
            Bitmap bitmap2 = this.mBitmapArray.get(keyAt);
            if (keyAt >= this.curlPageIndex - 2) {
                SparseArray<Bitmap> sparseArray = this.mBitmapArray;
                keyAt = sparseArray.keyAt(sparseArray.size() - 1);
                bitmap2 = this.mBitmapArray.get(keyAt);
                if (keyAt <= this.curlPageIndex + 2) {
                    break;
                }
            }
            bitmap2.recycle();
            this.mBitmapArray.remove(keyAt);
            bitmap = this.mBitmapArray.get(this.mBitmapArray.keyAt(0));
        }
        if (getScrollY() != 0 || getScrollX() != 0) {
            this.mScroller.startScroll(0, 0, getScrollX(), getScrollY());
        }
    }

    public void goToPage(int i) {
        if (i >= 0 && i < this.mAdapter.getCount() && getPageIdx() != i) {
            PageView pageView = getPageView();
            if (pageView == null) {
                this.mCurPageIdx = i;
                this.curlPageIndex = i;
                this.mCurlView.setCurrentIndex(i);
                return;
            }
            if (getPageIdx() != i) {
                pageView.clean(0);
                pageView.setPage(i, this.mDoc.getPageSize(i));
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(this.mCurlView);
                initCurlView(i);
                viewGroup.addView(this.mCurlView);
                bringToFront();
                requestLayout();
            }
            this.curlPageIndex = i;
            if (this.mListener != null) {
                this.mListener.onGoToPage(getPageIdx() + 1, pageCount());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void nearPageLayout(Point point, int i, int i2, int i3, int i4) {
        if (getPageIdx() > 0) {
            Bitmap bitmapFrom = getBitmapFrom(getPageIdx() - 1);
            this.mBitmapArray.put(getPageIdx() - 1, bitmapFrom);
            this.frontPageBm = bitmapFrom;
        }
        if (getPageIdx() < this.mAdapter.getCount() - 1) {
            Bitmap bitmapFrom2 = getBitmapFrom(getPageIdx() + 1);
            this.mBitmapArray.put(getPageIdx() + 1, bitmapFrom2);
            this.backPageBm = bitmapFrom2;
        }
    }

    /* access modifiers changed from: protected */
    public void pageLayout(View view, int i, int i2, int i3, int i4, Point point) {
        Point correctionPt = getCorrectionPt(getScrollBounds(i, i2, i3, i4));
        int i5 = i3 + correctionPt.x;
        int i6 = i + correctionPt.x;
        int i7 = i2 + correctionPt.y;
        int i8 = i4 + correctionPt.y;
        view.layout(i6, i7, i5, i8);
        nearPageLayout(point, i6, i7, i5, i8);
    }

    public PageView getPageView() {
        return (PageView) this.mPageViews.get(0);
    }

    public PageView getPageView(int i) {
        return (PageView) this.mPageViews.get(0);
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [android.view.View] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View createPageView(int r4) {
        /*
            r3 = this;
            android.util.SparseArray r0 = r3.mPageViews
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            com.epapyrus.plugpdf.core.viewer.PageView r0 = (com.epapyrus.plugpdf.core.viewer.PageView) r0
            if (r0 != 0) goto L_0x001b
            android.widget.Adapter r0 = r3.mAdapter
            r2 = 0
            android.view.View r4 = r0.getView(r4, r2, r3)
            r0 = r4
            com.epapyrus.plugpdf.core.viewer.PageView r0 = (com.epapyrus.plugpdf.core.viewer.PageView) r0
            if (r0 != 0) goto L_0x0018
            return r2
        L_0x0018:
            r3.registPageView(r1, r0)
        L_0x001b:
            r3.setupPageView(r1, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.RealisticDisplay.createPageView(int):android.view.View");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        PageView pageView = getPageView();
        if (this.mCurlView.getLeft() != pageView.getLeft() || this.mCurlView.getMargins().top != ((float) pageView.getTop()) / ((float) getHeight()) || this.mCurlView.getRight() != pageView.getRight() || this.mCurlView.getMargins().bottom != 1.0f - (((float) pageView.getBottom()) / ((float) getHeight()))) {
            int width = pageView.getWidth();
            int height = pageView.getHeight();
            if (width != 0 && height != 0) {
                this.mCurlView.setMargins(0.0f, ((float) pageView.getTop()) / ((float) getHeight()), 0.0f, 1.0f - (((float) pageView.getBottom()) / ((float) getHeight())));
                this.mCurlView.layout(pageView.getLeft(), 0, pageView.getRight(), getHeight());
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
                        RealisticDisplay realisticDisplay = RealisticDisplay.this;
                        realisticDisplay.onUserInteractionComplete(realisticDisplay.getPageView());
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
        PageView pageView = getPageView();
        if (((double) motionEvent.getX()) >= ((double) getWidth()) * 0.9d || this.curlPageIndex > pageCount() - 1 || ((double) motionEvent.getX()) <= ((double) getWidth()) * 0.1d || this.curlPageIndex < 0 || (this.mScale == calcMinScale(this.mFitType) && ((this.mFitType != BasePlugPDFDisplay.FitType.WIDTH || pageView.getWidth() >= pageView.getHeight()) && this.mScale == calcMinScale(this.mFitType) && (this.mFitType != BasePlugPDFDisplay.FitType.HEIGHT || pageView.getWidth() >= pageView.getHeight())))) {
            if (!this.isPageFiliping) {
                this.mCurlView.bringToFront();
                this.mCurlView.requestLayout();
                this.mCurlView.onTouch(this, getCorrentMotionEvent(motionEvent));
                this.isPageFiliping = true;
            }
            this.mCurlView.onTouch(this, getCorrentMotionEvent(motionEvent2));
            return;
        }
        super.onScroll(motionEvent, motionEvent2, f, f2);
    }

    private MotionEvent getCorrentMotionEvent(MotionEvent motionEvent) {
        motionEvent.setLocation(motionEvent.getX() - ((float) getPageView().getLeft()), motionEvent.getY() - ((float) getPageView().getTop()));
        return motionEvent;
    }

    private class PageProvider implements CurlView.PageProvider {
        private PageProvider() {
        }

        public int getPageCount() {
            if (RealisticDisplay.this.mDoc == null || RealisticDisplay.this.mDoc.wasReleased()) {
                return 0;
            }
            return RealisticDisplay.this.mDoc.getPageCount();
        }

        private Bitmap loadBitmap(CurlPage curlPage, int i) {
            if (RealisticDisplay.this.getPageIdx() < i) {
                if (RealisticDisplay.this.backPageBm != null && !RealisticDisplay.this.backPageBm.isRecycled()) {
                    return RealisticDisplay.this.backPageBm;
                }
            } else if (RealisticDisplay.this.getPageIdx() > i && RealisticDisplay.this.frontPageBm != null && !RealisticDisplay.this.frontPageBm.isRecycled()) {
                return RealisticDisplay.this.frontPageBm;
            }
            Bitmap access$700 = RealisticDisplay.this.getBitmapFrom(curlPage, i);
            RealisticDisplay.this.mBitmapArray.put(RealisticDisplay.this.getPageIdx(), access$700);
            return access$700;
        }

        public void updatePage(CurlPage curlPage, int i, int i2, int i3) {
            Bitmap loadBitmap = loadBitmap(curlPage, i3);
            RealisticDisplay.this.mCurlPages.put(i3, curlPage);
            curlPage.setTexture(loadBitmap, 1);
        }
    }

    public void refreshLayout() {
        postDelayed(new Runnable() {
            public void run() {
                PageView pageView = RealisticDisplay.this.getPageView();
                if (pageView != null) {
                    pageView.clean(RealisticDisplay.this.curlPageIndex);
                    pageView.setPage(RealisticDisplay.this.curlPageIndex, RealisticDisplay.this.mDoc.getPageSize(RealisticDisplay.this.curlPageIndex));
                    ViewGroup viewGroup = (ViewGroup) RealisticDisplay.this.getParent();
                    viewGroup.removeView(RealisticDisplay.this.mCurlView);
                    RealisticDisplay realisticDisplay = RealisticDisplay.this;
                    realisticDisplay.initCurlView(realisticDisplay.curlPageIndex);
                    viewGroup.addView(RealisticDisplay.this.mCurlView);
                }
            }
        }, 100);
    }
}

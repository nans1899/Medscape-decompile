package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.PlugPDF;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.annotation.AnnotFile;
import com.epapyrus.plugpdf.core.annotation.AnnotFreeText;
import com.epapyrus.plugpdf.core.annotation.AnnotInk;
import com.epapyrus.plugpdf.core.annotation.AnnotNote;
import com.epapyrus.plugpdf.core.annotation.AnnotPolygon;
import com.epapyrus.plugpdf.core.annotation.AnnotPolyline;
import com.epapyrus.plugpdf.core.annotation.AnnotRichMedia;
import com.epapyrus.plugpdf.core.annotation.AnnotSound;
import com.epapyrus.plugpdf.core.annotation.AnnotStamp;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.acroform.BaseField;
import com.epapyrus.plugpdf.core.annotation.acroform.ButtonField;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldCreator;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldEventListener;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldProperty;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldRule;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PageView extends ViewGroup {
    private static final int ANNOTATION_ADD_EVENT = 0;
    private static final int ANNOTATION_EDIT_EVENT = 1;
    private static final int ANNOTATION_REMOVE_EVENT = 2;
    /* access modifiers changed from: private */
    public List<BaseAnnot> mAnnotList;
    private AsyncTask<Void, Void, BaseAnnot[]> mAnnotTask;
    protected ProgressBar mBusyIndicator;
    protected final PDFDocument mDoc;
    private AsyncTask<Void, Void, Void> mDrawEntire;
    /* access modifiers changed from: private */
    public AsyncTask<Void, Void, Void> mDrawPatch = null;
    /* access modifiers changed from: private */
    public boolean mDrawnPage = false;
    /* access modifiers changed from: private */
    public boolean mDrawnPatch = false;
    protected ImageView mEntire;
    /* access modifiers changed from: private */
    public Bitmap mEntireBm;
    /* access modifiers changed from: private */
    public FieldEventListener mFieldEventListener;
    /* access modifiers changed from: private */
    public List<BaseField> mFieldList;
    private AsyncTask<Void, Void, FieldProperty[]> mFieldTask;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public boolean mIsBlank;
    /* access modifiers changed from: private */
    public boolean mIsDetached = false;
    /* access modifiers changed from: private */
    public PageViewListener mListener;
    /* access modifiers changed from: private */
    public boolean mLoadedAnnot = false;
    /* access modifiers changed from: private */
    public boolean mLoadedField = false;
    protected PointF mOriginPageSize;
    protected int mPageIdx;
    /* access modifiers changed from: private */
    public Paint mPaint = new Paint();
    protected Point mParentSize;
    /* access modifiers changed from: private */
    public ImageView mPatch;
    /* access modifiers changed from: private */
    public Rect mPatchArea;
    /* access modifiers changed from: private */
    public Bitmap mPatchBm = null;
    /* access modifiers changed from: private */
    public Point mPatchViewSize;
    /* access modifiers changed from: private */
    public PropertyManager.PageProperty mProperty;
    /* access modifiers changed from: private */
    public RectF[] mSearchBoxes;
    private View mSearchView;
    protected Point mSize;
    protected float mSourceScale;
    private boolean wasLoadedPage = false;

    public boolean isOpaque() {
        return true;
    }

    public boolean isLoadedPage() {
        return this.mDrawnPage && this.mLoadedAnnot && this.mLoadedField;
    }

    public PageView(Context context, PDFDocument pDFDocument, Point point) {
        super(context);
        this.mDoc = pDFDocument;
        this.mParentSize = point;
        this.mProperty = PropertyManager.getPageProperty();
        this.mAnnotList = new ArrayList();
        this.mFieldList = new ArrayList();
        setBackgroundColor(this.mProperty.getBackgroundColor());
    }

    public PDFDocument getDocument() {
        return this.mDoc;
    }

    public void setListener(PageViewListener pageViewListener) {
        this.mListener = pageViewListener;
    }

    public void setFieldEvenetListener(FieldEventListener fieldEventListener) {
        this.mFieldEventListener = fieldEventListener;
    }

    /* access modifiers changed from: protected */
    public void drawPage(Bitmap bitmap, int i, int i2, int i3, int i4, int i5, int i6) {
        this.mDoc.drawPage(this.mPageIdx, bitmap, i, i2, i3, i4, i5, i6);
    }

    public float getSourceScale() {
        return this.mSourceScale;
    }

    public int getPageIdx() {
        return this.mPageIdx;
    }

    public float getAnnotScale() {
        return (getSourceScale() * ((float) getWidth())) / ((float) this.mSize.x);
    }

    public void clean(int i) {
        cancelDrawEntire();
        cancelAdjustPatch();
        cancelAnnotTask();
        cancelFieldTask();
        this.mIsBlank = true;
        this.mPageIdx = i;
        if (this.mSize == null) {
            this.mSize = this.mParentSize;
        }
        Log.d("PlugPDF", "[DEBUG] Cleaning page: " + i);
        clearEntire();
        clearPatch();
        for (BaseAnnot removeView : this.mAnnotList) {
            removeView(removeView);
        }
        for (BaseField removeView2 : this.mFieldList) {
            removeView(removeView2);
        }
        this.mAnnotList.clear();
        this.mFieldList.clear();
        createBusyIndicator();
        this.mProperty = PropertyManager.getPageProperty();
    }

    public boolean setBitmap(int i, PointF pointF, Bitmap bitmap) {
        cancelDrawEntire();
        cancelAnnotTask();
        cancelBusyIndicator();
        if (bitmap.isRecycled()) {
            Log.d("PlugPDF", "recycled bitmap, at " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + bitmap.isRecycled());
            return false;
        }
        this.mOriginPageSize = pointF;
        this.mIsBlank = false;
        this.mPageIdx = i;
        clearEntire();
        createEntire();
        try {
            Bitmap copy = bitmap.copy(PlugPDF.bitmapConfig(), false);
            this.mEntireBm = copy;
            this.mEntire.setImageBitmap(copy);
            calculateSize(pointF);
            requestLayout();
            return true;
        } catch (Exception unused) {
            Log.d("PlugPDF", "recycled bitmap, at " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + bitmap.isRecycled());
            return false;
        }
    }

    public void setPage(int i, PointF pointF) {
        if (getParent() != null || !PropertyManager.usePageLoadThread()) {
            this.mOriginPageSize = pointF;
            this.mIsBlank = false;
            this.mPageIdx = i;
            calculateSize(pointF);
            drawEntire();
            prepareAnnot();
            prepareField();
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void drawEntire() {
        cancelDrawEntire();
        createEntire();
        createEntireBitmap();
        createDrawEntire();
        this.mDrawEntire.execute(new Void[0]);
        createSearchView();
    }

    /* access modifiers changed from: protected */
    public void prepareAnnot() {
        cancelAnnotTask();
        createAnnotTask();
        this.mAnnotTask.execute(new Void[0]);
    }

    /* access modifiers changed from: protected */
    public void prepareField() {
        cancelFieldTask();
        createFieldTask();
        this.mFieldTask.execute(new Void[0]);
    }

    public void enableSearchHighlight(RectF[] rectFArr) {
        this.mSearchBoxes = rectFArr;
        View view = this.mSearchView;
        if (view != null) {
            view.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (View.MeasureSpec.getMode(i) != 0) {
            i3 = View.MeasureSpec.getSize(i);
        } else {
            i3 = this.mSize.x;
        }
        if (View.MeasureSpec.getMode(i2) != 0) {
            i4 = View.MeasureSpec.getSize(i2);
        } else {
            i4 = this.mSize.y;
        }
        setMeasuredDimension(i3, i4);
        if (this.mBusyIndicator != null) {
            int min = (Math.min(this.mParentSize.x, this.mParentSize.y) / 2) | Integer.MIN_VALUE;
            this.mBusyIndicator.measure(min, min);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        ImageView imageView = this.mEntire;
        if (imageView != null) {
            imageView.layout(0, 0, i5, i6);
        }
        Point point = this.mPatchViewSize;
        if (!(point == null || (point.x == i5 && this.mPatchViewSize.y == i6))) {
            this.mPatchViewSize = null;
            this.mPatchArea = null;
            ImageView imageView2 = this.mPatch;
            if (imageView2 != null) {
                imageView2.setVisibility(4);
            }
        }
        View view = this.mSearchView;
        if (view != null) {
            view.layout(0, 0, i5, i6);
        }
        ProgressBar progressBar = this.mBusyIndicator;
        if (progressBar != null) {
            int measuredWidth = progressBar.getMeasuredWidth();
            int measuredHeight = this.mBusyIndicator.getMeasuredHeight();
            this.mBusyIndicator.layout((i5 - measuredWidth) / 2, (i6 - measuredHeight) / 2, (measuredWidth + i5) / 2, (measuredHeight + i6) / 2);
        }
        for (int i7 = 0; i7 < this.mFieldList.size(); i7++) {
            BaseField baseField = this.mFieldList.get(i7);
            baseField.bringToFront();
            baseField.setScale(getAnnotScale());
        }
        for (int i8 = 0; i8 < this.mAnnotList.size(); i8++) {
            BaseAnnot baseAnnot = this.mAnnotList.get(i8);
            baseAnnot.bringToFront();
            baseAnnot.setScale(getAnnotScale());
            baseAnnot.layout(0, 0, i5, i6);
        }
    }

    private void redrawAP(BaseAnnot baseAnnot) {
        if (((baseAnnot instanceof AnnotNote) || (baseAnnot instanceof AnnotFile) || (baseAnnot instanceof AnnotStamp) || (baseAnnot instanceof AnnotFreeText) || (baseAnnot instanceof AnnotPolyline) || (baseAnnot instanceof AnnotPolygon) || (baseAnnot instanceof AnnotSound) || (baseAnnot instanceof AnnotRichMedia)) && baseAnnot.getObjID() != -1) {
            baseAnnot.setApBitmap(this.mDoc.drawAnnotAP(getPageIdx(), baseAnnot.getObjID(), 0, (double) getAnnotScale()));
        }
    }

    /* access modifiers changed from: protected */
    public void redrawAP() {
        for (int i = 0; i < this.mFieldList.size(); i++) {
            BaseField baseField = this.mFieldList.get(i);
            if (baseField instanceof ButtonField) {
                ((ButtonField) baseField).redrawAP();
            }
        }
        for (int i2 = 0; i2 < this.mAnnotList.size(); i2++) {
            redrawAP(this.mAnnotList.get(i2));
        }
    }

    private void cancelDrawPatch() {
        AsyncTask<Void, Void, Void> asyncTask = this.mDrawPatch;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.mDrawPatch = null;
        }
    }

    public void adjustPatch() {
        Log.d("PlugPDF", "[DEBUG] adjustPatch called");
        if (!this.mDoc.wasReleased() && ((ViewGroup) getParent()) != null && ((ViewGroup) getParent()).getChildCount() != 0) {
            Rect rect = new Rect(getLeft(), getTop(), getRight(), getBottom());
            final Point point = new Point(rect.width(), rect.height());
            final Rect rect2 = new Rect(0, 0, this.mParentSize.x, this.mParentSize.y);
            if (rect2.intersect(rect)) {
                rect2.offset(-rect.left, -rect.top);
                if (!rect2.equals(this.mPatchArea) || !point.equals(this.mPatchViewSize)) {
                    cancelDrawPatch();
                    this.mDrawnPage = false;
                    if (this.mPatch == null) {
                        Log.d("PlugPDF", "[DEBUG] Creating Patch View");
                        OpaqueImageView opaqueImageView = new OpaqueImageView(getContext());
                        this.mPatch = opaqueImageView;
                        opaqueImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        addView(this.mPatch, 1);
                        View view = this.mSearchView;
                        if (view != null) {
                            view.bringToFront();
                        }
                    }
                    clearPatch();
                    this.mDrawPatch = new AsyncTask<Void, Void, Void>() {
                        private Rect pa;
                        Bitmap patchBitmap = Bitmap.createBitmap(rect2.width(), rect2.height(), PlugPDF.bitmapConfig());
                        private Point pvs;

                        /* access modifiers changed from: protected */
                        public Void doInBackground(Void... voidArr) {
                            if (PageView.this.mDoc.wasReleased()) {
                                return null;
                            }
                            this.pvs = point;
                            this.pa = rect2;
                            if (!PageView.this.mDoc.getRecentPageIdxList().contains(Integer.valueOf(PageView.this.mPageIdx))) {
                                PageView.this.mDrawPatch.cancel(true);
                            } else {
                                Log.d("PlugPDF", "[DEBUG] Starting to draw a patch. Page : " + PageView.this.mPageIdx + "\n patchViewSize : " + this.pvs.x + " , " + this.pvs.y + " patchArea : " + this.pa.left + "," + this.pa.top + "," + this.pa.width() + "," + this.pa.height());
                                PageView.this.mDoc.drawPage(PageView.this.mPageIdx, this.patchBitmap, this.pvs.x, this.pvs.y, this.pa.left, this.pa.top, this.pa.width(), this.pa.height());
                                Log.d("PlugPDF", "[DEBUG] Complete to draw a patch");
                            }
                            return null;
                        }

                        /* access modifiers changed from: protected */
                        public void onCancelled(Void voidR) {
                            Bitmap bitmap = this.patchBitmap;
                            if (bitmap != null) {
                                bitmap.recycle();
                            }
                            boolean unused = PageView.this.mDrawnPage = true;
                            boolean unused2 = PageView.this.mDrawnPatch = true;
                        }

                        /* access modifiers changed from: protected */
                        public void onPostExecute(Void voidR) {
                            if (!PageView.this.mIsDetached) {
                                Bitmap unused = PageView.this.mPatchBm = this.patchBitmap;
                                Point unused2 = PageView.this.mPatchViewSize = this.pvs;
                                Rect unused3 = PageView.this.mPatchArea = this.pa;
                                if (PageView.this.mPatchBm != null) {
                                    PageView.this.mPatch.setImageBitmap(PageView.this.mPatchBm);
                                    PageView.this.mPatch.setVisibility(0);
                                    PageView.this.mPatch.layout(this.pa.left, this.pa.top, this.pa.right, this.pa.bottom);
                                    PageView.this.invalidate();
                                }
                                boolean unused4 = PageView.this.mDrawnPage = true;
                                boolean unused5 = PageView.this.mDrawnPatch = true;
                                if (PageView.this.isLoadedPage() && PageView.this.mDrawnPatch) {
                                    PageView.this.onPageLoadFinish();
                                }
                                PageView.this.requestLayout();
                            }
                        }
                    };
                    this.mPatchViewSize = point;
                    this.mPatchArea = rect2;
                    if (Math.abs(rect2.width() - point.x) >= rect2.width() / 20 || Math.abs(rect2.height() - point.y) >= rect2.height() / 20) {
                        this.mDrawPatch.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                        return;
                    }
                    this.mDrawnPage = true;
                    this.mDrawnPatch = true;
                }
            }
        }
    }

    public void cancelAdjustPatch() {
        Log.d("PlugPDF", "[DEBUG] Canceling patch adjustment\n");
        cancelDrawPatch();
        this.mPatchViewSize = null;
        this.mPatchArea = null;
        ImageView imageView = this.mPatch;
        if (imageView != null) {
            imageView.setImageBitmap((Bitmap) null);
        }
    }

    /* access modifiers changed from: protected */
    public void calculateSize(PointF pointF) {
        this.mSourceScale = Math.min(((float) this.mParentSize.x) / pointF.x, ((float) this.mParentSize.y) / pointF.y);
        this.mSize = new Point((int) (pointF.x * this.mSourceScale), (int) (pointF.y * this.mSourceScale));
    }

    private void cancelDrawEntire() {
        AsyncTask<Void, Void, Void> asyncTask = this.mDrawEntire;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.mDrawEntire = null;
        }
    }

    private void cancelAnnotTask() {
        AsyncTask<Void, Void, BaseAnnot[]> asyncTask = this.mAnnotTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.mAnnotTask = null;
        }
    }

    private void cancelFieldTask() {
        AsyncTask<Void, Void, FieldProperty[]> asyncTask = this.mFieldTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.mFieldTask = null;
        }
    }

    /* access modifiers changed from: protected */
    public void clearEntire() {
        Bitmap bitmap = this.mEntireBm;
        if (bitmap != null) {
            bitmap.recycle();
            this.mEntireBm = null;
            this.mEntire.setImageBitmap((Bitmap) null);
        }
    }

    private void clearPatch() {
        if (this.mPatch != null) {
            Log.d("PlugPDF", "[DEBUG] Clearing patch");
            this.mPatch.setImageBitmap((Bitmap) null);
        }
        if (this.mPatchBm != null) {
            this.mPatch.setImageBitmap((Bitmap) null);
            this.mPatchBm.recycle();
            this.mPatchBm = null;
        }
    }

    private void createBusyIndicator() {
        if (this.mBusyIndicator == null) {
            ProgressBar progressBar = new ProgressBar(getContext());
            this.mBusyIndicator = progressBar;
            progressBar.setIndeterminate(true);
            addView(this.mBusyIndicator);
        }
    }

    private void createEntire() {
        if (this.mEntire == null) {
            Log.d("PlugPDF", "[DEBUG] Creating Entire View");
            OpaqueImageView opaqueImageView = new OpaqueImageView(getContext());
            this.mEntire = opaqueImageView;
            opaqueImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.mEntire, 0);
        }
    }

    /* access modifiers changed from: private */
    public Point scaleEntireBitmapSize(Point point) {
        double previewQualityCoef = PropertyManager.getPreviewQualityCoef();
        return new Point((int) (((double) point.x) * previewQualityCoef), (int) (((double) point.y) * previewQualityCoef));
    }

    private void createEntireBitmap() {
        Point scaleEntireBitmapSize = scaleEntireBitmapSize(this.mSize);
        Bitmap bitmap = this.mEntireBm;
        if (bitmap == null || bitmap.getWidth() != scaleEntireBitmapSize.x || this.mEntireBm.getHeight() != scaleEntireBitmapSize.y || this.mEntireBm.isRecycled()) {
            Bitmap bitmap2 = this.mEntireBm;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                this.mEntireBm.recycle();
            }
            this.mEntireBm = Bitmap.createBitmap(scaleEntireBitmapSize.x, scaleEntireBitmapSize.y, PlugPDF.bitmapConfig());
        }
    }

    private void cancelBusyIndicator() {
        ProgressBar progressBar = this.mBusyIndicator;
        if (progressBar != null) {
            removeView(progressBar);
            this.mBusyIndicator = null;
        }
    }

    /* access modifiers changed from: private */
    public void onPageLoadFinish() {
        if (getParent() == null) {
            clean(this.mPageIdx);
        }
        PageViewListener pageViewListener = this.mListener;
        if (pageViewListener != null && !this.wasLoadedPage) {
            pageViewListener.onPageLoadFinish(this.mPageIdx);
        }
        this.wasLoadedPage = true;
    }

    private void createDrawEntire() {
        this.mDrawEntire = new AsyncTask<Void, Void, Void>() {
            Point sz;
            Bitmap wholeBitmap;

            {
                PageView pageView = PageView.this;
                Point access$900 = pageView.scaleEntireBitmapSize(pageView.mSize);
                this.sz = access$900;
                this.wholeBitmap = Bitmap.createBitmap(access$900.x, this.sz.y, PlugPDF.bitmapConfig());
            }

            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                if (PageView.this.mDoc.wasReleased()) {
                    return null;
                }
                Log.d("PlugPDF", "[DEBUG] Starting to draw a Enter. Page: " + PageView.this.mPageIdx + " (widht,height) : (" + this.sz.x + ", " + this.sz.y + ")");
                PageView.this.mDoc.drawPage(PageView.this.mPageIdx, this.wholeBitmap, this.sz.x, this.sz.y, 0, 0, this.sz.x, this.sz.y);
                StringBuilder sb = new StringBuilder();
                sb.append("[DEBUG] Complete to draw a Enter. Page: ");
                sb.append(PageView.this.mPageIdx);
                Log.d("PlugPDF", sb.toString());
                return null;
            }

            /* access modifiers changed from: protected */
            public void onPreExecute() {
                PageView.this.clearEntire();
                if (PageView.this.mBusyIndicator == null) {
                    PageView.this.mBusyIndicator = new ProgressBar(PageView.this.getContext());
                    PageView.this.mBusyIndicator.setIndeterminate(true);
                    PageView pageView = PageView.this;
                    pageView.addView(pageView.mBusyIndicator);
                    PageView.this.mBusyIndicator.setVisibility(4);
                    PageView.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (PageView.this.mBusyIndicator != null) {
                                PageView.this.mBusyIndicator.setVisibility(0);
                            }
                        }
                    }, (long) PageView.this.mProperty.getProgressDialogDelay());
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                if (PageView.this.mIsDetached) {
                    finish();
                    return;
                }
                Bitmap unused = PageView.this.mEntireBm = this.wholeBitmap;
                PageView pageView = PageView.this;
                pageView.removeView(pageView.mBusyIndicator);
                PageView.this.mBusyIndicator = null;
                PageView.this.mEntire.setImageBitmap(PageView.this.mEntireBm);
                PageView.this.invalidate();
                finish();
                if (PropertyManager.isEnableAdjustpatch()) {
                    PageView.this.adjustPatch();
                }
            }

            private void finish() {
                boolean unused = PageView.this.mDrawnPage = true;
                if (PageView.this.mListener != null) {
                    PageView.this.mListener.cachePageBitmap(PageView.this.mPageIdx, PageView.this.mEntireBm);
                }
            }
        };
    }

    private void createAnnotTask() {
        this.mAnnotTask = new AsyncTask<Void, Void, BaseAnnot[]>() {
            /* access modifiers changed from: protected */
            public BaseAnnot[] doInBackground(Void... voidArr) {
                if (PageView.this.mDoc.wasReleased()) {
                    return null;
                }
                return PageView.this.mDoc.loadAnnotList(PageView.this.mPageIdx);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(BaseAnnot[] baseAnnotArr) {
                if (PageView.this.mIsDetached || baseAnnotArr == null) {
                    finish((List<BaseAnnot>) null);
                    return;
                }
                for (BaseAnnot baseAnnot : baseAnnotArr) {
                    if (baseAnnot != null) {
                        PageView.this.addAnnot(baseAnnot);
                    }
                }
                finish(PageView.this.mAnnotList);
            }

            private void finish(List<BaseAnnot> list) {
                boolean unused = PageView.this.mLoadedAnnot = true;
                if (!(list == null || PageView.this.mListener == null)) {
                    PageView.this.mListener.onAnnotationList(PageView.this.mPageIdx, PageView.this.mAnnotList);
                }
                if (PageView.this.isLoadedPage() && PageView.this.mDrawnPatch) {
                    PageView.this.onPageLoadFinish();
                    PageView.this.redrawAP();
                }
                PageView.this.requestLayout();
            }
        };
    }

    private void createFieldTask() {
        this.mFieldTask = new AsyncTask<Void, Void, FieldProperty[]>() {
            /* access modifiers changed from: protected */
            public FieldProperty[] doInBackground(Void... voidArr) {
                if (PageView.this.mDoc.wasReleased()) {
                    return null;
                }
                return PageView.this.mDoc.loadFieldList(PageView.this.mPageIdx);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(FieldProperty[] fieldPropertyArr) {
                BaseField create;
                if (PageView.this.mIsDetached || fieldPropertyArr == null) {
                    finish((List<BaseField>) null);
                    return;
                }
                List<FieldProperty> asList = Arrays.asList(fieldPropertyArr);
                Collections.reverse(asList);
                FieldRule instance = FieldRule.instance();
                ArrayList arrayList = new ArrayList();
                for (FieldProperty fieldProperty : asList) {
                    if (!(fieldProperty == null || (create = FieldCreator.create(PageView.this.getContext(), PageView.this.mDoc, fieldProperty)) == null)) {
                        if (create.getType().equals("RADIO_BUTTON") || create.getType().equals("TEXT_FIELD") || create.getType().equals("CHECK_BOX") || create.getType().equals("Signature_Field")) {
                            arrayList.add(create);
                        }
                        create.setListener(PageView.this.mFieldEventListener);
                        instance.applyRule(create);
                        PageView.this.addField(create);
                    }
                }
                FieldCreator.grouping(arrayList);
                finish(PageView.this.mFieldList);
            }

            private void finish(List<BaseField> list) {
                boolean unused = PageView.this.mLoadedField = true;
                if (!(list == null || PageView.this.mListener == null)) {
                    PageView.this.mListener.onFieldList(PageView.this.mPageIdx, PageView.this.mFieldList);
                }
                if (PageView.this.isLoadedPage() && PageView.this.mDrawnPatch) {
                    PageView.this.onPageLoadFinish();
                    PageView.this.redrawAP();
                }
                PageView.this.requestLayout();
            }
        };
    }

    private void createSearchView() {
        if (this.mSearchView == null) {
            AnonymousClass5 r0 = new View(getContext()) {
                /* access modifiers changed from: protected */
                public void onDraw(Canvas canvas) {
                    super.onDraw(canvas);
                    float width = (PageView.this.mSourceScale * ((float) getWidth())) / ((float) PageView.this.mSize.x);
                    if (!PageView.this.mIsBlank && PageView.this.mSearchBoxes != null) {
                        PageView.this.mPaint.setColor(PageView.this.mProperty.getHighlightColor());
                        for (RectF rectF : PageView.this.mSearchBoxes) {
                            canvas.drawRect(rectF.left * width, rectF.top * width, rectF.right * width, rectF.bottom * width, PageView.this.mPaint);
                        }
                    }
                }
            };
            this.mSearchView = r0;
            addView(r0);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: private */
    public void addField(BaseField baseField) {
        this.mFieldList.add(baseField);
        addView(baseField);
        baseField.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (PageView.this.getParent() instanceof BasePlugPDFDisplay) {
                    if (motionEvent.getActionMasked() == 0) {
                        ((BasePlugPDFDisplay) PageView.this.getParent()).setScrollDisabled(true);
                    } else if (motionEvent.getActionMasked() == 1) {
                        ((BasePlugPDFDisplay) PageView.this.getParent()).setScrollDisabled(false);
                    }
                    ((View) PageView.this.getParent()).onTouchEvent(motionEvent);
                }
                return false;
            }
        });
    }

    public List<BaseField> getFieldList() {
        return this.mFieldList;
    }

    public void addAnnot(BaseAnnot baseAnnot) {
        if (baseAnnot != null) {
            this.mAnnotList.add(baseAnnot);
            addView(baseAnnot);
            redrawAP(baseAnnot);
        }
    }

    public List<BaseAnnot> getAnnotList() {
        return this.mAnnotList;
    }

    public void removeAnnotFromPageView(int i) {
        for (int i2 = 0; i2 < this.mAnnotList.size(); i2++) {
            BaseAnnot baseAnnot = this.mAnnotList.get(i2);
            if (baseAnnot.getObjID() == i) {
                removeView(baseAnnot);
                this.mAnnotList.remove(i2);
            }
        }
    }

    public void removeAnnotFromPDF(int i) {
        this.mDoc.removeAnnot(this.mPageIdx, i);
        removeAnnotFromPageView(i);
        onAnnotationEdited(2);
    }

    public BaseAnnot getAnnot(int i) {
        for (int i2 = 0; i2 < this.mAnnotList.size(); i2++) {
            BaseAnnot baseAnnot = this.mAnnotList.get(i2);
            if (baseAnnot.getObjID() == i) {
                return baseAnnot;
            }
        }
        return null;
    }

    public ArrayList<RectF> extractTextRects(RectF rectF, boolean z, boolean z2, boolean z3) {
        return this.mDoc.extractTextRects(this.mPageIdx, rectF, z, z2, z3);
    }

    public String extractText(RectF rectF) {
        return this.mDoc.extractText(this.mPageIdx, rectF);
    }

    public String extractText(RectF rectF, RectF rectF2) {
        return this.mDoc.extractText(this.mPageIdx, rectF, rectF2);
    }

    public void addInkAnnot(int i, int i2, ArrayList<ArrayList<PointF>> arrayList) {
        if (arrayList.get(0).size() >= 2) {
            PointF[][] pointFArr = new PointF[arrayList.size()][];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                pointFArr[i3] = (PointF[]) arrayList.get(i3).toArray(new PointF[0]);
            }
            addAnnot(this.mDoc.insertInkAnnot(this.mPageIdx, pointFArr, Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i), (float) i2));
            onAnnotationEdited(0);
        }
    }

    public void addLineAnnot(PointF pointF, PointF pointF2, int i, int i2) {
        addAnnot(this.mDoc.insertLineAnnot(this.mPageIdx, pointF, pointF2, Color.red(i2), Color.green(i2), Color.blue(i2), Color.alpha(i2), (float) i));
        onAnnotationEdited(0);
    }

    public void updateLineAnnot(PointF pointF, PointF pointF2, int i, int i2, int i3) {
        this.mDoc.updateLineAnnot(this.mPageIdx, pointF, pointF2, Color.red(i2), Color.green(i2), Color.blue(i2), Color.alpha(i2), (float) i, i3);
        onAnnotationEdited(1);
    }

    public void addNoteAnnot(PointF pointF, String str, String str2) {
        addAnnot(this.mDoc.insertNoteAnnot(this.mPageIdx, pointF, str, str2));
        onAnnotationEdited(0);
    }

    public void addFileAnnot(PointF pointF, String str) {
        addAnnot(this.mDoc.insertFileAnnot(this.mPageIdx, pointF, str));
        onAnnotationEdited(0);
    }

    public void addLinkAnnot(RectF rectF, int i, String str) {
        addAnnot(this.mDoc.insertLinkAnnot(this.mPageIdx, rectF, i, str));
        onAnnotationEdited(0);
    }

    /* renamed from: com.epapyrus.plugpdf.core.viewer.PageView$8  reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType[] r0 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType = r0
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.HIGHLIGHT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.UNDERLINE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.SQUIGGLY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.STRIKEOUT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.PageView.AnonymousClass8.<clinit>():void");
        }
    }

    public void addTextMarkupAnnot(RectF[] rectFArr, BaseAnnotTool.AnnotToolType annotToolType, int i) {
        int i2 = AnonymousClass8.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        addAnnot(this.mDoc.insertTextMarkupAnnot(this.mPageIdx, rectFArr, (float) Color.red(i), (float) Color.green(i), (float) Color.blue(i), i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? null : "StrikeOut" : "Squiggly" : "Underline" : "Highlight"));
        onAnnotationEdited(0);
    }

    public void addSquareAnnot(RectF rectF, int i, int i2, boolean z, int i3) {
        addAnnot(this.mDoc.insertSquareAnnot(this.mPageIdx, rectF, Color.red(i), Color.green(i), Color.blue(i), Color.red(i2), Color.green(i2), Color.blue(i2), z, Color.alpha(i), i3));
        onAnnotationEdited(0);
    }

    public void updateSquareAnnot(RectF rectF, int i, int i2, boolean z, int i3, int i4) {
        this.mDoc.updateSquareAnnot(this.mPageIdx, rectF, Color.red(i), Color.green(i), Color.blue(i), Color.red(i2), Color.green(i2), Color.blue(i2), z, Color.alpha(i), i3, i4);
        onAnnotationEdited(1);
    }

    public void addStampAnnot(RectF rectF, int i, Bitmap bitmap) {
        addAnnot(this.mDoc.insertStampAnnot(this.mPageIdx, rectF, i, bitmap));
        onAnnotationEdited(0);
    }

    public void updateStampAnnot(RectF rectF, int i, int i2) {
        this.mDoc.updateStampAnnot(this.mPageIdx, rectF, i, i2);
        onAnnotationEdited(1);
    }

    public void addFreeTextAnnot(PointF pointF, String str, String str2, double d, double d2, double d3, int i, int i2, int i3, RectF rectF) {
        addAnnot(this.mDoc.insertFreeText(this.mPageIdx, pointF, str, str2, d, d2, d3, i, i2, i3, rectF));
        onAnnotationEdited(0);
    }

    public void updateFreeTextAnnot(PointF pointF, String str, String str2, double d, double d2, double d3, int i, RectF rectF, int i2) {
        this.mDoc.updateFreeText(this.mPageIdx, pointF, str, str2, d, d2, d3, Color.red(i), Color.green(i), Color.blue(i), rectF, i2);
        onAnnotationEdited(1);
    }

    public void addCircleAnnot(RectF rectF, int i, int i2, boolean z, int i3) {
        addAnnot(this.mDoc.insertCircleAnnot(this.mPageIdx, rectF, Color.red(i), Color.green(i), Color.blue(i), Color.red(i2), Color.green(i2), Color.blue(i2), z, Color.alpha(i), i3));
        onAnnotationEdited(0);
    }

    public void updateCircleAnnot(RectF rectF, int i, int i2, boolean z, int i3, int i4) {
        this.mDoc.updateCircleAnnot(this.mPageIdx, rectF, Color.red(i), Color.green(i), Color.blue(i), Color.red(i2), Color.green(i2), Color.blue(i2), z, Color.alpha(i), i3, i4);
        onAnnotationEdited(1);
    }

    public void updateInkAnnot(int i, int i2, ArrayList<ArrayList<PointF>> arrayList, int i3) {
        if (arrayList.size() <= 0 || arrayList.get(0).size() >= 2) {
            PointF[][] pointFArr = new PointF[arrayList.size()][];
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                pointFArr[i4] = (PointF[]) arrayList.get(i4).toArray(new PointF[0]);
            }
            this.mDoc.updateInkAnnot(this.mPageIdx, pointFArr, Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i), (float) i2, i3);
            BaseAnnot annot = getAnnot(i3);
            if (annot != null) {
                AnnotInk annotInk = (AnnotInk) annot;
                annotInk.setARGB(annot.getOpacity(), Color.red(i), Color.green(i), Color.blue(i));
                annotInk.setOpacity(Color.alpha(i));
                annotInk.setLineWidth(i2);
                annotInk.setPointContainer(arrayList);
                annot.invalidate();
            }
            onAnnotationEdited(1);
        }
    }

    public void updateInkAnnotColor(int i, int i2, int i3, int i4) {
        this.mDoc.updateInkAnnotColor(getPageIdx(), i, i2, i3, i4);
        BaseAnnot annot = getAnnot(i);
        if (annot != null && (annot instanceof AnnotInk)) {
            ((AnnotInk) annot).setARGB(annot.getOpacity(), i2, i3, i4);
            annot.invalidate();
        }
        onAnnotationEdited(1);
    }

    public void updateInkAnnotOpacity(int i, float f) {
        this.mDoc.updateInkAnnotOpacity(getPageIdx(), i, f);
        BaseAnnot annot = getAnnot(i);
        if (annot != null) {
            ((AnnotInk) annot).setOpacity((int) f);
            annot.invalidate();
        }
        onAnnotationEdited(1);
    }

    public void updateInkAnnotLineWidth(int i, float f) {
        this.mDoc.updateInkAnnotLineWidth(getPageIdx(), i, f);
        BaseAnnot annot = getAnnot(i);
        if (annot != null && (annot instanceof AnnotInk)) {
            ((AnnotInk) annot).setLineWidth((int) f);
            annot.invalidate();
        }
        onAnnotationEdited(1);
    }

    public void updateNoteAnnot(int i, String str, String str2, PointF pointF) {
        this.mDoc.updateNoteAnnot(getPageIdx(), i, str, str2, pointF);
        onAnnotationEdited(1);
    }

    public void updateFileAnnot(int i, String str) {
        this.mDoc.updateFileAnnot(getPageIdx(), i, str);
        onAnnotationEdited(1);
    }

    private void onAnnotationEdited(int i) {
        PageViewListener pageViewListener = this.mListener;
        if (pageViewListener != null) {
            pageViewListener.onAnnotationEdited(this.mPageIdx, this.mAnnotList, i);
        }
    }

    public void insertImageWatermark(RectF rectF, Bitmap bitmap) {
        insertImageWatermark(rectF, bitmap, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d);
    }

    public void insertImageWatermark(RectF rectF, Bitmap bitmap, double d, double d2) {
        this.mDoc.insertImageWatermark(new int[]{this.mPageIdx}, rectF, bitmap, d, d2);
        cancelAdjustPatch();
        drawEntire();
    }

    public void insertTextWatermark(PointF pointF, String str, int i) {
        insertTextWatermark(pointF, str, "DroidSans", i, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d);
    }

    public void insertTextWatermark(PointF pointF, String str, String str2, int i, double d, double d2) {
        this.mDoc.insertTextWatermark(new int[]{this.mPageIdx}, pointF, str, str2, i, d, d2);
        cancelAdjustPatch();
        drawEntire();
    }

    public void insertImageToWidget(int i, Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        insertImageToWidget(i, bitmap.getWidth(), bitmap.getHeight(), byteArrayOutputStream.toByteArray());
    }

    public void insertImageToWidget(int i, int i2, int i3, byte[] bArr) {
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final byte[] bArr2 = bArr;
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                PageView.this.mDoc.insertImageToWidget(PageView.this.mPageIdx, i4, i5, i6, bArr2);
                return null;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                PageView.this.redrawAP();
            }
        }.execute(new Void[0]);
    }

    public PointF getOriginPageSize() {
        return this.mOriginPageSize;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsDetached = true;
    }

    public void setParentSize(int i, int i2) {
        this.mParentSize = new Point(i, i2);
    }
}

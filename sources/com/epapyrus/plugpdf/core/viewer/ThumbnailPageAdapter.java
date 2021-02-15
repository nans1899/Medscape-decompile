package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.util.LruCache;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.acroform.BaseField;
import java.util.List;

class ThumbnailPageAdapter extends BaseAdapter implements PageViewListener {
    private BitmapCache mBitmapCache;
    /* access modifiers changed from: private */
    public final SparseArray<PointF> mCachePageSizes = new SparseArray<>();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final PDFDocument mDoc;
    private PageViewListener mListener;
    private Point mParentSize;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public ThumbnailPageAdapter(Context context, PDFDocument pDFDocument) {
        this.mContext = context;
        this.mDoc = pDFDocument;
        this.mBitmapCache = new BitmapCache();
    }

    public void setListener(PageViewListener pageViewListener) {
        this.mListener = pageViewListener;
    }

    public int getCount() {
        return this.mDoc.getPageCount();
    }

    public void setParentSize(int i, int i2) {
        this.mParentSize = new Point(i, i2);
    }

    /* access modifiers changed from: protected */
    public PageView createPageView(Context context, PDFDocument pDFDocument, ViewGroup viewGroup) {
        return new ThumbnailPageView(context, pDFDocument, this.mParentSize);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PageView pageView;
        if (view == null) {
            pageView = createPageView(this.mContext, this.mDoc, viewGroup);
        } else {
            pageView = (PageView) view;
        }
        PointF pointF = this.mCachePageSizes.get(i);
        pageView.clean(i);
        pageView.setListener(this);
        if (pointF == null) {
            new PageLoaderTask(i, pageView).execute(new Void[0]);
        } else {
            Bitmap bitmap = this.mBitmapCache.bitmap(Integer.valueOf(i));
            if (bitmap == null || bitmap.isRecycled()) {
                pageView.setPage(i, pointF);
            } else if (!pageView.setBitmap(i, pointF, bitmap)) {
                pageView.setPage(i, pointF);
            }
        }
        return pageView;
    }

    class PageLoaderTask extends AsyncTask<Void, Void, PointF> {
        public int mPageIdx;
        public PageView mPageView;

        public PageLoaderTask(int i, PageView pageView) {
            this.mPageIdx = i;
            this.mPageView = pageView;
        }

        /* access modifiers changed from: protected */
        public PointF doInBackground(Void... voidArr) {
            return ThumbnailPageAdapter.this.mDoc.getPageSize(this.mPageIdx);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(PointF pointF) {
            super.onPostExecute(pointF);
            ThumbnailPageAdapter.this.mCachePageSizes.put(this.mPageIdx, pointF);
            if (this.mPageIdx == this.mPageView.getPageIdx()) {
                this.mPageView.setPage(this.mPageIdx, pointF);
            }
        }
    }

    class BitmapCache {
        private LruCache<Integer, Bitmap> mCache;

        BitmapCache() {
            this.mCache = new LruCache<Integer, Bitmap>(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8, ThumbnailPageAdapter.this) {
                /* access modifiers changed from: protected */
                public int sizeOf(Integer num, Bitmap bitmap) {
                    return bitmap.getByteCount() / 1024;
                }
            };
        }

        public void addBitmap(Integer num, Bitmap bitmap) {
            if (bitmap(num) == null) {
                this.mCache.put(num, bitmap);
            }
        }

        public Bitmap bitmap(Integer num) {
            return this.mCache.get(num);
        }
    }

    public void onAnnotationList(int i, List<BaseAnnot> list) {
        PageViewListener pageViewListener = this.mListener;
        if (pageViewListener != null) {
            pageViewListener.onAnnotationList(i, list);
        }
    }

    public void cachePageBitmap(int i, Bitmap bitmap) {
        this.mBitmapCache.addBitmap(Integer.valueOf(i), bitmap);
    }

    public void onFieldList(int i, List<BaseField> list) {
        PageViewListener pageViewListener = this.mListener;
        if (pageViewListener != null) {
            pageViewListener.onFieldList(i, list);
        }
    }

    public void onPageLoadFinish(int i) {
        PageViewListener pageViewListener = this.mListener;
        if (pageViewListener != null) {
            pageViewListener.onPageLoadFinish(i);
        }
    }

    public void onAnnotationEdited(int i, List<BaseAnnot> list, int i2) {
        PageViewListener pageViewListener = this.mListener;
        if (pageViewListener != null) {
            pageViewListener.onAnnotationEdited(i, list, i2);
        }
    }
}

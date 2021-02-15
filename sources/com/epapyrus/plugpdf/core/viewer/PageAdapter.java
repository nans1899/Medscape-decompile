package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldEventListener;

class PageAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public PageAdapterListener mAdapterListener;
    /* access modifiers changed from: private */
    public final SparseArray<PointF> mCachePageSizes = new SparseArray<>();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final PDFDocument mDoc;
    private FieldEventListener mFieldEventListener;
    private PageViewListener mListener;

    public interface PageAdapterListener {
        void onLoadedPage(PageView pageView);
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public PageAdapter(Context context, PDFDocument pDFDocument) {
        this.mContext = context;
        this.mDoc = pDFDocument;
    }

    public void setListener(PageViewListener pageViewListener) {
        this.mListener = pageViewListener;
    }

    public void setFieldEvenetListener(FieldEventListener fieldEventListener) {
        this.mFieldEventListener = fieldEventListener;
    }

    public void setAdapterListener(PageAdapterListener pageAdapterListener) {
        this.mAdapterListener = pageAdapterListener;
    }

    public int getCount() {
        return this.mDoc.getPageCount();
    }

    private PageView createPageView(Context context, PDFDocument pDFDocument, ViewGroup viewGroup) {
        return new PageView(context, pDFDocument, new Point(viewGroup.getWidth(), viewGroup.getHeight()));
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
        pageView.setListener(this.mListener);
        pageView.setFieldEvenetListener(this.mFieldEventListener);
        if (PropertyManager.usePageLoadThread()) {
            new PageLoaderTask(i, pageView).execute(new PointF[]{pointF});
        } else {
            if (pointF == null) {
                pointF = this.mDoc.getPageSize(i);
            }
            this.mCachePageSizes.put(i, pointF);
            if (i == pageView.getPageIdx()) {
                pageView.setPage(i, pointF);
            }
        }
        return pageView;
    }

    private class PageLoaderTask extends AsyncTask<PointF, Void, PointF> {
        private int mPageIdx;
        private PageView mPageView;

        public PageLoaderTask(int i, PageView pageView) {
            this.mPageIdx = i;
            this.mPageView = pageView;
        }

        /* access modifiers changed from: protected */
        public PointF doInBackground(PointF... pointFArr) {
            if (this.mPageView == null) {
                return null;
            }
            if (pointFArr[0] != null) {
                return pointFArr[0];
            }
            return PageAdapter.this.mDoc.getPageSize(this.mPageIdx);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(PointF pointF) {
            super.onPostExecute(pointF);
            if (pointF != null) {
                PageAdapter.this.mCachePageSizes.put(this.mPageIdx, pointF);
                if (this.mPageIdx == this.mPageView.getPageIdx()) {
                    this.mPageView.setPage(this.mPageIdx, pointF);
                }
                if (PageAdapter.this.mAdapterListener != null) {
                    PageAdapter.this.mAdapterListener.onLoadedPage(this.mPageView);
                }
            }
        }
    }
}

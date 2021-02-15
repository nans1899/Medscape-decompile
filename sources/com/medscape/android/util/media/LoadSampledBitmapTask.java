package com.medscape.android.util.media;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public class LoadSampledBitmapTask extends AsyncTask<Object, Void, SampledBitmap> {
    private Object mData = 0;
    private int[] mImageDimensions;
    private SampledBitmapLoadListener mListener;
    private final WeakReference<ImageView> mReferenceView;

    public LoadSampledBitmapTask(ImageView imageView, int[] iArr) {
        this.mReferenceView = new WeakReference<>(imageView);
        this.mImageDimensions = iArr;
    }

    public void setSampledBitmapLoadListener(SampledBitmapLoadListener sampledBitmapLoadListener) {
        this.mListener = sampledBitmapLoadListener;
    }

    public void setImageDimensions(int[] iArr) {
        this.mImageDimensions = iArr;
    }

    public int getImageWidth() {
        return this.mImageDimensions[0];
    }

    public int getImageHeight() {
        return this.mImageDimensions[1];
    }

    public Object getData() {
        return this.mData;
    }

    /* access modifiers changed from: protected */
    public SampledBitmap doInBackground(Object... objArr) {
        WeakReference<ImageView> weakReference;
        Object obj = objArr[0];
        this.mData = obj;
        if ((!(obj instanceof Integer) && !(obj instanceof Uri)) || (weakReference = this.mReferenceView) == null || weakReference.get() == null) {
            return null;
        }
        SampledBitmapLoadListener sampledBitmapLoadListener = this.mListener;
        if (sampledBitmapLoadListener != null) {
            sampledBitmapLoadListener.onLoadStarted();
        }
        Object obj2 = this.mData;
        if (objArr.length > 1) {
            obj2 = objArr[1];
        }
        Object obj3 = this.mData;
        String obj4 = obj2.toString();
        boolean z = true ^ (this.mData instanceof Integer);
        return BitmapLoader.getSampledBitmapFromAsset((View) this.mReferenceView.get(), obj3, obj4, z ? 1 : 0, this.mImageDimensions);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.widget.ImageView} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPostExecute(com.medscape.android.util.media.SampledBitmap r3) {
        /*
            r2 = this;
            boolean r0 = r2.isCancelled()
            r1 = 0
            if (r0 == 0) goto L_0x000b
            r3.setBitmap(r1)
            r3 = r1
        L_0x000b:
            java.lang.ref.WeakReference<android.widget.ImageView> r0 = r2.mReferenceView
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = r0.get()
            r1 = r0
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            if (r1 == 0) goto L_0x001f
            android.graphics.Bitmap r0 = r3.getBitmap()
            r1.setImageBitmap(r0)
        L_0x001f:
            com.medscape.android.util.media.SampledBitmapLoadListener r0 = r2.mListener
            if (r0 == 0) goto L_0x0026
            r0.onLoadComplete(r1, r3)
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.media.LoadSampledBitmapTask.onPostExecute(com.medscape.android.util.media.SampledBitmap):void");
    }
}

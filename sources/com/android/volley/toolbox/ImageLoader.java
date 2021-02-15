package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class ImageLoader {
    private final Map<Integer, String> cacheKeysForImageset = Collections.synchronizedMap(new HashMap());
    private int mBatchResponseDelayMs = 100;
    /* access modifiers changed from: private */
    public final HashMap<String, BatchedImageRequest> mBatchedResponses = new HashMap<>();
    /* access modifiers changed from: private */
    public final ImageCache mCache;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final HashMap<String, BatchedImageRequest> mInFlightRequests = new HashMap<>();
    private final RequestQueue mRequestQueue;
    /* access modifiers changed from: private */
    public Runnable mRunnable;

    public interface ImageCache {
        void cleanKeys();

        Bitmap getBitmap(String str);

        Bitmap getBitmapFromMemCache(String str);

        File getDiskBitmap(String str);

        void putBitmap(String str, Bitmap bitmap);

        void putBitmapToMemcache(String str, Bitmap bitmap);
    }

    public interface ImageListener extends Response.ErrorListener {
        void onResponse(ImageContainer imageContainer, boolean z);
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public ImageLoader(RequestQueue requestQueue, ImageCache imageCache) {
        this.mRequestQueue = requestQueue;
        this.mCache = imageCache;
    }

    public static ImageListener getImageListener(final ImageView imageView, final int i, final int i2) {
        return new ImageListener() {
            public void onErrorResponse(VolleyError volleyError) {
                int i = i2;
                if (i != 0) {
                    imageView.setImageResource(i);
                }
            }

            public void onResponse(ImageContainer imageContainer, boolean z) {
                if (imageContainer.getBitmap() != null) {
                    imageView.setImageBitmap(imageContainer.getBitmap());
                    return;
                }
                int i = i;
                if (i != 0) {
                    imageView.setImageResource(i);
                }
            }
        };
    }

    public boolean isCached(String str, int i, int i2) {
        return isCached(str, i, i2, ImageView.ScaleType.CENTER_INSIDE);
    }

    public boolean isCached(String str, int i, int i2, ImageView.ScaleType scaleType) {
        throwIfNotOnMainThread();
        return this.mCache.getBitmap(getCacheKey(str, i, i2, scaleType)) != null;
    }

    public ImageContainer get(String str, ImageListener imageListener) {
        return get(str, imageListener, 0, 0);
    }

    public ImageContainer get(String str, ImageListener imageListener, int i, int i2) {
        return get(str, imageListener, i, i2, ImageView.ScaleType.CENTER_INSIDE);
    }

    public void getAsync(String str, ImageListener imageListener, int i, int i2, ImageView.ScaleType scaleType) {
        ImageListener imageListener2 = imageListener;
        throwIfNotOnMainThread();
        String cacheKey = getCacheKey(str, i, i2, scaleType);
        Bitmap bitmapFromMemCache = this.mCache.getBitmapFromMemCache(cacheKey);
        if (bitmapFromMemCache != null) {
            VolleyLog.v("TAG", "Found in cache");
            imageListener2.onResponse(new ImageContainer(bitmapFromMemCache, str, (String) null, (ImageListener) null), true);
            return;
        }
        ImageContainer imageContainer = new ImageContainer((Bitmap) null, str, cacheKey, imageListener);
        imageListener2.onResponse(imageContainer, true);
        BatchedImageRequest batchedImageRequest = this.mInFlightRequests.get(cacheKey);
        if (batchedImageRequest != null) {
            batchedImageRequest.addContainer(imageContainer);
        }
        Request<Bitmap> makeImageRequest = makeImageRequest(str, i, i2, scaleType, cacheKey);
        makeImageRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        this.mRequestQueue.add(makeImageRequest);
        this.mInFlightRequests.put(cacheKey, new BatchedImageRequest(makeImageRequest, imageContainer));
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap decodeSampleBitmap(File file, int i, int i2) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), (Rect) null, options);
            options.inSampleSize = calculateInSampleSize(options, i, i2);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(new FileInputStream(file), (Rect) null, options);
        } catch (Exception e) {
            Log.e("Test", e.getMessage());
            return null;
        }
    }

    public void getImageAsync(String str, ImageListener imageListener, int i, int i2, ImageView.ScaleType scaleType, RecycleImageView recycleImageView) {
        throwIfNotOnMainThread();
        String str2 = this.cacheKeysForImageset.get(Integer.valueOf(recycleImageView.getId()));
        String cacheKey = getCacheKey(str, i, i2, scaleType);
        if (str2 != null && !cacheKey.equalsIgnoreCase(str2)) {
            Log.v("Test", "Canceling previous request");
            cancelRequest(str2);
        }
        this.cacheKeysForImageset.put(Integer.valueOf(recycleImageView.getId()), cacheKey);
        getAsync(str, imageListener, i, i2, scaleType);
    }

    public void cancelRequest(String str) {
        BatchedImageRequest batchedImageRequest = this.mInFlightRequests.get(str);
        if (batchedImageRequest != null && batchedImageRequest.cancel()) {
            this.mInFlightRequests.remove(str);
        }
    }

    public ImageContainer get(String str, ImageListener imageListener, int i, int i2, ImageView.ScaleType scaleType) {
        ImageListener imageListener2 = imageListener;
        throwIfNotOnMainThread();
        String cacheKey = getCacheKey(str, i, i2, scaleType);
        Bitmap bitmap = this.mCache.getBitmap(cacheKey);
        if (bitmap != null) {
            VolleyLog.v("TAG", "Found in cache");
            ImageContainer imageContainer = new ImageContainer(bitmap, str, (String) null, (ImageListener) null);
            imageListener2.onResponse(imageContainer, true);
            return imageContainer;
        }
        ImageContainer imageContainer2 = new ImageContainer((Bitmap) null, str, cacheKey, imageListener);
        imageListener2.onResponse(imageContainer2, true);
        BatchedImageRequest batchedImageRequest = this.mInFlightRequests.get(cacheKey);
        if (batchedImageRequest != null) {
            batchedImageRequest.addContainer(imageContainer2);
            return imageContainer2;
        }
        Request<Bitmap> makeImageRequest = makeImageRequest(str, i, i2, scaleType, cacheKey);
        this.mRequestQueue.add(makeImageRequest);
        this.mInFlightRequests.put(cacheKey, new BatchedImageRequest(makeImageRequest, imageContainer2));
        return imageContainer2;
    }

    /* access modifiers changed from: protected */
    public Request<Bitmap> makeImageRequest(String str, int i, int i2, ImageView.ScaleType scaleType, final String str2) {
        ImageRequest imageRequest = new ImageRequest(str, new Response.Listener<Bitmap>() {
            public void onResponse(Bitmap bitmap) {
                ImageLoader.this.onGetImageSuccess(str2, bitmap);
            }
        }, i, i2, scaleType, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError.getCause() instanceof OutOfMemoryError) {
                    ImageLoader.this.mCache.cleanKeys();
                }
                ImageLoader.this.onGetImageError(str2, volleyError);
            }
        });
        imageRequest.setTag("ImageLoader");
        return imageRequest;
    }

    public void setBatchedResponseDelay(int i) {
        this.mBatchResponseDelayMs = i;
    }

    /* access modifiers changed from: protected */
    public void onGetImageSuccess(final String str, final Bitmap bitmap) {
        new Runnable() {
            public void run() {
                ImageLoader.this.mCache.putBitmap(str, bitmap);
            }
        }.run();
        BatchedImageRequest remove = this.mInFlightRequests.remove(str);
        if (remove != null) {
            Bitmap unused = remove.mResponseBitmap = bitmap;
            batchResponse(str, remove);
        }
    }

    /* access modifiers changed from: protected */
    public void onGetImageError(String str, VolleyError volleyError) {
        BatchedImageRequest remove = this.mInFlightRequests.remove(str);
        if (remove != null) {
            remove.setError(volleyError);
            batchResponse(str, remove);
        }
    }

    public class ImageContainer {
        /* access modifiers changed from: private */
        public Bitmap mBitmap;
        private final String mCacheKey;
        /* access modifiers changed from: private */
        public final ImageListener mListener;
        private final String mRequestUrl;

        public ImageContainer(Bitmap bitmap, String str, String str2, ImageListener imageListener) {
            this.mBitmap = bitmap;
            this.mRequestUrl = str;
            this.mCacheKey = str2;
            this.mListener = imageListener;
        }

        public void cancelRequest() {
            if (this.mListener != null) {
                BatchedImageRequest batchedImageRequest = (BatchedImageRequest) ImageLoader.this.mInFlightRequests.get(this.mCacheKey);
                if (batchedImageRequest == null) {
                    BatchedImageRequest batchedImageRequest2 = (BatchedImageRequest) ImageLoader.this.mBatchedResponses.get(this.mCacheKey);
                    if (batchedImageRequest2 != null) {
                        batchedImageRequest2.removeContainerAndCancelIfNecessary(this);
                        if (batchedImageRequest2.mContainers.size() == 0) {
                            ImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
                        }
                    }
                } else if (batchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                    ImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
                }
            }
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public String getRequestUrl() {
            return this.mRequestUrl;
        }
    }

    private class BatchedImageRequest {
        /* access modifiers changed from: private */
        public final LinkedList<ImageContainer> mContainers;
        private VolleyError mError;
        private final Request<?> mRequest;
        /* access modifiers changed from: private */
        public Bitmap mResponseBitmap;

        public BatchedImageRequest(Request<?> request, ImageContainer imageContainer) {
            LinkedList<ImageContainer> linkedList = new LinkedList<>();
            this.mContainers = linkedList;
            this.mRequest = request;
            linkedList.add(imageContainer);
        }

        public void setError(VolleyError volleyError) {
            this.mError = volleyError;
        }

        public VolleyError getError() {
            return this.mError;
        }

        public void addContainer(ImageContainer imageContainer) {
            this.mContainers.add(imageContainer);
        }

        public boolean removeContainerAndCancelIfNecessary(ImageContainer imageContainer) {
            this.mContainers.remove(imageContainer);
            if (this.mContainers.size() != 0) {
                return false;
            }
            this.mRequest.cancel();
            return true;
        }

        public boolean cancel() {
            this.mRequest.cancel();
            return true;
        }
    }

    private void batchResponse(String str, BatchedImageRequest batchedImageRequest) {
        this.mBatchedResponses.put(str, batchedImageRequest);
        if (this.mRunnable == null) {
            AnonymousClass5 r3 = new Runnable() {
                public void run() {
                    for (BatchedImageRequest batchedImageRequest : ImageLoader.this.mBatchedResponses.values()) {
                        Iterator it = batchedImageRequest.mContainers.iterator();
                        while (it.hasNext()) {
                            ImageContainer imageContainer = (ImageContainer) it.next();
                            if (imageContainer.mListener != null) {
                                if (batchedImageRequest.getError() == null) {
                                    Bitmap unused = imageContainer.mBitmap = batchedImageRequest.mResponseBitmap;
                                    imageContainer.mListener.onResponse(imageContainer, false);
                                } else {
                                    imageContainer.mListener.onErrorResponse(batchedImageRequest.getError());
                                }
                            }
                        }
                    }
                    ImageLoader.this.mBatchedResponses.clear();
                    Runnable unused2 = ImageLoader.this.mRunnable = null;
                }
            };
            this.mRunnable = r3;
            this.mHandler.postDelayed(r3, (long) this.mBatchResponseDelayMs);
        }
    }

    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    private static String getCacheKey(String str, int i, int i2, ImageView.ScaleType scaleType) {
        StringBuilder sb = new StringBuilder(str.length() + 12);
        sb.append("#W");
        sb.append(i);
        sb.append("#H");
        sb.append(i2);
        sb.append("#S");
        sb.append(scaleType.ordinal());
        sb.append(str);
        return sb.toString();
    }
}

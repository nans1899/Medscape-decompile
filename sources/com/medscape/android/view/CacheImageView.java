package com.medscape.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.cache.ImageCacheManager;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.util.BlurBuilder;
import com.medscape.android.util.media.BitmapLoadListener;
import com.medscape.android.util.media.BitmapLoader;
import com.medscape.android.util.media.LoadSampledBitmapTask;
import com.medscape.android.util.media.SampledBitmap;
import com.medscape.android.util.media.SampledBitmapLoadListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheImageView extends ImageView implements ImageCacheManager.CacheCallback {
    private static final String TAG = "CacheImageView";
    private static final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(6);
    BitmapLoadListener mBitmapListener;
    boolean mBitmapLoaded;
    LoadSampledBitmapTask mCachedBitmapLoaderTask;
    int mDesiredBitmapHeight;
    int mDesiredBitmapWidth;
    boolean mDoBlur;
    SampledBitmapLoadListener mInternalBitmapListener;
    private int mPlaceholderResourceId;
    int mRecycleCount;
    boolean mShouldLoadAfterMeasure;
    boolean mShouldRemoveDrawableOnDetach;
    boolean mTrackBitmap;
    /* access modifiers changed from: private */
    public String mURL;

    public void onFileFailed(String str, String str2, Exception exc) {
    }

    public CacheImageView(Context context) {
        super(context);
        this.mBitmapLoaded = false;
        this.mTrackBitmap = true;
        this.mRecycleCount = 0;
        this.mDoBlur = false;
        this.mInternalBitmapListener = new SampledBitmapLoadListener() {
            public void onLoadStarted() {
                if (CacheImageView.this.mBitmapListener != null) {
                    CacheImageView.this.mBitmapListener.onLoadStarted();
                }
            }

            public void onLoadComplete(View view, SampledBitmap sampledBitmap) {
                if (!CacheImageView.this.mCachedBitmapLoaderTask.isCancelled() && !CacheImageView.this.mShouldLoadAfterMeasure && CacheImageView.this.mURL != null) {
                    if (sampledBitmap.getBitmap() == null || !sampledBitmap.getBitmap().isRecycled()) {
                        CacheImageView.this.showAppropriateBitmap(sampledBitmap.getBitmap());
                        if (CacheImageView.this.mTrackBitmap) {
                            BitmapLoader.trackBitmap(view, CacheImageView.this.mURL, sampledBitmap, true);
                        } else if (!(sampledBitmap == null || sampledBitmap.getBitmap() == null)) {
                            MedscapeApplication.get().addSampledBitmapToMemory(CacheImageView.this.mURL, sampledBitmap);
                        }
                        if (CacheImageView.this.mBitmapListener != null) {
                            CacheImageView.this.mBitmapListener.onLoadComplete(view, sampledBitmap.getBitmap());
                            return;
                        }
                        return;
                    }
                    CacheImageView.this.findAndLoadBitmap();
                }
            }
        };
    }

    public CacheImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CacheImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBitmapLoaded = false;
        this.mTrackBitmap = true;
        this.mRecycleCount = 0;
        this.mDoBlur = false;
        this.mInternalBitmapListener = new SampledBitmapLoadListener() {
            public void onLoadStarted() {
                if (CacheImageView.this.mBitmapListener != null) {
                    CacheImageView.this.mBitmapListener.onLoadStarted();
                }
            }

            public void onLoadComplete(View view, SampledBitmap sampledBitmap) {
                if (!CacheImageView.this.mCachedBitmapLoaderTask.isCancelled() && !CacheImageView.this.mShouldLoadAfterMeasure && CacheImageView.this.mURL != null) {
                    if (sampledBitmap.getBitmap() == null || !sampledBitmap.getBitmap().isRecycled()) {
                        CacheImageView.this.showAppropriateBitmap(sampledBitmap.getBitmap());
                        if (CacheImageView.this.mTrackBitmap) {
                            BitmapLoader.trackBitmap(view, CacheImageView.this.mURL, sampledBitmap, true);
                        } else if (!(sampledBitmap == null || sampledBitmap.getBitmap() == null)) {
                            MedscapeApplication.get().addSampledBitmapToMemory(CacheImageView.this.mURL, sampledBitmap);
                        }
                        if (CacheImageView.this.mBitmapListener != null) {
                            CacheImageView.this.mBitmapListener.onLoadComplete(view, sampledBitmap.getBitmap());
                            return;
                        }
                        return;
                    }
                    CacheImageView.this.findAndLoadBitmap();
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Medscape);
        this.mShouldRemoveDrawableOnDetach = obtainStyledAttributes.getBoolean(12, true);
        this.mTrackBitmap = obtainStyledAttributes.getBoolean(15, true);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        reset();
    }

    public void reset() {
        if (this.mShouldRemoveDrawableOnDetach) {
            if (getDrawable() != null) {
                getDrawable().setCallback((Drawable.Callback) null);
            }
            if (this.mTrackBitmap && this.mURL != null && getDrawable() != null && this.mBitmapLoaded) {
                BitmapLoader.cleanupBitmap(this.mURL, this);
            }
            setImageBitmap((Bitmap) null);
            setImageDrawable((Drawable) null);
            this.mBitmapLoaded = false;
            this.mShouldLoadAfterMeasure = true;
            LoadSampledBitmapTask loadSampledBitmapTask = this.mCachedBitmapLoaderTask;
            if (loadSampledBitmapTask != null) {
                loadSampledBitmapTask.cancel(true);
            }
        }
    }

    private void createBitmapLoaderTask() {
        int[] iArr;
        LoadSampledBitmapTask loadSampledBitmapTask = this.mCachedBitmapLoaderTask;
        if (loadSampledBitmapTask != null) {
            loadSampledBitmapTask.cancel(true);
        }
        if (this.mDesiredBitmapWidth > 0 || this.mDesiredBitmapHeight > 0) {
            iArr = new int[]{this.mDesiredBitmapWidth, this.mDesiredBitmapHeight};
        } else {
            iArr = new int[2];
            iArr[0] = getMeasuredWidth() > 0 ? getMeasuredWidth() : BitmapLoader.MAX_IMAGE_WIDTH;
            iArr[1] = getMeasuredHeight() > 0 ? getMeasuredHeight() : BitmapLoader.MAX_IMAGE_HEIGHT;
        }
        LoadSampledBitmapTask loadSampledBitmapTask2 = new LoadSampledBitmapTask(this, iArr);
        this.mCachedBitmapLoaderTask = loadSampledBitmapTask2;
        loadSampledBitmapTask2.setSampledBitmapLoadListener(this.mInternalBitmapListener);
    }

    public void configure(String str, int i, boolean z) {
        this.mDoBlur = z;
        configure(str, i, -1, -1);
    }

    public void configure(String str, int i) {
        configure(str, i, -1, -1);
    }

    public void configure(String str, int i, int i2, int i3) {
        boolean z = false;
        if (isBadURL(str)) {
            this.mURL = str;
            this.mBitmapLoaded = false;
            if (i > 0) {
                setImageBitmap(BitmapLoader.getBitmapFromAsset(this, Integer.valueOf(i), 0));
            } else {
                setImageResource(0);
            }
        } else if (!str.equals(this.mURL) || !this.mBitmapLoaded) {
            if (this.mTrackBitmap && this.mURL != null && getDrawable() != null && this.mBitmapLoaded) {
                BitmapLoader.cleanupBitmap(this.mURL, this);
            }
            this.mURL = str;
            this.mBitmapLoaded = false;
            this.mPlaceholderResourceId = i;
            this.mDesiredBitmapWidth = Math.max(i2, 0);
            this.mDesiredBitmapHeight = Math.max(i3, 0);
            if (i3 <= 0 && i2 <= 0 && getMeasuredHeight() == 0 && getMeasuredWidth() == 0) {
                z = true;
            }
            this.mShouldLoadAfterMeasure = z;
            if (!z) {
                findAndLoadBitmap();
            }
        }
    }

    public void cancel() {
        LoadSampledBitmapTask loadSampledBitmapTask = this.mCachedBitmapLoaderTask;
        if (loadSampledBitmapTask != null) {
            loadSampledBitmapTask.cancel(true);
        }
    }

    /* access modifiers changed from: private */
    public void findAndLoadBitmap() {
        File cacheFile = MedscapeApplication.get().getCacheManager().getCacheFile(this.mURL, this);
        if (cacheFile != null && cacheFile.exists()) {
            createBitmapLoaderTask();
            AsyncTaskHelper.execute(threadPoolExecutor, this.mCachedBitmapLoaderTask, Uri.parse(cacheFile.getAbsolutePath()), this.mURL);
        }
        int i = this.mPlaceholderResourceId;
        if (i != 0) {
            Integer valueOf = Integer.valueOf(i);
            int[] iArr = new int[2];
            iArr[0] = getMeasuredWidth() > 0 ? getMeasuredWidth() : BitmapLoader.MAX_IMAGE_WIDTH;
            iArr[1] = getMeasuredHeight() > 0 ? getMeasuredHeight() : BitmapLoader.MAX_IMAGE_HEIGHT;
            SampledBitmap sampledBitmapFromAsset = BitmapLoader.getSampledBitmapFromAsset(this, valueOf, 0, iArr);
            setImageBitmap(sampledBitmapFromAsset.getBitmap());
            MedscapeApplication medscapeApplication = MedscapeApplication.get();
            medscapeApplication.addSampledBitmapToMemory(this.mPlaceholderResourceId + "", sampledBitmapFromAsset);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mShouldLoadAfterMeasure && !isBadURL(this.mURL) && getMeasuredWidth() > 0 && getMeasuredHeight() > 0) {
            this.mShouldLoadAfterMeasure = false;
            findAndLoadBitmap();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void showAppropriateBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            if (getDrawable() instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
            }
            if (this.mDoBlur) {
                setImageBitmap(BlurBuilder.blur(getContext(), bitmap));
            } else {
                setImageBitmap(bitmap);
            }
            this.mBitmapLoaded = true;
            return;
        }
        int i = this.mPlaceholderResourceId;
        if (i != 0) {
            setImageResource(i);
        }
    }

    public boolean hasBitmapLoaded() {
        return this.mBitmapLoaded;
    }

    public void clearUrl() {
        this.mURL = null;
    }

    public String getUrl() {
        return this.mURL;
    }

    private boolean isBadURL(String str) {
        return str == null || str.contains("null") || str.length() == 0;
    }

    public void onFileAvailable(String str, File file) {
        showAppropriateBitmap(BitmapLoader.getBitmapFromAsset(this, Uri.parse(file.getAbsolutePath()), 1));
    }

    public void setBitmapLoadListener(BitmapLoadListener bitmapLoadListener) {
        this.mBitmapListener = bitmapLoadListener;
    }
}

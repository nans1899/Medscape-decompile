package com.medscape.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.medscape.android.util.DiskCache;
import java.util.Collections;
import java.util.Map;

public class DownloadCarouselThumbnailTask extends AsyncTask<ImageView, Void, Bitmap> {
    private final String TAG = "DownloadCarouselThumbnailTask";
    private Map<String, Bitmap> carouselThumbnailCache;
    private Context context;
    ImageView imageView = null;
    private final DiskCache mDiskCache;
    private String url;

    public DownloadCarouselThumbnailTask(Context context2, Map<String, Bitmap> map) {
        this.context = context2;
        this.carouselThumbnailCache = map;
        if (map != null) {
            this.carouselThumbnailCache = Collections.synchronizedMap(map);
        }
        this.mDiskCache = new DiskCache(context2, Constants.PREF_CAROUSEL_THUMBNAIL_PREFIX);
    }

    /* access modifiers changed from: protected */
    public Bitmap doInBackground(ImageView... imageViewArr) {
        String str;
        Bitmap bitmap;
        ImageView imageView2 = imageViewArr[0];
        this.imageView = imageView2;
        String str2 = (String) imageView2.getTag();
        this.url = str2;
        String trim = str2.trim();
        this.url = trim;
        if (trim.lastIndexOf("/") != -1) {
            String str3 = this.url;
            str = str3.substring(str3.lastIndexOf("/") + 1);
        } else {
            str = this.url;
        }
        String str4 = this.url.hashCode() + "_" + str;
        if (!this.mDiskCache.containsKey(str4) || (bitmap = this.mDiskCache.get(str4)) == null) {
            return this.mDiskCache.download(this.url, str4);
        }
        return bitmap;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            Map<String, Bitmap> map = this.carouselThumbnailCache;
            if (map != null) {
                map.put(this.url, Bitmap.createBitmap(bitmap));
            }
            this.imageView.setImageBitmap(bitmap);
        }
    }
}

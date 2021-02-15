package com.appboy.lrucache;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;
import bo.app.ba;
import com.appboy.IAppboyImageLoader;
import com.appboy.R;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import java.io.File;

public class AppboyLruImageLoader implements IAppboyImageLoader {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(AppboyLruImageLoader.class);
    private LruCache<String, Bitmap> b = new LruCache<String, Bitmap>(AppboyImageUtils.getImageLoaderCacheSize()) {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    };
    /* access modifiers changed from: private */
    public ba c;
    /* access modifiers changed from: private */
    public final Object d = new Object();
    /* access modifiers changed from: private */
    public boolean e = true;
    private boolean f = false;

    public AppboyLruImageLoader(Context context) {
        File a2 = a(context, "appboy.imageloader.lru.cache");
        new a().execute(new File[]{a2});
    }

    public void renderUrlIntoView(Context context, String str, ImageView imageView, AppboyViewBounds appboyViewBounds) {
        new b(context, imageView, appboyViewBounds, str).execute(new Void[0]);
    }

    public Bitmap getBitmapFromUrl(Context context, String str, AppboyViewBounds appboyViewBounds) {
        Bitmap a2 = a(str);
        if (a2 != null) {
            return a2;
        }
        if (this.f) {
            AppboyLogger.d(a, "Cache is currently in offline mode. Not downloading bitmap.");
            return null;
        }
        Bitmap a3 = a(context, Uri.parse(str), appboyViewBounds);
        if (a3 != null) {
            a(str, a3);
        }
        return a3;
    }

    public void setOffline(boolean z) {
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("Appboy image loader outbound network requests are now ");
        sb.append(z ? "disabled" : "enabled");
        AppboyLogger.i(str, sb.toString());
        this.f = z;
    }

    public static void deleteStoredData(Context context) {
        File file = new File(context.getCacheDir(), "appboy.imageloader.lru.cache");
        String str = a;
        AppboyLogger.v(str, "Deleting lru image cache directory at: " + file.getAbsolutePath());
        AppboyFileUtils.deleteFileOrDirectory(file);
    }

    class b extends AsyncTask<Void, Void, Bitmap> {
        private final ImageView b;
        private final Context c;
        private final AppboyViewBounds d;
        private final String e;

        private b(Context context, ImageView imageView, AppboyViewBounds appboyViewBounds, String str) {
            this.b = imageView;
            this.c = context;
            this.d = appboyViewBounds;
            this.e = str;
            imageView.setTag(R.string.com_appboy_image_lru_cache_image_url_key, str);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(Void... voidArr) {
            return AppboyLruImageLoader.this.getBitmapFromUrl(this.c, this.e, this.d);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Bitmap bitmap) {
            ImageView imageView = this.b;
            if (imageView != null && ((String) imageView.getTag(R.string.com_appboy_image_lru_cache_image_url_key)).equals(this.e)) {
                this.b.setImageBitmap(bitmap);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Bitmap a(Context context, Uri uri, AppboyViewBounds appboyViewBounds) {
        return AppboyImageUtils.getBitmap(context, uri, appboyViewBounds);
    }

    /* access modifiers changed from: package-private */
    public Bitmap a(String str) {
        Bitmap bitmap = this.b.get(str);
        if (bitmap != null) {
            String str2 = a;
            AppboyLogger.v(str2, "Got bitmap from mem cache for key " + str);
            return bitmap;
        }
        Bitmap c2 = c(str);
        if (c2 != null) {
            String str3 = a;
            AppboyLogger.v(str3, "Got bitmap from disk cache for key " + str);
            b(str, c2);
            return c2;
        }
        String str4 = a;
        AppboyLogger.d(str4, "No cache hit for bitmap: " + str);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Bitmap b(String str) {
        return this.b.get(str);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap c(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.d
            monitor-enter(r0)
            boolean r1 = r3.e     // Catch:{ all -> 0x0020 }
            r2 = 0
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r2
        L_0x000a:
            bo.app.ba r1 = r3.c     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x001e
            bo.app.ba r1 = r3.c     // Catch:{ all -> 0x0020 }
            boolean r1 = r1.b(r4)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x001e
            bo.app.ba r1 = r3.c     // Catch:{ all -> 0x0020 }
            android.graphics.Bitmap r4 = r1.a(r4)     // Catch:{ all -> 0x0020 }
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r4
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r2
        L_0x0020:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.lrucache.AppboyLruImageLoader.c(java.lang.String):android.graphics.Bitmap");
    }

    private void b(String str, Bitmap bitmap) {
        this.b.put(str, bitmap);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Bitmap bitmap) {
        if (b(str) == null) {
            String str2 = a;
            AppboyLogger.d(str2, "Adding bitmap to mem cache for key " + str);
            this.b.put(str, bitmap);
        }
        synchronized (this.d) {
            if (this.c != null && !this.c.b(str)) {
                String str3 = a;
                AppboyLogger.d(str3, "Adding bitmap to disk cache for key " + str);
                this.c.a(str, bitmap);
            }
        }
    }

    class a extends AsyncTask<File, Void, Void> {
        private a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(File... fileArr) {
            synchronized (AppboyLruImageLoader.this.d) {
                File file = fileArr[0];
                try {
                    AppboyLogger.d(AppboyLruImageLoader.a, "Initializing disk cache");
                    ba unused = AppboyLruImageLoader.this.c = new ba(file, 1, 1, 52428800);
                } catch (Exception e) {
                    AppboyLogger.e(AppboyLruImageLoader.a, "Caught exception creating new disk cache. Unable to create new disk cache", e);
                }
                boolean unused2 = AppboyLruImageLoader.this.e = false;
                AppboyLruImageLoader.this.d.notifyAll();
            }
            return null;
        }
    }

    static File a(Context context, String str) {
        return new File(context.getCacheDir().getPath() + File.separator + str);
    }
}

package com.medscape.android.cache;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import com.medscape.android.util.DiskCache;
import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageCacheManager {
    private static final int MAX_CACHE_WORKERS = 5;
    private static final String TAG = "CacheManager";
    private final int EXPIRATION_IN_DAYS = 15;
    /* access modifiers changed from: private */
    public Application mApp;
    /* access modifiers changed from: private */
    public String mCacheDir;
    /* access modifiers changed from: private */
    public Map<String, List<CacheCallback>> mCallbackMap = new Hashtable();
    private ExecutorService mExecutor;
    /* access modifiers changed from: private */
    public Handler mMainHandler;
    /* access modifiers changed from: private */
    public Map<String, CacheWorker> mWorkerMap = new Hashtable();

    public interface CacheCallback {
        void onFileAvailable(String str, File file);

        void onFileFailed(String str, String str2, Exception exc);
    }

    public ImageCacheManager(Application application) {
        this.mApp = application;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        try {
            this.mCacheDir = application.getFileStreamPath(DiskCache.DEFAULT_PREFIX).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public File getCacheFile(String str, CacheCallback cacheCallback) {
        File file = new File(this.mCacheDir);
        if (file.exists() || file.mkdirs()) {
            String encodeToString = Base64.encodeToString(str.getBytes(), 0);
            File file2 = new File(file.getAbsolutePath() + File.separator + encodeToString);
            if (file2.exists() || cacheCallback == null) {
                return file2;
            }
            synchronized (this) {
                List list = this.mCallbackMap.get(str);
                if (list == null) {
                    list = new LinkedList();
                    this.mCallbackMap.put(str, list);
                }
                list.add(cacheCallback);
                if (this.mWorkerMap.get(str) == null) {
                    CacheWorker cacheWorker = new CacheWorker(str, file2);
                    this.mWorkerMap.put(str, cacheWorker);
                    this.mExecutor.execute(cacheWorker);
                }
            }
            return null;
        }
        throw new RuntimeException("Failed to create cache directory");
    }

    public String getFilePathForUrl(String str) {
        return this.mCacheDir + File.separator + Base64.encodeToString(str.getBytes(), 0);
    }

    public void startup() {
        this.mExecutor = Executors.newFixedThreadPool(5);
    }

    public void shutdown() {
        try {
            if (this.mExecutor != null) {
                this.mExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to stop cache worker pool", e);
        }
        sanitizeCache();
    }

    private void sanitizeCache() {
        File[] listFiles;
        File file = new File(this.mCacheDir);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (!file2.getName().equals(".nomedia") && System.currentTimeMillis() - file2.lastModified() > 1296000000) {
                    file2.delete();
                }
            }
        }
    }

    class CacheWorker implements Runnable {
        /* access modifiers changed from: private */
        public File mFile;
        /* access modifiers changed from: private */
        public String mURL;

        CacheWorker(String str, File file) {
            this.mURL = str;
            this.mFile = file;
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x009d A[SYNTHETIC, Splitter:B:30:0x009d] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ab A[SYNTHETIC, Splitter:B:37:0x00ab] */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x00b2  */
        /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r7 = this;
                r0 = 0
                java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                r2.<init>()     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                com.medscape.android.cache.ImageCacheManager r3 = com.medscape.android.cache.ImageCacheManager.this     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                java.lang.String r3 = r3.mCacheDir     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                r2.append(r3)     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                java.lang.String r3 = java.io.File.separator     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                r2.append(r3)     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                java.lang.String r3 = ".temp_"
                r2.append(r3)     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                java.util.UUID r3 = java.util.UUID.randomUUID()     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                r2.append(r3)     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0082, all -> 0x007d }
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                java.lang.String r3 = r7.mURL     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                r3 = 8192(0x2000, float:1.14794E-41)
                byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                r4 = r0
            L_0x003d:
                int r5 = r2.read(r3)     // Catch:{ Exception -> 0x0073 }
                r6 = -1
                if (r5 <= r6) goto L_0x0051
                if (r4 != 0) goto L_0x004c
                java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0073 }
                r6.<init>(r1)     // Catch:{ Exception -> 0x0073 }
                r4 = r6
            L_0x004c:
                r6 = 0
                r4.write(r3, r6, r5)     // Catch:{ Exception -> 0x0073 }
                goto L_0x003d
            L_0x0051:
                r2.close()     // Catch:{ Exception -> 0x0073 }
                r4.close()     // Catch:{ Exception -> 0x0073 }
                java.io.File r2 = r7.mFile     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                com.medscape.android.helper.FileHelper.copyFiles(r1, r2)     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                com.medscape.android.cache.ImageCacheManager r2 = com.medscape.android.cache.ImageCacheManager.this     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                android.app.Application r2 = r2.mApp     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                if (r2 == 0) goto L_0x00a4
                com.medscape.android.cache.ImageCacheManager r2 = com.medscape.android.cache.ImageCacheManager.this     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                android.os.Handler r2 = r2.mMainHandler     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                com.medscape.android.cache.ImageCacheManager$CacheWorker$1 r3 = new com.medscape.android.cache.ImageCacheManager$CacheWorker$1     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                r3.<init>()     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                r2.post(r3)     // Catch:{ Exception -> 0x0079, all -> 0x0075 }
                goto L_0x00a4
            L_0x0073:
                r0 = move-exception
                goto L_0x0086
            L_0x0075:
                r2 = move-exception
                r4 = r0
                r0 = r2
                goto L_0x00a9
            L_0x0079:
                r2 = move-exception
                r4 = r0
                r0 = r2
                goto L_0x0086
            L_0x007d:
                r1 = move-exception
                r4 = r0
                r0 = r1
                r1 = r4
                goto L_0x00a9
            L_0x0082:
                r1 = move-exception
                r4 = r0
                r0 = r1
                r1 = r4
            L_0x0086:
                java.lang.String r2 = "CacheManager"
                java.lang.String r3 = "Failed to download file for cache"
                android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x00a8 }
                com.medscape.android.cache.ImageCacheManager r2 = com.medscape.android.cache.ImageCacheManager.this     // Catch:{ all -> 0x00a8 }
                android.os.Handler r2 = r2.mMainHandler     // Catch:{ all -> 0x00a8 }
                com.medscape.android.cache.ImageCacheManager$CacheWorker$2 r3 = new com.medscape.android.cache.ImageCacheManager$CacheWorker$2     // Catch:{ all -> 0x00a8 }
                r3.<init>(r0)     // Catch:{ all -> 0x00a8 }
                r2.post(r3)     // Catch:{ all -> 0x00a8 }
                if (r4 == 0) goto L_0x00a2
                r4.close()     // Catch:{ Exception -> 0x00a1 }
                goto L_0x00a2
            L_0x00a1:
            L_0x00a2:
                if (r1 == 0) goto L_0x00a7
            L_0x00a4:
                r1.delete()
            L_0x00a7:
                return
            L_0x00a8:
                r0 = move-exception
            L_0x00a9:
                if (r4 == 0) goto L_0x00b0
                r4.close()     // Catch:{ Exception -> 0x00af }
                goto L_0x00b0
            L_0x00af:
            L_0x00b0:
                if (r1 == 0) goto L_0x00b5
                r1.delete()
            L_0x00b5:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.cache.ImageCacheManager.CacheWorker.run():void");
        }
    }
}

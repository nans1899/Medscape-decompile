package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.WorkQueue;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static Handler handler;
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    public static void downloadAsync(ImageRequest imageRequest) {
        if (imageRequest != null) {
            RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
            synchronized (pendingRequests) {
                DownloaderContext downloaderContext = pendingRequests.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = imageRequest;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                } else {
                    enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
                }
            }
        }
    }

    public static boolean cancelRequest(ImageRequest imageRequest) {
        boolean z;
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            z = true;
            if (downloaderContext == null) {
                z = false;
            } else if (downloaderContext.workItem.cancel()) {
                pendingRequests.remove(requestKey);
            } else {
                downloaderContext.isCancelled = true;
            }
        }
        return z;
    }

    public static void prioritizeRequest(ImageRequest imageRequest) {
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }

    private static void enqueueCacheRead(ImageRequest imageRequest, RequestKey requestKey, boolean z) {
        enqueueRequest(imageRequest, requestKey, cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, z));
    }

    private static void enqueueDownload(ImageRequest imageRequest, RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }

    private static void enqueueRequest(ImageRequest imageRequest, RequestKey requestKey, WorkQueue workQueue, Runnable runnable) {
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = imageRequest;
            pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r2 = r7.request;
        r6 = r2.getCallback();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void issueResponse(com.facebook.internal.ImageDownloader.RequestKey r7, java.lang.Exception r8, android.graphics.Bitmap r9, boolean r10) {
        /*
            com.facebook.internal.ImageDownloader$DownloaderContext r7 = removePendingRequest(r7)
            if (r7 == 0) goto L_0x0022
            boolean r0 = r7.isCancelled
            if (r0 != 0) goto L_0x0022
            com.facebook.internal.ImageRequest r2 = r7.request
            com.facebook.internal.ImageRequest$Callback r6 = r2.getCallback()
            if (r6 == 0) goto L_0x0022
            android.os.Handler r7 = getHandler()
            com.facebook.internal.ImageDownloader$1 r0 = new com.facebook.internal.ImageDownloader$1
            r1 = r0
            r3 = r8
            r4 = r10
            r5 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            r7.post(r0)
        L_0x0022:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.issueResponse(com.facebook.internal.ImageDownloader$RequestKey, java.lang.Exception, android.graphics.Bitmap, boolean):void");
    }

    /* access modifiers changed from: private */
    public static void readFromCache(RequestKey requestKey, Context context, boolean z) {
        InputStream inputStream;
        Uri redirectedUri;
        boolean z2 = false;
        if (!z || (redirectedUri = UrlRedirectCache.getRedirectedUri(requestKey.uri)) == null) {
            inputStream = null;
        } else {
            inputStream = ImageResponseCache.getCachedImageStream(redirectedUri, context);
            if (inputStream != null) {
                z2 = true;
            }
        }
        if (!z2) {
            inputStream = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
        }
        if (inputStream != null) {
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            Utility.closeQuietly(inputStream);
            issueResponse(requestKey, (Exception) null, decodeStream, z2);
            return;
        }
        DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            enqueueDownload(removePendingRequest.request, requestKey);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.io.IOException} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a3, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a5, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a6, code lost:
        r10 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a3 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0014] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void download(com.facebook.internal.ImageDownloader.RequestKey r9, android.content.Context r10) {
        /*
            r0 = 0
            r1 = 0
            r2 = 1
            java.net.URL r3 = new java.net.URL     // Catch:{ IOException -> 0x00b1, all -> 0x00a8 }
            android.net.Uri r4 = r9.uri     // Catch:{ IOException -> 0x00b1, all -> 0x00a8 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00b1, all -> 0x00a8 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x00b1, all -> 0x00a8 }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ IOException -> 0x00b1, all -> 0x00a8 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x00b1, all -> 0x00a8 }
            r3.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x00a5, all -> 0x00a3 }
            int r4 = r3.getResponseCode()     // Catch:{ IOException -> 0x00a5, all -> 0x00a3 }
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 == r5) goto L_0x008c
            r10 = 301(0x12d, float:4.22E-43)
            if (r4 == r10) goto L_0x0059
            r10 = 302(0x12e, float:4.23E-43)
            if (r4 == r10) goto L_0x0059
            java.io.InputStream r10 = r3.getErrorStream()     // Catch:{ IOException -> 0x00a5, all -> 0x00a3 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            r4.<init>()     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            if (r10 == 0) goto L_0x0049
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            r5.<init>(r10)     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            r6 = 128(0x80, float:1.794E-43)
            char[] r7 = new char[r6]     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
        L_0x003b:
            int r8 = r5.read(r7, r1, r6)     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            if (r8 <= 0) goto L_0x0045
            r4.append(r7, r1, r8)     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            goto L_0x003b
        L_0x0045:
            com.facebook.internal.Utility.closeQuietly(r5)     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            goto L_0x004e
        L_0x0049:
            java.lang.String r5 = "Unexpected error while downloading an image."
            r4.append(r5)     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
        L_0x004e:
            com.facebook.FacebookException r5 = new com.facebook.FacebookException     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            r5.<init>((java.lang.String) r4)     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            r4 = r0
            goto L_0x0095
        L_0x0059:
            java.lang.String r10 = "location"
            java.lang.String r10 = r3.getHeaderField(r10)     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            boolean r2 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r10)     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            if (r2 != 0) goto L_0x0084
            android.net.Uri r10 = android.net.Uri.parse(r10)     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            android.net.Uri r2 = r9.uri     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            com.facebook.internal.UrlRedirectCache.cacheUriRedirect(r2, r10)     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            com.facebook.internal.ImageDownloader$DownloaderContext r2 = removePendingRequest(r9)     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            if (r2 == 0) goto L_0x0084
            boolean r4 = r2.isCancelled     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            if (r4 != 0) goto L_0x0084
            com.facebook.internal.ImageRequest r2 = r2.request     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            com.facebook.internal.ImageDownloader$RequestKey r4 = new com.facebook.internal.ImageDownloader$RequestKey     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            java.lang.Object r5 = r9.tag     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            r4.<init>(r10, r5)     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
            enqueueCacheRead(r2, r4, r1)     // Catch:{ IOException -> 0x0088, all -> 0x00a3 }
        L_0x0084:
            r4 = r0
            r5 = r4
            r2 = 0
            goto L_0x0096
        L_0x0088:
            r4 = move-exception
            r10 = r0
            r2 = 0
            goto L_0x00b4
        L_0x008c:
            java.io.InputStream r10 = com.facebook.internal.ImageResponseCache.interceptAndCacheImageStream(r10, r3)     // Catch:{ IOException -> 0x00a5, all -> 0x00a3 }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r10)     // Catch:{ IOException -> 0x00a1, all -> 0x009e }
            r5 = r0
        L_0x0095:
            r0 = r10
        L_0x0096:
            com.facebook.internal.Utility.closeQuietly(r0)
            com.facebook.internal.Utility.disconnectQuietly(r3)
            r0 = r4
            goto L_0x00bb
        L_0x009e:
            r9 = move-exception
            r0 = r10
            goto L_0x00aa
        L_0x00a1:
            r4 = move-exception
            goto L_0x00b4
        L_0x00a3:
            r9 = move-exception
            goto L_0x00aa
        L_0x00a5:
            r4 = move-exception
            r10 = r0
            goto L_0x00b4
        L_0x00a8:
            r9 = move-exception
            r3 = r0
        L_0x00aa:
            com.facebook.internal.Utility.closeQuietly(r0)
            com.facebook.internal.Utility.disconnectQuietly(r3)
            throw r9
        L_0x00b1:
            r4 = move-exception
            r10 = r0
            r3 = r10
        L_0x00b4:
            com.facebook.internal.Utility.closeQuietly(r10)
            com.facebook.internal.Utility.disconnectQuietly(r3)
            r5 = r4
        L_0x00bb:
            if (r2 == 0) goto L_0x00c0
            issueResponse(r9, r5, r0, r1)
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.download(com.facebook.internal.ImageDownloader$RequestKey, android.content.Context):void");
    }

    private static synchronized Handler getHandler() {
        Handler handler2;
        synchronized (ImageDownloader.class) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    private static DownloaderContext removePendingRequest(RequestKey requestKey) {
        DownloaderContext remove;
        synchronized (pendingRequests) {
            remove = pendingRequests.remove(requestKey);
        }
        return remove;
    }

    private static class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        Uri uri;

        RequestKey(Uri uri2, Object obj) {
            this.uri = uri2;
            this.tag = obj;
        }

        public int hashCode() {
            return ((1073 + this.uri.hashCode()) * 37) + this.tag.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) obj;
            if (requestKey.uri == this.uri && requestKey.tag == this.tag) {
                return true;
            }
            return false;
        }
    }

    private static class DownloaderContext {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;

        private DownloaderContext() {
        }
    }

    private static class CacheReadWorkItem implements Runnable {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        CacheReadWorkItem(Context context2, RequestKey requestKey, boolean z) {
            this.context = context2;
            this.key = requestKey;
            this.allowCachedRedirects = z;
        }

        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }

    private static class DownloadImageWorkItem implements Runnable {
        private Context context;
        private RequestKey key;

        DownloadImageWorkItem(Context context2, RequestKey requestKey) {
            this.context = context2;
            this.key = requestKey;
        }

        public void run() {
            ImageDownloader.download(this.key, this.context);
        }
    }
}

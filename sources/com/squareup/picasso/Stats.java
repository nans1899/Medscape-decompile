package com.squareup.picasso;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

class Stats {
    private static final int BITMAP_DECODE_FINISHED = 2;
    private static final int BITMAP_TRANSFORMED_FINISHED = 3;
    private static final int CACHE_HIT = 0;
    private static final int CACHE_MISS = 1;
    private static final int DOWNLOAD_FINISHED = 4;
    private static final String STATS_THREAD_NAME = "Picasso-Stats";
    long averageDownloadSize;
    long averageOriginalBitmapSize;
    long averageTransformedBitmapSize;
    final Cache cache;
    long cacheHits;
    long cacheMisses;
    int downloadCount;
    final Handler handler = new StatsHandler(this.statsThread.getLooper(), this);
    int originalBitmapCount;
    final HandlerThread statsThread;
    long totalDownloadSize;
    long totalOriginalBitmapSize;
    long totalTransformedBitmapSize;
    int transformedBitmapCount;

    Stats(Cache cache2) {
        this.cache = cache2;
        HandlerThread handlerThread = new HandlerThread(STATS_THREAD_NAME, 10);
        this.statsThread = handlerThread;
        handlerThread.start();
        Utils.flushStackLocalLeaks(this.statsThread.getLooper());
    }

    /* access modifiers changed from: package-private */
    public void dispatchBitmapDecoded(Bitmap bitmap) {
        processBitmap(bitmap, 2);
    }

    /* access modifiers changed from: package-private */
    public void dispatchBitmapTransformed(Bitmap bitmap) {
        processBitmap(bitmap, 3);
    }

    /* access modifiers changed from: package-private */
    public void dispatchDownloadFinished(long j) {
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(4, Long.valueOf(j)));
    }

    /* access modifiers changed from: package-private */
    public void dispatchCacheHit() {
        this.handler.sendEmptyMessage(0);
    }

    /* access modifiers changed from: package-private */
    public void dispatchCacheMiss() {
        this.handler.sendEmptyMessage(1);
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.statsThread.quit();
    }

    /* access modifiers changed from: package-private */
    public void performCacheHit() {
        this.cacheHits++;
    }

    /* access modifiers changed from: package-private */
    public void performCacheMiss() {
        this.cacheMisses++;
    }

    /* access modifiers changed from: package-private */
    public void performDownloadFinished(Long l) {
        this.downloadCount++;
        long longValue = this.totalDownloadSize + l.longValue();
        this.totalDownloadSize = longValue;
        this.averageDownloadSize = getAverage(this.downloadCount, longValue);
    }

    /* access modifiers changed from: package-private */
    public void performBitmapDecoded(long j) {
        int i = this.originalBitmapCount + 1;
        this.originalBitmapCount = i;
        long j2 = this.totalOriginalBitmapSize + j;
        this.totalOriginalBitmapSize = j2;
        this.averageOriginalBitmapSize = getAverage(i, j2);
    }

    /* access modifiers changed from: package-private */
    public void performBitmapTransformed(long j) {
        this.transformedBitmapCount++;
        long j2 = this.totalTransformedBitmapSize + j;
        this.totalTransformedBitmapSize = j2;
        this.averageTransformedBitmapSize = getAverage(this.originalBitmapCount, j2);
    }

    /* access modifiers changed from: package-private */
    public StatsSnapshot createSnapshot() {
        return new StatsSnapshot(this.cache.maxSize(), this.cache.size(), this.cacheHits, this.cacheMisses, this.totalDownloadSize, this.totalOriginalBitmapSize, this.totalTransformedBitmapSize, this.averageDownloadSize, this.averageOriginalBitmapSize, this.averageTransformedBitmapSize, this.downloadCount, this.originalBitmapCount, this.transformedBitmapCount, System.currentTimeMillis());
    }

    private void processBitmap(Bitmap bitmap, int i) {
        int bitmapBytes = Utils.getBitmapBytes(bitmap);
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(i, bitmapBytes, 0));
    }

    private static long getAverage(int i, long j) {
        return j / ((long) i);
    }

    private static class StatsHandler extends Handler {
        private final Stats stats;

        StatsHandler(Looper looper, Stats stats2) {
            super(looper);
            this.stats = stats2;
        }

        public void handleMessage(final Message message) {
            int i = message.what;
            if (i == 0) {
                this.stats.performCacheHit();
            } else if (i == 1) {
                this.stats.performCacheMiss();
            } else if (i == 2) {
                this.stats.performBitmapDecoded((long) message.arg1);
            } else if (i == 3) {
                this.stats.performBitmapTransformed((long) message.arg1);
            } else if (i != 4) {
                Picasso.HANDLER.post(new Runnable() {
                    public void run() {
                        throw new AssertionError("Unhandled stats message." + message.what);
                    }
                });
            } else {
                this.stats.performDownloadFinished((Long) message.obj);
            }
        }
    }
}

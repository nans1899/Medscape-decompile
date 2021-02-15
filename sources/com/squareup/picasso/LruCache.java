package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;

public final class LruCache implements Cache {
    final android.util.LruCache<String, BitmapAndSize> cache;

    public LruCache(Context context) {
        this(Utils.calculateMemoryCacheSize(context));
    }

    public LruCache(int i) {
        this.cache = new android.util.LruCache<String, BitmapAndSize>(i) {
            /* access modifiers changed from: protected */
            public int sizeOf(String str, BitmapAndSize bitmapAndSize) {
                return bitmapAndSize.byteCount;
            }
        };
    }

    public Bitmap get(String str) {
        BitmapAndSize bitmapAndSize = this.cache.get(str);
        if (bitmapAndSize != null) {
            return bitmapAndSize.bitmap;
        }
        return null;
    }

    public void set(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || bitmap == null");
        }
        int bitmapBytes = Utils.getBitmapBytes(bitmap);
        if (bitmapBytes > maxSize()) {
            this.cache.remove(str);
        } else {
            this.cache.put(str, new BitmapAndSize(bitmap, bitmapBytes));
        }
    }

    public int size() {
        return this.cache.size();
    }

    public int maxSize() {
        return this.cache.maxSize();
    }

    public void clear() {
        this.cache.evictAll();
    }

    public void clearKeyUri(String str) {
        for (String next : this.cache.snapshot().keySet()) {
            if (next.startsWith(str) && next.length() > str.length() && next.charAt(str.length()) == 10) {
                this.cache.remove(next);
            }
        }
    }

    public int hitCount() {
        return this.cache.hitCount();
    }

    public int missCount() {
        return this.cache.missCount();
    }

    public int putCount() {
        return this.cache.putCount();
    }

    public int evictionCount() {
        return this.cache.evictionCount();
    }

    static final class BitmapAndSize {
        final Bitmap bitmap;
        final int byteCount;

        BitmapAndSize(Bitmap bitmap2, int i) {
            this.bitmap = bitmap2;
            this.byteCount = i;
        }
    }
}

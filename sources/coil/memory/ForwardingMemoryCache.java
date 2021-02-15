package coil.memory;

import android.graphics.Bitmap;
import coil.memory.MemoryCache;
import coil.util.Bitmaps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcoil/memory/ForwardingMemoryCache;", "Lcoil/memory/MemoryCache;", "weakMemoryCache", "Lcoil/memory/WeakMemoryCache;", "(Lcoil/memory/WeakMemoryCache;)V", "clearMemory", "", "get", "Lcoil/memory/MemoryCache$Value;", "key", "Lcoil/memory/MemoryCache$Key;", "invalidate", "maxSize", "", "set", "bitmap", "Landroid/graphics/Bitmap;", "isSampled", "", "size", "trimMemory", "level", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MemoryCache.kt */
final class ForwardingMemoryCache implements MemoryCache {
    private final WeakMemoryCache weakMemoryCache;

    public void clearMemory() {
    }

    public void invalidate(MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
    }

    public int maxSize() {
        return 0;
    }

    public int size() {
        return 0;
    }

    public void trimMemory(int i) {
    }

    public ForwardingMemoryCache(WeakMemoryCache weakMemoryCache2) {
        Intrinsics.checkParameterIsNotNull(weakMemoryCache2, "weakMemoryCache");
        this.weakMemoryCache = weakMemoryCache2;
    }

    public MemoryCache.Value get(MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.weakMemoryCache.get(key);
    }

    public void set(MemoryCache.Key key, Bitmap bitmap, boolean z) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        this.weakMemoryCache.set(key, bitmap, z, Bitmaps.getAllocationByteCountCompat(bitmap));
    }
}

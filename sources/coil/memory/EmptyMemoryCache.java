package coil.memory;

import android.graphics.Bitmap;
import coil.memory.MemoryCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000bH\u0016¨\u0006\u0014"}, d2 = {"Lcoil/memory/EmptyMemoryCache;", "Lcoil/memory/MemoryCache;", "()V", "clearMemory", "", "get", "Lcoil/memory/MemoryCache$Value;", "key", "Lcoil/memory/MemoryCache$Key;", "invalidate", "maxSize", "", "set", "bitmap", "Landroid/graphics/Bitmap;", "isSampled", "", "size", "trimMemory", "level", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MemoryCache.kt */
final class EmptyMemoryCache implements MemoryCache {
    public static final EmptyMemoryCache INSTANCE = new EmptyMemoryCache();

    public void clearMemory() {
    }

    public MemoryCache.Value get(MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return null;
    }

    public void invalidate(MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
    }

    public int maxSize() {
        return 0;
    }

    public void set(MemoryCache.Key key, Bitmap bitmap, boolean z) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
    }

    public int size() {
        return 0;
    }

    public void trimMemory(int i) {
    }

    private EmptyMemoryCache() {
    }
}

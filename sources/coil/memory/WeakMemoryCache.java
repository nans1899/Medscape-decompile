package coil.memory;

import android.graphics.Bitmap;
import coil.memory.MemoryCache;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J(\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000fH&¨\u0006\u0012"}, d2 = {"Lcoil/memory/WeakMemoryCache;", "", "clearMemory", "", "get", "Lcoil/memory/MemoryCache$Value;", "key", "Lcoil/memory/MemoryCache$Key;", "invalidate", "bitmap", "Landroid/graphics/Bitmap;", "set", "isSampled", "", "size", "", "trimMemory", "level", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WeakMemoryCache.kt */
public interface WeakMemoryCache {
    void clearMemory();

    MemoryCache.Value get(MemoryCache.Key key);

    void invalidate(Bitmap bitmap);

    void invalidate(MemoryCache.Key key);

    void set(MemoryCache.Key key, Bitmap bitmap, boolean z, int i);

    void trimMemory(int i);
}

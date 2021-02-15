package coil.memory;

import android.graphics.Bitmap;
import coil.memory.MemoryCache;
import coil.util.Bitmaps;
import coil.util.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006*\u0001\f\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0007H\u0016J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016R\u0010\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcoil/memory/RealMemoryCache;", "Lcoil/memory/MemoryCache;", "weakMemoryCache", "Lcoil/memory/WeakMemoryCache;", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "maxSize", "", "logger", "Lcoil/util/Logger;", "(Lcoil/memory/WeakMemoryCache;Lcoil/memory/BitmapReferenceCounter;ILcoil/util/Logger;)V", "cache", "coil/memory/RealMemoryCache$cache$1", "Lcoil/memory/RealMemoryCache$cache$1;", "clearMemory", "", "get", "Lcoil/memory/MemoryCache$Value;", "key", "Lcoil/memory/MemoryCache$Key;", "invalidate", "set", "bitmap", "Landroid/graphics/Bitmap;", "isSampled", "", "size", "trimMemory", "level", "Companion", "InternalValue", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MemoryCache.kt */
final class RealMemoryCache implements MemoryCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "RealMemoryCache";
    private final RealMemoryCache$cache$1 cache;
    private final Logger logger;
    /* access modifiers changed from: private */
    public final BitmapReferenceCounter referenceCounter;
    /* access modifiers changed from: private */
    public final WeakMemoryCache weakMemoryCache;

    public RealMemoryCache(WeakMemoryCache weakMemoryCache2, BitmapReferenceCounter bitmapReferenceCounter, int i, Logger logger2) {
        Intrinsics.checkParameterIsNotNull(weakMemoryCache2, "weakMemoryCache");
        Intrinsics.checkParameterIsNotNull(bitmapReferenceCounter, "referenceCounter");
        this.weakMemoryCache = weakMemoryCache2;
        this.referenceCounter = bitmapReferenceCounter;
        this.logger = logger2;
        this.cache = new RealMemoryCache$cache$1(this, i, i);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcoil/memory/RealMemoryCache$Companion;", "", "()V", "TAG", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MemoryCache.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MemoryCache.Value get(MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        InternalValue internalValue = (InternalValue) this.cache.get(key);
        return internalValue != null ? internalValue : this.weakMemoryCache.get(key);
    }

    public void set(MemoryCache.Key key, Bitmap bitmap, boolean z) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
        if (allocationByteCountCompat <= maxSize()) {
            this.referenceCounter.increment(bitmap);
            this.cache.put(key, new InternalValue(bitmap, z, allocationByteCountCompat));
        } else if (((InternalValue) this.cache.remove(key)) == null) {
            this.weakMemoryCache.set(key, bitmap, z, allocationByteCountCompat);
        }
    }

    public int size() {
        return this.cache.size();
    }

    public int maxSize() {
        return this.cache.maxSize();
    }

    public void invalidate(MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, "invalidate, key=" + key, (Throwable) null);
        }
        this.cache.remove(key);
    }

    public void clearMemory() {
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, "clearMemory", (Throwable) null);
        }
        this.cache.trimToSize(-1);
    }

    public void trimMemory(int i) {
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, "trimMemory, level=" + i, (Throwable) null);
        }
        if (i >= 40) {
            clearMemory();
        } else if (10 <= i && 20 > i) {
            this.cache.trimToSize(size() / 2);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcoil/memory/RealMemoryCache$InternalValue;", "Lcoil/memory/MemoryCache$Value;", "bitmap", "Landroid/graphics/Bitmap;", "isSampled", "", "size", "", "(Landroid/graphics/Bitmap;ZI)V", "getBitmap", "()Landroid/graphics/Bitmap;", "()Z", "getSize", "()I", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MemoryCache.kt */
    private static final class InternalValue implements MemoryCache.Value {
        private final Bitmap bitmap;
        private final boolean isSampled;
        private final int size;

        public InternalValue(Bitmap bitmap2, boolean z, int i) {
            Intrinsics.checkParameterIsNotNull(bitmap2, "bitmap");
            this.bitmap = bitmap2;
            this.isSampled = z;
            this.size = i;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public boolean isSampled() {
            return this.isSampled;
        }

        public final int getSize() {
            return this.size;
        }
    }
}

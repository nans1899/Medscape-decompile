package coil.memory;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import coil.bitmappool.BitmapPool;
import coil.collection.SparseIntArraySet;
import coil.util.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u00020\n8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00108\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcoil/memory/BitmapReferenceCounter;", "", "weakMemoryCache", "Lcoil/memory/WeakMemoryCache;", "bitmapPool", "Lcoil/bitmappool/BitmapPool;", "logger", "Lcoil/util/Logger;", "(Lcoil/memory/WeakMemoryCache;Lcoil/bitmappool/BitmapPool;Lcoil/util/Logger;)V", "counts", "Landroid/util/SparseIntArray;", "counts$annotations", "()V", "getCounts$coil_base_release", "()Landroid/util/SparseIntArray;", "invalidKeys", "Lcoil/collection/SparseIntArraySet;", "invalidKeys$annotations", "getInvalidKeys$coil_base_release", "()Lcoil/collection/SparseIntArraySet;", "decrement", "", "bitmap", "Landroid/graphics/Bitmap;", "increment", "", "invalidate", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BitmapReferenceCounter.kt */
public final class BitmapReferenceCounter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "BitmapReferenceCounter";
    private final BitmapPool bitmapPool;
    private final SparseIntArray counts = new SparseIntArray();
    private final SparseIntArraySet invalidKeys = new SparseIntArraySet(0, 1, (DefaultConstructorMarker) null);
    private final Logger logger;
    private final WeakMemoryCache weakMemoryCache;

    public static /* synthetic */ void counts$annotations() {
    }

    public static /* synthetic */ void invalidKeys$annotations() {
    }

    public BitmapReferenceCounter(WeakMemoryCache weakMemoryCache2, BitmapPool bitmapPool2, Logger logger2) {
        Intrinsics.checkParameterIsNotNull(weakMemoryCache2, "weakMemoryCache");
        Intrinsics.checkParameterIsNotNull(bitmapPool2, "bitmapPool");
        this.weakMemoryCache = weakMemoryCache2;
        this.bitmapPool = bitmapPool2;
        this.logger = logger2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcoil/memory/BitmapReferenceCounter$Companion;", "", "()V", "TAG", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BitmapReferenceCounter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final SparseIntArray getCounts$coil_base_release() {
        return this.counts;
    }

    public final SparseIntArraySet getInvalidKeys$coil_base_release() {
        return this.invalidKeys;
    }

    public final void invalidate(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        this.invalidKeys.add(System.identityHashCode(bitmap));
    }

    public final void increment(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int identityHashCode = System.identityHashCode(bitmap);
        int i = this.counts.get(identityHashCode) + 1;
        this.counts.put(identityHashCode, i);
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, "INCREMENT: [" + identityHashCode + ", " + i + ']', (Throwable) null);
        }
    }

    public final boolean decrement(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int identityHashCode = System.identityHashCode(bitmap);
        int i = this.counts.get(identityHashCode) - 1;
        this.counts.put(identityHashCode, i);
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, "DECREMENT: [" + identityHashCode + ", " + i + ']', (Throwable) null);
        }
        if (i > 0) {
            return false;
        }
        this.counts.delete(identityHashCode);
        if (!(!this.invalidKeys.remove(identityHashCode))) {
            return false;
        }
        this.weakMemoryCache.invalidate(bitmap);
        this.bitmapPool.put(bitmap);
        return true;
    }
}

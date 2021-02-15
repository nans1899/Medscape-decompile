package coil.memory;

import coil.memory.RealWeakMemoryCache;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcoil/memory/RealWeakMemoryCache$WeakValue;", "test"}, k = 3, mv = {1, 1, 16})
/* compiled from: WeakMemoryCache.kt */
final class RealWeakMemoryCache$cleanUp$1<T> implements Predicate<RealWeakMemoryCache.WeakValue> {
    public static final RealWeakMemoryCache$cleanUp$1 INSTANCE = new RealWeakMemoryCache$cleanUp$1();

    RealWeakMemoryCache$cleanUp$1() {
    }

    public final boolean test(RealWeakMemoryCache.WeakValue weakValue) {
        Intrinsics.checkParameterIsNotNull(weakValue, "it");
        return weakValue.getReference().get() == null;
    }
}

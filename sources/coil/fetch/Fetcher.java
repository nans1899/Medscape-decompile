package coil.fetch;

import coil.bitmappool.BitmapPool;
import coil.decode.Options;
import coil.size.Size;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0012\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcoil/fetch/Fetcher;", "T", "", "fetch", "Lcoil/fetch/FetchResult;", "pool", "Lcoil/bitmappool/BitmapPool;", "data", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Ljava/lang/Object;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handles", "", "(Ljava/lang/Object;)Z", "key", "", "(Ljava/lang/Object;)Ljava/lang/String;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Fetcher.kt */
public interface Fetcher<T> {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: Fetcher.kt */
    public static final class DefaultImpls {
        public static <T> boolean handles(Fetcher<T> fetcher, T t) {
            Intrinsics.checkParameterIsNotNull(t, "data");
            return true;
        }
    }

    Object fetch(BitmapPool bitmapPool, T t, Size size, Options options, Continuation<? super FetchResult> continuation);

    boolean handles(T t);

    String key(T t);
}

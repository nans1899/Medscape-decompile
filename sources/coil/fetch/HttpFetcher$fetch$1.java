package coil.fetch;

import coil.bitmappool.BitmapPool;
import coil.decode.Options;
import coil.size.Size;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH@"}, d2 = {"fetch", "", "T", "pool", "Lcoil/bitmappool/BitmapPool;", "data", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcoil/fetch/FetchResult;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.fetch.HttpFetcher", f = "HttpFetcher.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {106}, m = "fetch$suspendImpl", n = {"this", "pool", "data", "size", "options", "url", "request", "networkRead", "diskRead", "$this$await$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "Z$1", "L$7"})
/* compiled from: HttpFetcher.kt */
final class HttpFetcher$fetch$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpFetcher$fetch$1(HttpFetcher httpFetcher, Continuation continuation) {
        super(continuation);
        this.this$0 = httpFetcher;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpFetcher.fetch$suspendImpl(this.this$0, (BitmapPool) null, (Object) null, (Size) null, (Options) null, this);
    }
}

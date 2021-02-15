package coil;

import coil.RealImageLoader;
import coil.fetch.Fetcher;
import coil.request.Parameters;
import coil.transform.Transformation;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eHH"}, d2 = {"computeCacheKey", "", "T", "fetcher", "Lcoil/fetch/Fetcher;", "data", "parameters", "Lcoil/request/Parameters;", "transformations", "", "Lcoil/transform/Transformation;", "lazySizeResolver", "Lcoil/RealImageLoader$LazySizeResolver;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcoil/memory/MemoryCache$Key;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader", f = "RealImageLoader.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {530}, m = "computeCacheKey$coil_base_release", n = {"this", "fetcher", "data", "parameters", "transformations", "lazySizeResolver", "baseKey", "this_$iv", "cached$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
/* compiled from: RealImageLoader.kt */
public final class RealImageLoader$computeCacheKey$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RealImageLoader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RealImageLoader$computeCacheKey$1(RealImageLoader realImageLoader, Continuation continuation) {
        super(continuation);
        this.this$0 = realImageLoader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.computeCacheKey$coil_base_release((Fetcher) null, null, (Parameters) null, (List<? extends Transformation>) null, (RealImageLoader.LazySizeResolver) null, this);
    }
}

package coil;

import coil.RealImageLoader;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006HH"}, d2 = {"mapData", "", "data", "lazySizeResolver", "Lcoil/RealImageLoader$LazySizeResolver;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader", f = "RealImageLoader.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {502}, m = "mapData$coil_base_release", n = {"this", "data", "lazySizeResolver", "mappedData", "$this$forEachIndices$iv", "i$iv", "$dstr$type$mapper", "type", "mapper", "this_$iv", "cached$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$5", "L$6", "L$7", "L$8", "L$12"})
/* compiled from: RealImageLoader.kt */
public final class RealImageLoader$mapData$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
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
    public RealImageLoader$mapData$1(RealImageLoader realImageLoader, Continuation continuation) {
        super(continuation);
        this.this$0 = realImageLoader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mapData$coil_base_release((Object) null, (RealImageLoader.LazySizeResolver) null, this);
    }
}

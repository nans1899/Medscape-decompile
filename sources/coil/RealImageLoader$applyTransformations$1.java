package coil;

import coil.decode.Options;
import coil.fetch.DrawableResult;
import coil.request.Request;
import coil.size.Size;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fHH"}, d2 = {"applyTransformations", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "result", "Lcoil/fetch/DrawableResult;", "request", "Lcoil/request/Request;", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "eventListener", "Lcoil/EventListener;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader", f = "RealImageLoader.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {414}, m = "applyTransformations$coil_base_release", n = {"this", "scope", "result", "request", "size", "options", "eventListener", "$this$run", "transformations", "$this$foldIndices$iv", "accumulator$iv", "i$iv", "baseBitmap", "transformation", "bitmap"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "I$0", "L$11", "L$12", "L$13"})
/* compiled from: RealImageLoader.kt */
public final class RealImageLoader$applyTransformations$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
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
    public RealImageLoader$applyTransformations$1(RealImageLoader realImageLoader, Continuation continuation) {
        super(continuation);
        this.this$0 = realImageLoader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.applyTransformations$coil_base_release((CoroutineScope) null, (DrawableResult) null, (Request) null, (Size) null, (Options) null, (EventListener) null, this);
    }
}

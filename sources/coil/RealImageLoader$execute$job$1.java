package coil;

import coil.request.LoadRequest;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader$execute$job$1", f = "RealImageLoader.kt", i = {0}, l = {138}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: RealImageLoader.kt */
final class RealImageLoader$execute$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadRequest $request;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ RealImageLoader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RealImageLoader$execute$job$1(RealImageLoader realImageLoader, LoadRequest loadRequest, Continuation continuation) {
        super(2, continuation);
        this.this$0 = realImageLoader;
        this.$request = loadRequest;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        RealImageLoader$execute$job$1 realImageLoader$execute$job$1 = new RealImageLoader$execute$job$1(this.this$0, this.$request, continuation);
        realImageLoader$execute$job$1.p$ = (CoroutineScope) obj;
        return realImageLoader$execute$job$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RealImageLoader$execute$job$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.p$;
            this.label = 1;
            if (this.this$0.executeInternal(this.$request, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}

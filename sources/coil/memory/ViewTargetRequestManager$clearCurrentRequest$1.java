package coil.memory;

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
@DebugMetadata(c = "coil.memory.ViewTargetRequestManager$clearCurrentRequest$1", f = "ViewTargetRequestManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ViewTargetRequestManager.kt */
final class ViewTargetRequestManager$clearCurrentRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ViewTargetRequestManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ViewTargetRequestManager$clearCurrentRequest$1(ViewTargetRequestManager viewTargetRequestManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = viewTargetRequestManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        ViewTargetRequestManager$clearCurrentRequest$1 viewTargetRequestManager$clearCurrentRequest$1 = new ViewTargetRequestManager$clearCurrentRequest$1(this.this$0, continuation);
        viewTargetRequestManager$clearCurrentRequest$1.p$ = (CoroutineScope) obj;
        return viewTargetRequestManager$clearCurrentRequest$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ViewTargetRequestManager$clearCurrentRequest$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.setCurrentRequest((ViewTargetRequestDelegate) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}

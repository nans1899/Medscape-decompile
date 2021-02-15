package coil.memory;

import coil.request.SuccessResult;
import coil.transition.Transition;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@"}, d2 = {"success", "", "result", "Lcoil/request/SuccessResult;", "transition", "Lcoil/transition/Transition;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.memory.PoolableTargetDelegate", f = "TargetDelegate.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {233}, m = "success", n = {"this", "result", "transition", "$this$instrument$iv", "bitmap$iv", "$this$instrument", "$this$onSuccess$iv", "request$iv", "result$iv", "transition$iv", "eventListener$iv", "logger$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11"})
/* compiled from: TargetDelegate.kt */
final class PoolableTargetDelegate$success$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
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
    final /* synthetic */ PoolableTargetDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PoolableTargetDelegate$success$1(PoolableTargetDelegate poolableTargetDelegate, Continuation continuation) {
        super(continuation);
        this.this$0 = poolableTargetDelegate;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.success((SuccessResult) null, (Transition) null, this);
    }
}

package coil.memory;

import coil.request.ErrorResult;
import coil.transition.Transition;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@"}, d2 = {"error", "", "result", "Lcoil/request/ErrorResult;", "transition", "Lcoil/transition/Transition;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.memory.InvalidatableTargetDelegate", f = "TargetDelegate.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {246}, m = "error", n = {"this", "result", "transition", "$this$onError$iv", "request$iv", "eventListener$iv", "logger$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* compiled from: TargetDelegate.kt */
final class InvalidatableTargetDelegate$error$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ InvalidatableTargetDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InvalidatableTargetDelegate$error$1(InvalidatableTargetDelegate invalidatableTargetDelegate, Continuation continuation) {
        super(continuation);
        this.this$0 = invalidatableTargetDelegate;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.error((ErrorResult) null, (Transition) null, this);
    }
}

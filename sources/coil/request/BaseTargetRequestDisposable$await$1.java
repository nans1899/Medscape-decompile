package coil.request;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H@"}, d2 = {"await", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.request.BaseTargetRequestDisposable", f = "RequestDisposable.kt", i = {0}, l = {49}, m = "await", n = {"this"}, s = {"L$0"})
/* compiled from: RequestDisposable.kt */
final class BaseTargetRequestDisposable$await$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseTargetRequestDisposable this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseTargetRequestDisposable$await$1(BaseTargetRequestDisposable baseTargetRequestDisposable, Continuation continuation) {
        super(continuation);
        this.this$0 = baseTargetRequestDisposable;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.await(this);
    }
}

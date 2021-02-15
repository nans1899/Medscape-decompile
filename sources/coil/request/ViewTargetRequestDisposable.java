package coil.request;

import coil.target.ViewTarget;
import coil.util.Extensions;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000bH\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcoil/request/ViewTargetRequestDisposable;", "Lcoil/request/RequestDisposable;", "requestId", "Ljava/util/UUID;", "target", "Lcoil/target/ViewTarget;", "(Ljava/util/UUID;Lcoil/target/ViewTarget;)V", "isDisposed", "", "()Z", "await", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispose", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestDisposable.kt */
public final class ViewTargetRequestDisposable implements RequestDisposable {
    private final UUID requestId;
    private final ViewTarget<?> target;

    public ViewTargetRequestDisposable(UUID uuid, ViewTarget<?> viewTarget) {
        Intrinsics.checkParameterIsNotNull(uuid, "requestId");
        Intrinsics.checkParameterIsNotNull(viewTarget, "target");
        this.requestId = uuid;
        this.target = viewTarget;
    }

    public boolean isDisposed() {
        return !Intrinsics.areEqual((Object) Extensions.getRequestManager(this.target.getView()).getCurrentRequestId(), (Object) this.requestId);
    }

    public void dispose() {
        if (!isDisposed()) {
            Extensions.getRequestManager(this.target.getView()).clearCurrentRequest();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object await(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof coil.request.ViewTargetRequestDisposable$await$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            coil.request.ViewTargetRequestDisposable$await$1 r0 = (coil.request.ViewTargetRequestDisposable$await$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            coil.request.ViewTargetRequestDisposable$await$1 r0 = new coil.request.ViewTargetRequestDisposable$await$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            coil.request.ViewTargetRequestDisposable r0 = (coil.request.ViewTargetRequestDisposable) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x005a
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            boolean r5 = r4.isDisposed()
            if (r5 != 0) goto L_0x005a
            coil.target.ViewTarget<?> r5 = r4.target
            android.view.View r5 = r5.getView()
            coil.memory.ViewTargetRequestManager r5 = coil.util.Extensions.getRequestManager(r5)
            kotlinx.coroutines.Job r5 = r5.getCurrentRequestJob()
            if (r5 == 0) goto L_0x005a
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.join(r0)
            if (r5 != r1) goto L_0x005a
            return r1
        L_0x005a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.request.ViewTargetRequestDisposable.await(kotlin.coroutines.Continuation):java.lang.Object");
    }
}

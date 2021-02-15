package coil.request;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\tH\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcoil/request/BaseTargetRequestDisposable;", "Lcoil/request/RequestDisposable;", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "isDisposed", "", "()Z", "await", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispose", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestDisposable.kt */
public final class BaseTargetRequestDisposable implements RequestDisposable {
    private final Job job;

    public BaseTargetRequestDisposable(Job job2) {
        Intrinsics.checkParameterIsNotNull(job2, "job");
        this.job = job2;
    }

    public boolean isDisposed() {
        return !this.job.isActive();
    }

    public void dispose() {
        if (!isDisposed()) {
            Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object await(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof coil.request.BaseTargetRequestDisposable$await$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            coil.request.BaseTargetRequestDisposable$await$1 r0 = (coil.request.BaseTargetRequestDisposable$await$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            coil.request.BaseTargetRequestDisposable$await$1 r0 = new coil.request.BaseTargetRequestDisposable$await$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            coil.request.BaseTargetRequestDisposable r0 = (coil.request.BaseTargetRequestDisposable) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x004c
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r5)
            boolean r5 = r4.isDisposed()
            if (r5 != 0) goto L_0x004c
            kotlinx.coroutines.Job r5 = r4.job
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.join(r0)
            if (r5 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.request.BaseTargetRequestDisposable.await(kotlin.coroutines.Continuation):java.lang.Object");
    }
}

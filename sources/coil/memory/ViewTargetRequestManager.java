package coil.memory;

import android.view.View;
import coil.util.Extensions;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0007J\b\u0010\u0014\u001a\u00020\u0006H\u0003J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0017J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0017J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\nH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\n@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcoil/memory/ViewTargetRequestManager;", "Landroid/view/View$OnAttachStateChangeListener;", "()V", "currentRequest", "Lcoil/memory/ViewTargetRequestDelegate;", "<set-?>", "Ljava/util/UUID;", "currentRequestId", "getCurrentRequestId", "()Ljava/util/UUID;", "Lkotlinx/coroutines/Job;", "currentRequestJob", "getCurrentRequestJob", "()Lkotlinx/coroutines/Job;", "isRestart", "", "pendingClear", "skipAttach", "clearCurrentRequest", "", "newRequestId", "onViewAttachedToWindow", "v", "Landroid/view/View;", "onViewDetachedFromWindow", "setCurrentRequest", "request", "setCurrentRequestJob", "job", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ViewTargetRequestManager.kt */
public final class ViewTargetRequestManager implements View.OnAttachStateChangeListener {
    private ViewTargetRequestDelegate currentRequest;
    private volatile UUID currentRequestId;
    private volatile Job currentRequestJob;
    private boolean isRestart;
    private volatile Job pendingClear;
    private boolean skipAttach = true;

    public final UUID getCurrentRequestId() {
        return this.currentRequestId;
    }

    public final Job getCurrentRequestJob() {
        return this.currentRequestJob;
    }

    public final void setCurrentRequest(ViewTargetRequestDelegate viewTargetRequestDelegate) {
        if (this.isRestart) {
            this.isRestart = false;
        } else {
            Job job = this.pendingClear;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.pendingClear = null;
        }
        ViewTargetRequestDelegate viewTargetRequestDelegate2 = this.currentRequest;
        if (viewTargetRequestDelegate2 != null) {
            viewTargetRequestDelegate2.dispose();
        }
        this.currentRequest = viewTargetRequestDelegate;
        this.skipAttach = true;
    }

    public final UUID setCurrentRequestJob(Job job) {
        Intrinsics.checkParameterIsNotNull(job, "job");
        UUID newRequestId = newRequestId();
        this.currentRequestId = newRequestId;
        this.currentRequestJob = job;
        return newRequestId;
    }

    public final void clearCurrentRequest() {
        this.currentRequestId = null;
        this.currentRequestJob = null;
        Job job = this.pendingClear;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.pendingClear = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().getImmediate()), (CoroutineContext) null, (CoroutineStart) null, new ViewTargetRequestManager$clearCurrentRequest$1(this, (Continuation) null), 3, (Object) null);
    }

    public void onViewAttachedToWindow(View view) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        if (this.skipAttach) {
            this.skipAttach = false;
            return;
        }
        ViewTargetRequestDelegate viewTargetRequestDelegate = this.currentRequest;
        if (viewTargetRequestDelegate != null) {
            this.isRestart = true;
            viewTargetRequestDelegate.restart();
        }
    }

    public void onViewDetachedFromWindow(View view) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        this.skipAttach = false;
        ViewTargetRequestDelegate viewTargetRequestDelegate = this.currentRequest;
        if (viewTargetRequestDelegate != null) {
            viewTargetRequestDelegate.dispose();
        }
    }

    private final UUID newRequestId() {
        UUID uuid = this.currentRequestId;
        if (uuid != null && this.isRestart && Extensions.isMainThread()) {
            return uuid;
        }
        UUID randomUUID = UUID.randomUUID();
        Intrinsics.checkExpressionValueIsNotNull(randomUUID, "UUID.randomUUID()");
        return randomUUID;
    }
}

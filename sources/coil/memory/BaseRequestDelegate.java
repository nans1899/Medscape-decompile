package coil.memory;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcoil/memory/BaseRequestDelegate;", "Lcoil/memory/RequestDelegate;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "job", "Lkotlinx/coroutines/Job;", "(Landroidx/lifecycle/Lifecycle;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/Job;)V", "dispose", "", "onComplete", "onDestroy", "owner", "Landroidx/lifecycle/LifecycleOwner;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestDelegate.kt */
public final class BaseRequestDelegate extends RequestDelegate {
    private final CoroutineDispatcher dispatcher;
    private final Job job;
    private final Lifecycle lifecycle;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseRequestDelegate(Lifecycle lifecycle2, CoroutineDispatcher coroutineDispatcher, Job job2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(lifecycle2, "lifecycle");
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "dispatcher");
        Intrinsics.checkParameterIsNotNull(job2, "job");
        this.lifecycle = lifecycle2;
        this.dispatcher = coroutineDispatcher;
        this.job = job2;
    }

    public void dispose() {
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
    }

    public void onComplete() {
        CoroutineDispatcher coroutineDispatcher = this.dispatcher;
        if (coroutineDispatcher instanceof LifecycleObserver) {
            this.lifecycle.removeObserver((LifecycleObserver) coroutineDispatcher);
        }
        this.lifecycle.removeObserver(this);
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        dispose();
    }
}

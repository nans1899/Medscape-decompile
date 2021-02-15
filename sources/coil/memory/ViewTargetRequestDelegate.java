package coil.memory;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import coil.ImageLoader;
import coil.request.LoadRequest;
import com.facebook.share.internal.ShareConstants;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcoil/memory/ViewTargetRequestDelegate;", "Lcoil/memory/RequestDelegate;", "imageLoader", "Lcoil/ImageLoader;", "request", "Lcoil/request/LoadRequest;", "target", "Lcoil/memory/TargetDelegate;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "job", "Lkotlinx/coroutines/Job;", "(Lcoil/ImageLoader;Lcoil/request/LoadRequest;Lcoil/memory/TargetDelegate;Landroidx/lifecycle/Lifecycle;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/Job;)V", "dispose", "", "onComplete", "onDestroy", "owner", "Landroidx/lifecycle/LifecycleOwner;", "restart", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestDelegate.kt */
public final class ViewTargetRequestDelegate extends RequestDelegate {
    private final CoroutineDispatcher dispatcher;
    private final ImageLoader imageLoader;
    private final Job job;
    private final Lifecycle lifecycle;
    private final LoadRequest request;
    private final TargetDelegate target;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewTargetRequestDelegate(ImageLoader imageLoader2, LoadRequest loadRequest, TargetDelegate targetDelegate, Lifecycle lifecycle2, CoroutineDispatcher coroutineDispatcher, Job job2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(imageLoader2, "imageLoader");
        Intrinsics.checkParameterIsNotNull(loadRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(targetDelegate, "target");
        Intrinsics.checkParameterIsNotNull(lifecycle2, "lifecycle");
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "dispatcher");
        Intrinsics.checkParameterIsNotNull(job2, "job");
        this.imageLoader = imageLoader2;
        this.request = loadRequest;
        this.target = targetDelegate;
        this.lifecycle = lifecycle2;
        this.dispatcher = coroutineDispatcher;
        this.job = job2;
    }

    public final void restart() {
        this.imageLoader.execute(this.request);
    }

    public void dispose() {
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
        this.target.clear();
        if (this.request.getTarget() instanceof LifecycleObserver) {
            this.lifecycle.removeObserver((LifecycleObserver) this.request.getTarget());
        }
        this.lifecycle.removeObserver(this);
    }

    public void onComplete() {
        CoroutineDispatcher coroutineDispatcher = this.dispatcher;
        if (coroutineDispatcher instanceof LifecycleObserver) {
            this.lifecycle.removeObserver((LifecycleObserver) coroutineDispatcher);
        }
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        dispose();
    }
}

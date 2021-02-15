package coil.memory;

import androidx.lifecycle.Lifecycle;
import coil.EventListener;
import coil.ImageLoader;
import coil.request.GetRequest;
import coil.request.LoadRequest;
import coil.request.Request;
import coil.target.PoolableViewTarget;
import coil.target.Target;
import coil.target.ViewTarget;
import coil.util.Extensions;
import coil.util.Logger;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Deferred;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ4\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0007J\u0016\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcoil/memory/DelegateService;", "", "imageLoader", "Lcoil/ImageLoader;", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "logger", "Lcoil/util/Logger;", "(Lcoil/ImageLoader;Lcoil/memory/BitmapReferenceCounter;Lcoil/util/Logger;)V", "createRequestDelegate", "Lcoil/memory/RequestDelegate;", "request", "Lcoil/request/Request;", "targetDelegate", "Lcoil/memory/TargetDelegate;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "deferred", "Lkotlinx/coroutines/Deferred;", "createTargetDelegate", "eventListener", "Lcoil/EventListener;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DelegateService.kt */
public final class DelegateService {
    private final ImageLoader imageLoader;
    private final Logger logger;
    private final BitmapReferenceCounter referenceCounter;

    public DelegateService(ImageLoader imageLoader2, BitmapReferenceCounter bitmapReferenceCounter, Logger logger2) {
        Intrinsics.checkParameterIsNotNull(imageLoader2, "imageLoader");
        Intrinsics.checkParameterIsNotNull(bitmapReferenceCounter, "referenceCounter");
        this.imageLoader = imageLoader2;
        this.referenceCounter = bitmapReferenceCounter;
        this.logger = logger2;
    }

    public final TargetDelegate createTargetDelegate(Request request, EventListener eventListener) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(eventListener, "eventListener");
        if (request instanceof GetRequest) {
            return new InvalidatableEmptyTargetDelegate(this.referenceCounter);
        }
        if (request instanceof LoadRequest) {
            Target target = request.getTarget();
            if (target == null) {
                return EmptyTargetDelegate.INSTANCE;
            }
            if (target instanceof PoolableViewTarget) {
                return new PoolableTargetDelegate(request, (PoolableViewTarget) target, this.referenceCounter, eventListener, this.logger);
            }
            return new InvalidatableTargetDelegate(request, target, this.referenceCounter, eventListener, this.logger);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final RequestDelegate createRequestDelegate(Request request, TargetDelegate targetDelegate, Lifecycle lifecycle, CoroutineDispatcher coroutineDispatcher, Deferred<?> deferred) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(targetDelegate, "targetDelegate");
        Intrinsics.checkParameterIsNotNull(lifecycle, "lifecycle");
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "mainDispatcher");
        Intrinsics.checkParameterIsNotNull(deferred, "deferred");
        if (request instanceof GetRequest) {
            RequestDelegate requestDelegate = EmptyRequestDelegate.INSTANCE;
            lifecycle.addObserver(requestDelegate);
            return requestDelegate;
        } else if (request instanceof LoadRequest) {
            Target target = request.getTarget();
            if (target instanceof ViewTarget) {
                RequestDelegate viewTargetRequestDelegate = new ViewTargetRequestDelegate(this.imageLoader, (LoadRequest) request, targetDelegate, lifecycle, coroutineDispatcher, deferred);
                lifecycle.addObserver(viewTargetRequestDelegate);
                Extensions.getRequestManager(((ViewTarget) target).getView()).setCurrentRequest((ViewTargetRequestDelegate) viewTargetRequestDelegate);
                return viewTargetRequestDelegate;
            }
            RequestDelegate baseRequestDelegate = new BaseRequestDelegate(lifecycle, coroutineDispatcher, deferred);
            lifecycle.addObserver(baseRequestDelegate);
            return baseRequestDelegate;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}

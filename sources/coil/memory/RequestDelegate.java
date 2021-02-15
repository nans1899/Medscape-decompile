package coil.memory;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0017J\b\u0010\u0005\u001a\u00020\u0004H\u0017\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcoil/memory/RequestDelegate;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "()V", "dispose", "", "onComplete", "Lcoil/memory/EmptyRequestDelegate;", "Lcoil/memory/BaseRequestDelegate;", "Lcoil/memory/ViewTargetRequestDelegate;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestDelegate.kt */
public abstract class RequestDelegate implements DefaultLifecycleObserver {
    public void dispose() {
    }

    public void onComplete() {
    }

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStart(this, lifecycleOwner);
    }

    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
    }

    private RequestDelegate() {
    }

    public /* synthetic */ RequestDelegate(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

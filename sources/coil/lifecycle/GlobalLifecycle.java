package coil.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcoil/lifecycle/GlobalLifecycle;", "Landroidx/lifecycle/Lifecycle;", "()V", "addObserver", "", "observer", "Landroidx/lifecycle/LifecycleObserver;", "getCurrentState", "Landroidx/lifecycle/Lifecycle$State;", "removeObserver", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: GlobalLifecycle.kt */
public final class GlobalLifecycle extends Lifecycle {
    public static final GlobalLifecycle INSTANCE = new GlobalLifecycle();

    public void addObserver(LifecycleObserver lifecycleObserver) {
        Intrinsics.checkParameterIsNotNull(lifecycleObserver, "observer");
    }

    public void removeObserver(LifecycleObserver lifecycleObserver) {
        Intrinsics.checkParameterIsNotNull(lifecycleObserver, "observer");
    }

    private GlobalLifecycle() {
    }

    public Lifecycle.State getCurrentState() {
        return Lifecycle.State.RESUMED;
    }
}

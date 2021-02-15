package coil.lifecycle;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import net.bytebuddy.implementation.MethodDelegation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00172\u00020\u00012\u00020\u0002:\u0001\u0017B\u0017\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\n\u0010\u0010\u001a\u00060\u000bj\u0002`\fH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0003\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u00060\u000bj\u0002`\f0\t0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcoil/lifecycle/LifecycleCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "delegate", "isStarted", "", "(Lkotlinx/coroutines/CoroutineDispatcher;Z)V", "queue", "Ljava/util/Queue;", "Lkotlin/Pair;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatch", "", "context", "block", "drainQueue", "isDispatchNeeded", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LifecycleCoroutineDispatcher.kt */
public final class LifecycleCoroutineDispatcher extends CoroutineDispatcher implements DefaultLifecycleObserver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final CoroutineDispatcher delegate;
    private boolean isStarted;
    private final Queue<Pair<CoroutineContext, Runnable>> queue;

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

    public /* synthetic */ LifecycleCoroutineDispatcher(CoroutineDispatcher coroutineDispatcher, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineDispatcher, z);
    }

    private LifecycleCoroutineDispatcher(CoroutineDispatcher coroutineDispatcher, boolean z) {
        this.delegate = coroutineDispatcher;
        this.isStarted = z;
        this.queue = new ArrayDeque();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Lcoil/lifecycle/LifecycleCoroutineDispatcher$Companion;", "", "()V", "create", "Lcoil/lifecycle/LifecycleCoroutineDispatcher;", "delegate", "Lkotlinx/coroutines/CoroutineDispatcher;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "createUnlessStarted", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: LifecycleCoroutineDispatcher.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LifecycleCoroutineDispatcher create(CoroutineDispatcher coroutineDispatcher, Lifecycle lifecycle) {
            Intrinsics.checkParameterIsNotNull(coroutineDispatcher, MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX);
            Intrinsics.checkParameterIsNotNull(lifecycle, "lifecycle");
            LifecycleCoroutineDispatcher lifecycleCoroutineDispatcher = new LifecycleCoroutineDispatcher(coroutineDispatcher, lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED), (DefaultConstructorMarker) null);
            lifecycle.addObserver(lifecycleCoroutineDispatcher);
            return lifecycleCoroutineDispatcher;
        }

        public final CoroutineDispatcher createUnlessStarted(CoroutineDispatcher coroutineDispatcher, Lifecycle lifecycle) {
            Intrinsics.checkParameterIsNotNull(coroutineDispatcher, MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX);
            Intrinsics.checkParameterIsNotNull(lifecycle, "lifecycle");
            boolean isAtLeast = lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED);
            if (isAtLeast) {
                return coroutineDispatcher;
            }
            LifecycleCoroutineDispatcher lifecycleCoroutineDispatcher = new LifecycleCoroutineDispatcher(coroutineDispatcher, isAtLeast, (DefaultConstructorMarker) null);
            lifecycle.addObserver(lifecycleCoroutineDispatcher);
            return lifecycleCoroutineDispatcher;
        }
    }

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return this.delegate.isDispatchNeeded(coroutineContext);
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        if (this.isStarted) {
            this.delegate.dispatch(coroutineContext, runnable);
        } else {
            this.queue.offer(TuplesKt.to(coroutineContext, runnable));
        }
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        this.isStarted = true;
        drainQueue();
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        this.isStarted = false;
    }

    private final void drainQueue() {
        if (!this.queue.isEmpty()) {
            Iterator it = this.queue.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                it.remove();
                this.delegate.dispatch((CoroutineContext) pair.component1(), (Runnable) pair.component2());
            }
        }
    }
}

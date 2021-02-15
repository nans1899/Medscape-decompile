package coil.util;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import coil.RealImageLoader;
import coil.network.NetworkObserver;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\tH\u0016J\b\u0010\u001b\u001a\u00020\u0017H\u0016J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0017R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R*\u0010\u0003\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00040\u00040\u000b8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcoil/util/SystemCallbacks;", "Landroid/content/ComponentCallbacks2;", "Lcoil/network/NetworkObserver$Listener;", "imageLoader", "Lcoil/RealImageLoader;", "context", "Landroid/content/Context;", "(Lcoil/RealImageLoader;Landroid/content/Context;)V", "_isOnline", "", "_isShutdown", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "imageLoader$annotations", "()V", "getImageLoader$coil_base_release", "()Ljava/lang/ref/WeakReference;", "isOnline", "()Z", "isShutdown", "networkObserver", "Lcoil/network/NetworkObserver;", "onConfigurationChanged", "", "newConfig", "Landroid/content/res/Configuration;", "onConnectivityChange", "onLowMemory", "onTrimMemory", "level", "", "shutdown", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SystemCallbacks.kt */
public final class SystemCallbacks implements ComponentCallbacks2, NetworkObserver.Listener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String OFFLINE = "OFFLINE";
    private static final String ONLINE = "ONLINE";
    private static final String TAG = "NetworkObserver";
    private boolean _isOnline;
    private boolean _isShutdown;
    private final Context context;
    private final WeakReference<RealImageLoader> imageLoader;
    private final NetworkObserver networkObserver;

    public static /* synthetic */ void imageLoader$annotations() {
    }

    public SystemCallbacks(RealImageLoader realImageLoader, Context context2) {
        Intrinsics.checkParameterIsNotNull(realImageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        this.imageLoader = new WeakReference<>(realImageLoader);
        NetworkObserver invoke = NetworkObserver.Companion.invoke(this.context, this, realImageLoader.getLogger$coil_base_release());
        this.networkObserver = invoke;
        this._isOnline = invoke.isOnline();
        this.context.registerComponentCallbacks(this);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcoil/util/SystemCallbacks$Companion;", "", "()V", "OFFLINE", "", "ONLINE", "TAG", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: SystemCallbacks.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final WeakReference<RealImageLoader> getImageLoader$coil_base_release() {
        return this.imageLoader;
    }

    public final boolean isOnline() {
        return this._isOnline;
    }

    public final boolean isShutdown() {
        return this._isShutdown;
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        if (((RealImageLoader) this.imageLoader.get()) == null) {
            shutdown();
            Unit unit = Unit.INSTANCE;
        }
    }

    public void onTrimMemory(int i) {
        RealImageLoader realImageLoader = (RealImageLoader) this.imageLoader.get();
        if (realImageLoader != null) {
            realImageLoader.onTrimMemory(i);
        } else {
            shutdown();
        }
    }

    public void onLowMemory() {
        onTrimMemory(80);
    }

    public void onConnectivityChange(boolean z) {
        RealImageLoader realImageLoader = (RealImageLoader) this.imageLoader.get();
        if (realImageLoader == null) {
            shutdown();
            return;
        }
        this._isOnline = z;
        Logger logger$coil_base_release = realImageLoader.getLogger$coil_base_release();
        if (logger$coil_base_release != null && logger$coil_base_release.getLevel() <= 4) {
            logger$coil_base_release.log(TAG, 4, z ? ONLINE : OFFLINE, (Throwable) null);
        }
    }

    public final void shutdown() {
        if (!this._isShutdown) {
            this._isShutdown = true;
            this.context.unregisterComponentCallbacks(this);
            this.networkObserver.shutdown();
        }
    }
}

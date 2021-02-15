package coil.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import coil.util.Logger;
import coil.util.Logs;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b`\u0018\u0000 \u00072\u00020\u0001:\u0002\u0007\bJ\b\u0010\u0005\u001a\u00020\u0006H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\t"}, d2 = {"Lcoil/network/NetworkObserver;", "", "isOnline", "", "()Z", "shutdown", "", "Companion", "Listener", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NetworkObserver.kt */
public interface NetworkObserver {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"}, d2 = {"Lcoil/network/NetworkObserver$Listener;", "", "onConnectivityChange", "", "isOnline", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NetworkObserver.kt */
    public interface Listener {
        void onConnectivityChange(boolean z);
    }

    boolean isOnline();

    void shutdown();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcoil/network/NetworkObserver$Companion;", "", "()V", "TAG", "", "invoke", "Lcoil/network/NetworkObserver;", "context", "Landroid/content/Context;", "listener", "Lcoil/network/NetworkObserver$Listener;", "logger", "Lcoil/util/Logger;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NetworkObserver.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String TAG = "NetworkObserver";

        private Companion() {
        }

        public final NetworkObserver invoke(Context context, Listener listener, Logger logger) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            ConnectivityManager connectivityManager = (ConnectivityManager) ContextCompat.getSystemService(context, ConnectivityManager.class);
            if (connectivityManager != null) {
                if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0) {
                    try {
                        if (Build.VERSION.SDK_INT >= 21) {
                            return new NetworkObserverApi21(connectivityManager, listener);
                        }
                        return new NetworkObserverApi14(context, connectivityManager, listener);
                    } catch (Exception e) {
                        if (logger != null) {
                            Logs.log(logger, TAG, new RuntimeException("Failed to register network observer.", e));
                        }
                        return EmptyNetworkObserver.INSTANCE;
                    }
                }
            }
            if (logger != null && logger.getLevel() <= 5) {
                logger.log(TAG, 5, "Unable to register network observer.", (Throwable) null);
            }
            return EmptyNetworkObserver.INSTANCE;
        }
    }
}

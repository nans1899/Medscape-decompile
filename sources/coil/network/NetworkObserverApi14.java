package coil.network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import coil.network.NetworkObserver;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\n\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0010\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcoil/network/NetworkObserverApi14;", "Lcoil/network/NetworkObserver;", "context", "Landroid/content/Context;", "connectivityManager", "Landroid/net/ConnectivityManager;", "listener", "Lcoil/network/NetworkObserver$Listener;", "(Landroid/content/Context;Landroid/net/ConnectivityManager;Lcoil/network/NetworkObserver$Listener;)V", "connectionReceiver", "coil/network/NetworkObserverApi14$connectionReceiver$1", "Lcoil/network/NetworkObserverApi14$connectionReceiver$1;", "isOnline", "", "()Z", "shutdown", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NetworkObserver.kt */
final class NetworkObserverApi14 implements NetworkObserver {
    private final NetworkObserverApi14$connectionReceiver$1 connectionReceiver;
    private final ConnectivityManager connectivityManager;
    private final Context context;

    public NetworkObserverApi14(Context context2, ConnectivityManager connectivityManager2, NetworkObserver.Listener listener) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(connectivityManager2, "connectivityManager");
        Intrinsics.checkParameterIsNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.context = context2;
        this.connectivityManager = connectivityManager2;
        NetworkObserverApi14$connectionReceiver$1 networkObserverApi14$connectionReceiver$1 = new NetworkObserverApi14$connectionReceiver$1(this, listener);
        this.connectionReceiver = networkObserverApi14$connectionReceiver$1;
        this.context.registerReceiver(networkObserverApi14$connectionReceiver$1, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void shutdown() {
        this.context.unregisterReceiver(this.connectionReceiver);
    }
}

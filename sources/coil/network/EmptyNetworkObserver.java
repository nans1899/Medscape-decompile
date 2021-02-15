package coil.network;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005¨\u0006\b"}, d2 = {"Lcoil/network/EmptyNetworkObserver;", "Lcoil/network/NetworkObserver;", "()V", "isOnline", "", "()Z", "shutdown", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NetworkObserver.kt */
final class EmptyNetworkObserver implements NetworkObserver {
    public static final EmptyNetworkObserver INSTANCE = new EmptyNetworkObserver();
    private static final boolean isOnline = true;

    public void shutdown() {
    }

    private EmptyNetworkObserver() {
    }

    public boolean isOnline() {
        return isOnline;
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzta implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zzsw zzbuz;

    zzta(zzsw zzsw) {
        this.zzbuz = zzsw;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        synchronized (this.zzbuz.lock) {
            zztf unused = this.zzbuz.zzbvc = null;
            if (this.zzbuz.zzbvb != null) {
                zztb unused2 = this.zzbuz.zzbvb = null;
            }
            this.zzbuz.lock.notifyAll();
        }
    }
}

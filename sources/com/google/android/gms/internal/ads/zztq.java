package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zztq implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zztk zzbvl;
    private final /* synthetic */ zzbcg zzbvs;

    zztq(zztk zztk, zzbcg zzbcg) {
        this.zzbvl = zztk;
        this.zzbvs = zzbcg;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        synchronized (this.zzbvl.lock) {
            this.zzbvs.setException(new RuntimeException("Connection failed."));
        }
    }
}

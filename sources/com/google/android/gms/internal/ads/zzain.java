package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzain implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zzbcg zzbvs;

    zzain(zzaik zzaik, zzbcg zzbcg) {
        this.zzbvs = zzbcg;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbvs.setException(new RuntimeException("Connection failed."));
    }
}

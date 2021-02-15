package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaio implements BaseGmsClient.BaseConnectionCallbacks {
    private final /* synthetic */ zzbcg zzbvs;
    private final /* synthetic */ zzaik zzdfy;

    zzaio(zzaik zzaik, zzbcg zzbcg) {
        this.zzdfy = zzaik;
        this.zzbvs = zzbcg;
    }

    public final void onConnected(Bundle bundle) {
        try {
            this.zzbvs.set(this.zzdfy.zzdfx.zzsz());
        } catch (DeadObjectException e) {
            this.zzbvs.setException(e);
        }
    }

    public final void onConnectionSuspended(int i) {
        zzbcg zzbcg = this.zzbvs;
        StringBuilder sb = new StringBuilder(34);
        sb.append("onConnectionSuspended: ");
        sb.append(i);
        zzbcg.setException(new RuntimeException(sb.toString()));
    }
}

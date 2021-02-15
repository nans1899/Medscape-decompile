package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zztm implements BaseGmsClient.BaseConnectionCallbacks {
    final /* synthetic */ zztk zzbvl;
    private final /* synthetic */ zzte zzbvr;
    private final /* synthetic */ zzbcg zzbvs;

    zztm(zztk zztk, zzte zzte, zzbcg zzbcg) {
        this.zzbvl = zztk;
        this.zzbvr = zzte;
        this.zzbvs = zzbcg;
    }

    public final void onConnectionSuspended(int i) {
    }

    public final void onConnected(Bundle bundle) {
        synchronized (this.zzbvl.lock) {
            if (!this.zzbvl.zzbvm) {
                boolean unused = this.zzbvl.zzbvm = true;
                zztb zzd = this.zzbvl.zzbvb;
                if (zzd != null) {
                    this.zzbvs.addListener(new zzto(this.zzbvs, zzbbz.zzeep.zzg(new zztl(this, zzd, this.zzbvr, this.zzbvs))), zzbbz.zzeeu);
                }
            }
        }
    }
}

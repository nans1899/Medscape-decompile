package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcaj implements zzcak {
    private final List<String> zzdsp;
    private final zzdst zzfnf;
    private boolean zzfwn;

    public zzcaj(zzdnv zzdnv, zzdst zzdst) {
        this.zzdsp = zzdnv.zzdsp;
        this.zzfnf = zzdst;
    }

    public final void zzakv() {
        if (!this.zzfwn) {
            this.zzfnf.zzj(this.zzdsp);
            this.zzfwn = true;
        }
    }
}

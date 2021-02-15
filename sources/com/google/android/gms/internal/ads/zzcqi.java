package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcqi implements zzcqj {
    private final zzdzb zzgcz;
    private final Map<String, zzeph<zzcqj>> zzgkt;
    /* access modifiers changed from: private */
    public final zzbwr zzgku;

    public zzcqi(Map<String, zzeph<zzcqj>> map, zzdzb zzdzb, zzbwr zzbwr) {
        this.zzgkt = map;
        this.zzgcz = zzdzb;
        this.zzgku = zzbwr;
    }

    public final zzdzc<zzdog> zzh(zzatc zzatc) {
        this.zzgku.zzd(zzatc);
        zzdzc<zzdog> immediateFailedFuture = zzdyq.immediateFailedFuture(new zzcof(zzdpg.NO_FILL));
        for (String trim : ((String) zzwm.zzpx().zzd(zzabb.zzcyk)).split(",")) {
            zzeph zzeph = this.zzgkt.get(trim.trim());
            if (zzeph != null) {
                immediateFailedFuture = zzdyq.zzb(immediateFailedFuture, zzcof.class, new zzcqh(zzeph, zzatc), this.zzgcz);
            }
        }
        zzdyq.zza(immediateFailedFuture, new zzcqk(this), zzbbz.zzeeu);
        return immediateFailedFuture;
    }
}

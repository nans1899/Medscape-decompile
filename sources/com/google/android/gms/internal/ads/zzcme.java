package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcme implements zzdso {
    private zzts zzggj;
    private Map<zzdsf, zzcmg> zzggs;

    zzcme(zzts zzts, Map<zzdsf, zzcmg> map) {
        this.zzggs = map;
        this.zzggj = zzts;
    }

    public final void zza(zzdsf zzdsf, String str) {
    }

    public final void zzb(zzdsf zzdsf, String str) {
        if (this.zzggs.containsKey(zzdsf)) {
            this.zzggj.zza(this.zzggs.get(zzdsf).zzggu);
        }
    }

    public final void zza(zzdsf zzdsf, String str, Throwable th) {
        if (this.zzggs.containsKey(zzdsf)) {
            this.zzggj.zza(this.zzggs.get(zzdsf).zzggw);
        }
    }

    public final void zzc(zzdsf zzdsf, String str) {
        if (this.zzggs.containsKey(zzdsf)) {
            this.zzggj.zza(this.zzggs.get(zzdsf).zzggv);
        }
    }
}

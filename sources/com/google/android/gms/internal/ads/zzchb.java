package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzchb implements zzdya {
    private final zzdzc zzgdq;

    zzchb(zzdzc zzdzc) {
        this.zzgdq = zzdzc;
    }

    public final zzdzc zzf(Object obj) {
        zzdzc zzdzc = this.zzgdq;
        if (obj != null) {
            return zzdzc;
        }
        return zzdyq.immediateFailedFuture(new zzcwn(zzdpg.INTERNAL_ERROR, "Retrieve required value in native ad response failed."));
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcgz implements zzdya {
    private final zzdzc zzgdq;

    zzcgz(zzdzc zzdzc) {
        this.zzgdq = zzdzc;
    }

    public final zzdzc zzf(Object obj) {
        zzdzc zzdzc = this.zzgdq;
        zzbgj zzbgj = (zzbgj) obj;
        if (zzbgj != null && zzbgj.zzzs() != null) {
            return zzdzc;
        }
        throw new zzcwn(zzdpg.INTERNAL_ERROR, "Retrieve video view in instream ad response failed.");
    }
}

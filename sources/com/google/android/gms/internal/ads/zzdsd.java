package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdsd implements zzdyr<O> {
    private final /* synthetic */ zzdrr zzhki;
    private final /* synthetic */ zzdsa zzhkj;

    zzdsd(zzdsa zzdsa, zzdrr zzdrr) {
        this.zzhkj = zzdsa;
        this.zzhki = zzdrr;
    }

    public final void onSuccess(O o) {
        this.zzhkj.zzhkd.zzhkb.zzc(this.zzhki);
    }

    public final void zzb(Throwable th) {
        this.zzhkj.zzhkd.zzhkb.zza(this.zzhki, th);
    }
}

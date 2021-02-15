package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdrw {
    private final E zzhjx;
    private final List<zzdzc<?>> zzhkc;
    private final /* synthetic */ zzdru zzhkd;

    private zzdrw(zzdru zzdru, E e, List<zzdzc<?>> list) {
        this.zzhkd = zzdru;
        this.zzhjx = e;
        this.zzhkc = list;
    }

    public final <O> zzdsa<O> zzb(Callable<O> callable) {
        zzdyv<V> zzk = zzdyq.zzk(this.zzhkc);
        zzdzc<C> zza = zzk.zza(zzdrv.zzgww, zzbbz.zzeeu);
        zzdru zzdru = this.zzhkd;
        return new zzdsa(zzdru, this.zzhjx, (String) null, zza, this.zzhkc, zzk.zza(callable, zzdru.zzgcz), (zzdrt) null);
    }
}

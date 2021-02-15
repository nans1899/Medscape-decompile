package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdry {
    private final E zzhjx;
    private final /* synthetic */ zzdru zzhkd;

    private zzdry(zzdru zzdru, E e) {
        this.zzhkd = zzdru;
        this.zzhjx = e;
    }

    public final <O> zzdsa<O> zze(zzdzc<O> zzdzc) {
        return new zzdsa(this.zzhkd, this.zzhjx, (String) null, zzdru.zzhka, Collections.emptyList(), zzdzc, (zzdrt) null);
    }

    public final <O> zzdsa<O> zzc(Callable<O> callable) {
        return zza(callable, this.zzhkd.zzgcz);
    }

    private final <O> zzdsa<O> zza(Callable<O> callable, zzdzb zzdzb) {
        return new zzdsa(this.zzhkd, this.zzhjx, (String) null, zzdru.zzhka, Collections.emptyList(), zzdzb.zze(callable), (zzdrt) null);
    }

    public final zzdsa<?> zza(zzdrs zzdrs, zzdzb zzdzb) {
        return zza(new zzdrx(zzdrs), zzdzb);
    }
}

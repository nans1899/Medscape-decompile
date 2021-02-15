package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdsa<O> {
    private final E zzhjx;
    private final String zzhjy;
    private final List<zzdzc<?>> zzhkc;
    final /* synthetic */ zzdru zzhkd;
    private final zzdzc<?> zzhkg;
    private final zzdzc<O> zzhkh;

    private zzdsa(zzdru zzdru, E e, String str, zzdzc<?> zzdzc, List<zzdzc<?>> list, zzdzc<O> zzdzc2) {
        this.zzhkd = zzdru;
        this.zzhjx = e;
        this.zzhjy = str;
        this.zzhkg = zzdzc;
        this.zzhkc = list;
        this.zzhkh = zzdzc2;
    }

    public final zzdsa<O> zzgx(String str) {
        return new zzdsa(this.zzhkd, this.zzhjx, str, this.zzhkg, this.zzhkc, this.zzhkh);
    }

    public final <O2> zzdsa<O2> zzb(zzdrp<O, O2> zzdrp) {
        return zza(new zzdrz(zzdrp));
    }

    public final <O2> zzdsa<O2> zza(zzdya<O, O2> zzdya) {
        return zza(zzdya, (Executor) this.zzhkd.zzgcz);
    }

    private final <O2> zzdsa<O2> zza(zzdya<O, O2> zzdya, Executor executor) {
        return new zzdsa(this.zzhkd, this.zzhjx, this.zzhjy, this.zzhkg, this.zzhkc, zzdyq.zzb(this.zzhkh, zzdya, executor));
    }

    public final <O2> zzdsa<O2> zze(zzdzc<O2> zzdzc) {
        return zza(new zzdsc(zzdzc), (Executor) zzbbz.zzeeu);
    }

    public final <T extends Throwable> zzdsa<O> zza(Class<T> cls, zzdrp<T, O> zzdrp) {
        return zza(cls, new zzdsb(zzdrp));
    }

    public final <T extends Throwable> zzdsa<O> zza(Class<T> cls, zzdya<T, O> zzdya) {
        zzdru zzdru = this.zzhkd;
        return new zzdsa(zzdru, this.zzhjx, this.zzhjy, this.zzhkg, this.zzhkc, zzdyq.zzb(this.zzhkh, cls, zzdya, zzdru.zzgcz));
    }

    public final zzdsa<O> zza(long j, TimeUnit timeUnit) {
        zzdru zzdru = this.zzhkd;
        return new zzdsa(zzdru, this.zzhjx, this.zzhjy, this.zzhkg, this.zzhkc, zzdyq.zza(this.zzhkh, j, timeUnit, zzdru.zzfoz));
    }

    public final zzdrr<E, O> zzavs() {
        E e = this.zzhjx;
        String str = this.zzhjy;
        if (str == null) {
            str = this.zzhkd.zzv(e);
        }
        zzdrr<E, O> zzdrr = new zzdrr<>(e, str, this.zzhkh);
        this.zzhkd.zzhkb.zza(zzdrr);
        this.zzhkg.addListener(new zzdse(this, zzdrr), zzbbz.zzeeu);
        zzdyq.zza(zzdrr, new zzdsd(this, zzdrr), zzbbz.zzeeu);
        return zzdrr;
    }

    public final zzdsa<O> zzw(E e) {
        return this.zzhkd.zza(e, zzavs());
    }

    /* synthetic */ zzdsa(zzdru zzdru, Object obj, String str, zzdzc zzdzc, List list, zzdzc zzdzc2, zzdrt zzdrt) {
        this(zzdru, obj, (String) null, zzdzc, list, zzdzc2);
    }
}

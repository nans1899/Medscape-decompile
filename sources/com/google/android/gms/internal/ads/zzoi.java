package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzoi {
    public final zznp zzbhw;
    public final zzod zzbhx;
    public final Object zzbhy;
    public final zzhz[] zzbhz;

    public zzoi(zznp zznp, zzod zzod, Object obj, zzhz[] zzhzArr) {
        this.zzbhw = zznp;
        this.zzbhx = zzod;
        this.zzbhy = obj;
        this.zzbhz = zzhzArr;
    }

    public final boolean zza(zzoi zzoi, int i) {
        if (zzoi != null && zzpq.zza(this.zzbhx.zzbg(i), zzoi.zzbhx.zzbg(i)) && zzpq.zza(this.zzbhz[i], zzoi.zzbhz[i])) {
            return true;
        }
        return false;
    }
}

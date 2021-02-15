package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbre {
    private final String zzcib;
    private final zzdog zzevd;
    private final zzdnv zzfmx;

    public zzbre(zzdog zzdog, zzdnv zzdnv, String str) {
        this.zzevd = zzdog;
        this.zzfmx = zzdnv;
        this.zzcib = str == null ? "com.google.ads.mediation.admob.AdMobAdapter" : str;
    }

    public final zzdog zzajc() {
        return this.zzevd;
    }

    public final zzdnv zzajd() {
        return this.zzfmx;
    }

    public final zzdnw zzaje() {
        return this.zzevd.zzhew.zzeou;
    }

    public final String zzajf() {
        return this.zzcib;
    }
}

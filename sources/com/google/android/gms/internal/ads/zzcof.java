package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzcof extends Exception {
    private final zzdpg zzgiy;

    public zzcof(zzdpg zzdpg) {
        this.zzgiy = zzdpg;
    }

    public zzcof(zzdpg zzdpg, String str) {
        super(str);
        this.zzgiy = zzdpg;
    }

    public zzcof(zzdpg zzdpg, String str, Throwable th) {
        super(str, th);
        this.zzgiy = zzdpg;
    }

    public final zzdpg zzapr() {
        return this.zzgiy;
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdac {
    private final zzcep zzgtr;
    private final zzczs zzgts;
    private final zzbui zzgtt;

    public zzdac(zzcep zzcep) {
        zzczs zzczs = new zzczs();
        this.zzgts = zzczs;
        this.zzgtr = zzcep;
        this.zzgtt = new zzdab(zzczs, zzcep.zzamt());
    }

    public final void zzc(zzwt zzwt) {
        this.zzgts.zzc(zzwt);
    }

    public final zzccw zzaqw() {
        return new zzccw(this.zzgtr, this.zzgts.zzaqt());
    }

    public final zzczs zzaqx() {
        return this.zzgts;
    }

    public final zzbuh zzaqy() {
        return this.zzgts;
    }

    public final zzbvs zzaqz() {
        return this.zzgts;
    }

    public final zzbui zzara() {
        return this.zzgtt;
    }

    public final zzbuz zzarb() {
        return this.zzgts;
    }

    public final zzux zzarc() {
        return this.zzgts;
    }
}

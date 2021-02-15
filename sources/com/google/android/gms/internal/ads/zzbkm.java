package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbkm {
    private zzbky zzesw;
    private zzbja zzesx;
    private zzdsp zzfjy;
    private zzblh zzfjz;
    private zzdpf zzfka;

    private zzbkm() {
    }

    public final zzbkm zzc(zzbja zzbja) {
        this.zzesx = (zzbja) zzepe.checkNotNull(zzbja);
        return this;
    }

    public final zzbkm zza(zzbky zzbky) {
        this.zzesw = (zzbky) zzepe.checkNotNull(zzbky);
        return this;
    }

    public final zzbix zzagf() {
        zzepe.zza(this.zzesx, zzbja.class);
        zzepe.zza(this.zzesw, zzbky.class);
        if (this.zzfjy == null) {
            this.zzfjy = new zzdsp();
        }
        if (this.zzfjz == null) {
            this.zzfjz = new zzblh();
        }
        if (this.zzfka == null) {
            this.zzfka = new zzdpf();
        }
        return new zzbjv(this.zzesx, this.zzesw, this.zzfjy, this.zzfjz, this.zzfka);
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbka implements zzboe {
    private zzbtp zzevb;
    private zzdog zzevd;
    private zzdmd zzevf;
    private zzdlf zzevg;
    private final /* synthetic */ zzbjv zzfbq;
    private zzbys zzfbr;

    private zzbka(zzbjv zzbjv) {
        this.zzfbq = zzbjv;
    }

    @Deprecated
    public final /* synthetic */ zzboe zza(zzbol zzbol) {
        zzepe.checkNotNull(zzbol);
        return this;
    }

    public final /* synthetic */ zzboe zzb(zzbtp zzbtp) {
        this.zzevb = (zzbtp) zzepe.checkNotNull(zzbtp);
        return this;
    }

    public final /* synthetic */ zzboe zzb(zzbys zzbys) {
        this.zzfbr = (zzbys) zzepe.checkNotNull(zzbys);
        return this;
    }

    public final /* synthetic */ Object zzaey() {
        zzepe.zza(this.zzfbr, zzbys.class);
        zzepe.zza(this.zzevb, zzbtp.class);
        return new zzbkd(this.zzfbq, new zzbrr(), new zzdph(), new zzbsz(), new zzcni(), this.zzfbr, this.zzevb, new zzdpp(), this.zzevd, this.zzevf, this.zzevg);
    }

    public final /* synthetic */ zzbtq zza(zzdlf zzdlf) {
        this.zzevg = zzdlf;
        return this;
    }

    public final /* synthetic */ zzbtq zza(zzdmd zzdmd) {
        this.zzevf = zzdmd;
        return this;
    }

    public final /* synthetic */ zzbtq zza(zzdog zzdog) {
        this.zzevd = zzdog;
        return this;
    }
}

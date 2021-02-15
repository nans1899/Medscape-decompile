package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbks implements zzcjm {
    private zzbtp zzevb;
    private zzdog zzevd;
    private zzdmd zzevf;
    private zzdlf zzevg;
    private final /* synthetic */ zzbjv zzfbq;
    private zzbys zzfbr;

    private zzbks(zzbjv zzbjv) {
        this.zzfbq = zzbjv;
    }

    /* renamed from: zzagl */
    public final zzcjj zzaey() {
        zzepe.zza(this.zzfbr, zzbys.class);
        zzepe.zza(this.zzevb, zzbtp.class);
        return new zzbkv(this.zzfbq, new zzbrr(), new zzdph(), new zzbsz(), new zzcni(), this.zzfbr, this.zzevb, new zzdpp(), this.zzevd, this.zzevf, this.zzevg);
    }

    public final /* synthetic */ zzcjm zzf(zzbtp zzbtp) {
        this.zzevb = (zzbtp) zzepe.checkNotNull(zzbtp);
        return this;
    }

    public final /* synthetic */ zzcjm zzf(zzbys zzbys) {
        this.zzfbr = (zzbys) zzepe.checkNotNull(zzbys);
        return this;
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

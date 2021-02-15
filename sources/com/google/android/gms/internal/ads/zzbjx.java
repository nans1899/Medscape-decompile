package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbjx implements zzccz {
    private zzbtp zzevb;
    private zzdog zzevd;
    private zzccw zzeve;
    private zzdmd zzevf;
    private zzdlf zzevg;
    private final /* synthetic */ zzbjv zzfbq;
    private zzbys zzfbr;

    private zzbjx(zzbjv zzbjv) {
        this.zzfbq = zzbjv;
    }

    /* renamed from: zzaex */
    public final zzcda zzaey() {
        zzepe.zza(this.zzfbr, zzbys.class);
        zzepe.zza(this.zzevb, zzbtp.class);
        zzepe.zza(this.zzeve, zzccw.class);
        return new zzbjw(this.zzfbq, this.zzeve, new zzbrr(), new zzdph(), new zzbsz(), new zzcni(), this.zzfbr, this.zzevb, new zzdpp(), this.zzevd, this.zzevf, this.zzevg);
    }

    public final /* synthetic */ zzccz zza(zzccw zzccw) {
        this.zzeve = (zzccw) zzepe.checkNotNull(zzccw);
        return this;
    }

    public final /* synthetic */ zzccz zza(zzbtp zzbtp) {
        this.zzevb = (zzbtp) zzepe.checkNotNull(zzbtp);
        return this;
    }

    public final /* synthetic */ zzccz zza(zzbys zzbys) {
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

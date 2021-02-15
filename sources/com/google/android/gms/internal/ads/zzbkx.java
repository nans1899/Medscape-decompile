package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbkx implements zzdan {
    private zzbtp zzevb;
    private final /* synthetic */ zzbjv zzfbq;
    private zzdbd zzflx;

    private zzbkx(zzbjv zzbjv) {
        this.zzfbq = zzbjv;
    }

    public final zzdao zzagt() {
        zzepe.zza(this.zzevb, zzbtp.class);
        zzepe.zza(this.zzflx, zzdbd.class);
        return new zzbkw(this.zzfbq, this.zzflx, new zzbrr(), new zzcni(), this.zzevb, new zzdpp(), (zzdmd) null, (zzdlf) null, (zzbju) null);
    }

    @Deprecated
    public final /* synthetic */ zzdan zzg(zzbys zzbys) {
        zzepe.checkNotNull(zzbys);
        return this;
    }

    public final /* synthetic */ zzdan zza(zzdbd zzdbd) {
        this.zzflx = (zzdbd) zzepe.checkNotNull(zzdbd);
        return this;
    }

    public final /* synthetic */ zzdan zzg(zzbtp zzbtp) {
        this.zzevb = (zzbtp) zzepe.checkNotNull(zzbtp);
        return this;
    }
}

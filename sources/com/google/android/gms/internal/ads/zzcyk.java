package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyk<AdT> implements zzcta<AdT> {
    private final zzdsi zzfsl;
    private final zzdzb zzgrc;
    private final zzaby zzgrj;
    /* access modifiers changed from: private */
    public final zzcyl<AdT> zzgrs;

    public zzcyk(zzdsi zzdsi, zzdzb zzdzb, zzaby zzaby, zzcyl<AdT> zzcyl) {
        this.zzfsl = zzdsi;
        this.zzgrc = zzdzb;
        this.zzgrj = zzaby;
        this.zzgrs = zzcyl;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (this.zzgrj == null || zzdnv.zzhdr == null || zzdnv.zzhdr.zzdpp == null) ? false : true;
    }

    public final zzdzc<AdT> zzb(zzdog zzdog, zzdnv zzdnv) {
        zzbcg zzbcg = new zzbcg();
        zzcys zzcys = new zzcys();
        zzcys.zza(new zzcym(this, zzbcg, zzdog, zzdnv, zzcys));
        return this.zzfsl.zzu(zzdsf.CUSTOM_RENDER_SYN).zza((zzdrs) new zzcyj(this, new zzabr(zzcys, zzdnv.zzhdr.zzdpn, zzdnv.zzhdr.zzdpp)), this.zzgrc).zzw(zzdsf.CUSTOM_RENDER_ACK).zze(zzbcg).zzavs();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzabr zzabr) throws Exception {
        this.zzgrj.zza(zzabr);
    }
}

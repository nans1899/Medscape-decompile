package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdnc implements zzdyr<zzcjg> {
    private final /* synthetic */ zzdag zzgty;
    private final /* synthetic */ zzdnh zzhcs;
    final /* synthetic */ zzdnb zzhct;

    zzdnc(zzdnb zzdnb, zzdag zzdag, zzdnh zzdnh) {
        this.zzhct = zzdnb;
        this.zzgty = zzdag;
        this.zzhcs = zzdnh;
    }

    public final void zzb(Throwable th) {
        zzva zzva;
        zzcjj zzcjj = (zzcjj) this.zzhct.zzhaj.zzasv();
        if (zzcjj == null) {
            zzva = zzdpe.zza(th, (zzctd) null);
        } else {
            zzva = zzcjj.zzaev().zze(th);
        }
        synchronized (this.zzhct) {
            if (zzcjj != null) {
                zzcjj.zzaew().zzk(zzva);
                if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyf)).booleanValue()) {
                    this.zzhct.zzfoc.execute(new zzdne(this, zzva));
                }
            } else {
                this.zzhct.zzhcq.zzk(zzva);
                this.zzhct.zze(this.zzhcs).zzagl().zzaev().zzaji().zzake();
            }
            zzdox.zza(zzva.errorCode, th, "RewardedAdLoader.onFailure");
            this.zzgty.zzaqv();
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzcjg zzcjg = (zzcjg) obj;
        synchronized (this.zzhct) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyf)).booleanValue()) {
                zzcjg.zzaiz().zza(this.zzhct.zzhcq);
            }
            this.zzgty.onSuccess(zzcjg);
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyf)).booleanValue()) {
                this.zzhct.zzfoc.execute(new zzdnf(this));
            }
            this.zzhct.zzhcq.onAdMetadataChanged();
        }
    }
}

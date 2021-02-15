package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdkb implements zzdyr<AppOpenAd> {
    private final /* synthetic */ zzdag zzgty;
    private final /* synthetic */ zzdkd zzham;
    final /* synthetic */ zzdjw zzhan;

    zzdkb(zzdjw zzdjw, zzdag zzdag, zzdkd zzdkd) {
        this.zzhan = zzdjw;
        this.zzgty = zzdag;
        this.zzham = zzdkd;
    }

    public final void zzb(Throwable th) {
        zzva zzva;
        zzbnw zzbnw = (zzbnw) this.zzhan.zzhaj.zzasv();
        if (zzbnw == null) {
            zzva = zzdpe.zza(th, (zzctd) null);
        } else {
            zzva = zzbnw.zzaev().zze(th);
        }
        synchronized (this.zzhan) {
            zzdzc unused = this.zzhan.zzgsx = null;
            if (zzbnw != null) {
                zzbnw.zzaew().zzk(zzva);
                if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyg)).booleanValue()) {
                    this.zzhan.zzfoc.execute(new zzdka(this, zzva));
                }
            } else {
                this.zzhan.zzhai.zzk(zzva);
                ((zzbnw) this.zzhan.zzb((zzdlu) this.zzham).zzaey()).zzaev().zzaji().zzake();
            }
            zzdox.zza(zzva.errorCode, th, "AppOpenAdLoader.onFailure");
            this.zzgty.zzaqv();
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzbqo zzbqo = (zzbqo) obj;
        synchronized (this.zzhan) {
            zzdzc unused = this.zzhan.zzgsx = null;
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyg)).booleanValue()) {
                zzbqo.zzaiz().zza(this.zzhan.zzhai);
            }
            this.zzgty.onSuccess(zzbqo);
        }
    }
}

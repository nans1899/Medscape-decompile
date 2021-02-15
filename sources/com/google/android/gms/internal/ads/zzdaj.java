package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdaj implements zzdyr<zzbqo> {
    private final /* synthetic */ zzdag zzgty;
    private final /* synthetic */ zzcda zzgtz;
    final /* synthetic */ zzdai zzgua;

    zzdaj(zzdai zzdai, zzdag zzdag, zzcda zzcda) {
        this.zzgua = zzdai;
        this.zzgty = zzdag;
        this.zzgtz = zzcda;
    }

    public final void zzb(Throwable th) {
        zzva zze = this.zzgtz.zzaev().zze(th);
        this.zzgtz.zzaew().zzk(zze);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyi)).booleanValue()) {
            this.zzgua.zzgpt.zzadi().execute(new zzdal(this, zze));
        }
        zzdox.zza(zze.errorCode, th, "NativeAdLoader.onFailure");
        this.zzgty.zzaqv();
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzbqo zzbqo = (zzbqo) obj;
        synchronized (this.zzgua) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyi)).booleanValue()) {
                zzbqo.zzaiz().zza(this.zzgua.zzgtw.zzaqx());
            }
            this.zzgty.onSuccess(zzbqo);
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyi)).booleanValue()) {
                this.zzgua.zzgpt.zzadi().execute(new zzdam(this));
            }
        }
    }
}

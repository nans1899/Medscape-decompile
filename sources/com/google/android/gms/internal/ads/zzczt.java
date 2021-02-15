package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzczt implements zzdyr<zzcbe> {
    private final /* synthetic */ zzcce zzgti;
    final /* synthetic */ zzczu zzgtj;

    zzczt(zzczu zzczu, zzcce zzcce) {
        this.zzgtj = zzczu;
        this.zzgti = zzcce;
    }

    public final void zzb(Throwable th) {
        zzva zze = this.zzgti.zzaev().zze(th);
        synchronized (this.zzgtj) {
            zzdzc unused = this.zzgtj.zzgsx = null;
            this.zzgti.zzaew().zzk(zze);
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcye)).booleanValue()) {
                this.zzgtj.zzgpt.zzadi().execute(new zzczy(this, zze));
                this.zzgtj.zzgpt.zzadi().execute(new zzczx(this, zze));
            }
            zzdox.zza(zze.errorCode, th, "InterstitialAdManagerShim.onFailure");
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzcbe zzcbe = (zzcbe) obj;
        synchronized (this.zzgtj) {
            zzdzc unused = this.zzgtj.zzgsx = null;
            zzcbe unused2 = this.zzgtj.zzgtl = zzcbe;
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyh)).booleanValue()) {
                zzcbe.zzaiz().zza(this.zzgtj.zzgsq).zza(this.zzgtj.zzgss).zza(this.zzgtj.zzgst).zza(this.zzgtj.zzgtk);
            }
            zzcbe.zzaih();
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyh)).booleanValue()) {
                this.zzgtj.zzgpt.zzadi().execute(new zzczw(this));
                this.zzgtj.zzgpt.zzadi().execute(new zzczv(this));
            }
        }
    }
}

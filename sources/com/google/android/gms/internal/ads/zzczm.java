package com.google.android.gms.internal.ads;

import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzczm implements zzdyr<zzboq> {
    private final /* synthetic */ zzbpm zzgta;
    final /* synthetic */ zzczj zzgtb;

    zzczm(zzczj zzczj, zzbpm zzbpm) {
        this.zzgtb = zzczj;
        this.zzgta = zzbpm;
    }

    public final void zzb(Throwable th) {
        zzva zze = this.zzgta.zzaev().zze(th);
        synchronized (this.zzgtb) {
            zzdzc unused = this.zzgtb.zzgsx = null;
            this.zzgta.zzaew().zzk(zze);
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcye)).booleanValue()) {
                this.zzgtb.zzgpt.zzadi().execute(new zzczo(this, zze));
            }
            this.zzgtb.zzgsu.zzdw(60);
            zzdox.zza(zze.errorCode, th, "BannerAdManagerShim.onFailure");
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzboq zzboq = (zzboq) obj;
        synchronized (this.zzgtb) {
            zzdzc unused = this.zzgtb.zzgsx = null;
            if (this.zzgtb.zzgsg != null) {
                this.zzgtb.zzgsg.destroy();
            }
            zzboq unused2 = this.zzgtb.zzgsg = zzboq;
            this.zzgtb.zzfpw.removeAllViews();
            if (zzboq.zzahx() != null) {
                ViewParent parent = zzboq.zzahx().getParent();
                if (parent instanceof ViewGroup) {
                    String mediationAdapterClassName = this.zzgtb.getMediationAdapterClassName();
                    StringBuilder sb = new StringBuilder(String.valueOf(mediationAdapterClassName).length() + 78);
                    sb.append("Banner view provided from ");
                    sb.append(mediationAdapterClassName);
                    sb.append(" already has a parent view. Removing its old parent.");
                    zzayp.zzfe(sb.toString());
                    ((ViewGroup) parent).removeView(zzboq.zzahx());
                }
            }
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcye)).booleanValue()) {
                zzboq.zzaiz().zza(this.zzgtb.zzgsq).zza(this.zzgtb.zzgsr).zza(this.zzgtb.zzgss).zza(this.zzgtb.zzgst);
            }
            this.zzgtb.zzfpw.addView(zzboq.zzahx());
            zzboq.zzaih();
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcye)).booleanValue()) {
                Executor zzadi = this.zzgtb.zzgpt.zzadi();
                zzczs zzf = this.zzgtb.zzgsq;
                zzf.getClass();
                zzadi.execute(zzczl.zzb(zzf));
            }
            this.zzgtb.zzgsu.zzdw(zzboq.zzaig());
        }
    }
}

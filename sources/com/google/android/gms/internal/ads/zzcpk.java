package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcpk implements zzdyr<zzdog> {
    private final /* synthetic */ zzcpf zzgjx;

    zzcpk(zzcpf zzcpf) {
        this.zzgjx = zzcpf;
    }

    public final void zzb(Throwable th) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue()) {
            Matcher matcher = zzcpf.zzgju.matcher(th.getMessage());
            if (matcher.matches()) {
                this.zzgjx.zzgjt.zzdz(Integer.parseInt(matcher.group(1)));
            }
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzdog zzdog = (zzdog) obj;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue()) {
            this.zzgjx.zzgjt.zzdz(zzdog.zzhew.zzeou.responseCode);
            this.zzgjx.zzgjt.zzeo(zzdog.zzhew.zzeou.zzglu);
        }
    }
}

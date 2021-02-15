package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcpn implements zzdyr<zzdog> {
    private final /* synthetic */ zzcpm zzgjz;

    zzcpn(zzcpm zzcpm) {
        this.zzgjz = zzcpm;
    }

    public final void zzb(Throwable th) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue()) {
            Matcher matcher = zzcpm.zzgju.matcher(th.getMessage());
            if (matcher.matches()) {
                this.zzgjz.zzgjt.zzdz(Integer.parseInt(matcher.group(1)));
            }
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzdog zzdog = (zzdog) obj;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue()) {
            this.zzgjz.zzgjt.zzdz(zzdog.zzhew.zzeou.responseCode);
            this.zzgjz.zzgjt.zzeo(zzdog.zzhew.zzeou.zzglu);
        }
    }
}

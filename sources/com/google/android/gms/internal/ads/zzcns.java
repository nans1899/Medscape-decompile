package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.internal.zzp;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcns implements AppEventListener, zzbuh, zzbui, zzbuy, zzbuz, zzbvs, zzbws, zzdso, zzux {
    private long startTime;
    private final List<Object> zzeeg;
    private final zzcng zzgib;

    public zzcns(zzcng zzcng, zzbix zzbix) {
        this.zzgib = zzcng;
        this.zzeeg = Collections.singletonList(zzbix);
    }

    public final void zzb(zzdog zzdog) {
    }

    public final void zzca(Context context) {
        zza((Class<?>) zzbuy.class, "onPause", context);
    }

    public final void zzcb(Context context) {
        zza((Class<?>) zzbuy.class, "onResume", context);
    }

    public final void zzcc(Context context) {
        zza((Class<?>) zzbuy.class, "onDestroy", context);
    }

    public final void onAppEvent(String str, String str2) {
        zza((Class<?>) AppEventListener.class, "onAppEvent", str, str2);
    }

    public final void onAdLoaded() {
        long elapsedRealtime = zzp.zzky().elapsedRealtime() - this.startTime;
        StringBuilder sb = new StringBuilder(41);
        sb.append("Ad Request Latency : ");
        sb.append(elapsedRealtime);
        zzayp.zzei(sb.toString());
        zza((Class<?>) zzbvs.class, "onAdLoaded", new Object[0]);
    }

    public final void zzk(zzva zzva) {
        zza((Class<?>) zzbui.class, "onAdFailedToLoad", Integer.valueOf(zzva.errorCode), zzva.zzcgw, zzva.zzcgx);
    }

    public final void onAdOpened() {
        zza((Class<?>) zzbuh.class, "onAdOpened", new Object[0]);
    }

    public final void onAdClosed() {
        zza((Class<?>) zzbuh.class, "onAdClosed", new Object[0]);
    }

    public final void onAdLeftApplication() {
        zza((Class<?>) zzbuh.class, "onAdLeftApplication", new Object[0]);
    }

    public final void onAdClicked() {
        zza((Class<?>) zzux.class, "onAdClicked", new Object[0]);
    }

    public final void onAdImpression() {
        zza((Class<?>) zzbuz.class, "onAdImpression", new Object[0]);
    }

    public final void onRewardedVideoStarted() {
        zza((Class<?>) zzbuh.class, "onRewardedVideoStarted", new Object[0]);
    }

    @ParametersAreNonnullByDefault
    public final void zzb(zzatw zzatw, String str, String str2) {
        zza((Class<?>) zzbuh.class, "onRewarded", zzatw, str, str2);
    }

    public final void onRewardedVideoCompleted() {
        zza((Class<?>) zzbuh.class, "onRewardedVideoCompleted", new Object[0]);
    }

    public final void zza(zzdsf zzdsf, String str) {
        zza((Class<?>) zzdsg.class, "onTaskCreated", str);
    }

    public final void zzb(zzdsf zzdsf, String str) {
        zza((Class<?>) zzdsg.class, "onTaskStarted", str);
    }

    public final void zza(zzdsf zzdsf, String str, Throwable th) {
        zza((Class<?>) zzdsg.class, "onTaskFailed", str, th.getClass().getSimpleName());
    }

    public final void zzc(zzdsf zzdsf, String str) {
        zza((Class<?>) zzdsg.class, "onTaskSucceeded", str);
    }

    private final void zza(Class<?> cls, String str, Object... objArr) {
        zzcng zzcng = this.zzgib;
        List<Object> list = this.zzeeg;
        String valueOf = String.valueOf(cls.getSimpleName());
        zzcng.zza(list, valueOf.length() != 0 ? "Event-".concat(valueOf) : new String("Event-"), str, objArr);
    }

    public final void zzd(zzatc zzatc) {
        this.startTime = zzp.zzky().elapsedRealtime();
        zza((Class<?>) zzbws.class, "onAdRequest", new Object[0]);
    }
}

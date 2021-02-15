package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsa implements zzbuh, zzbuz, zzbvs, zzbws, zzux {
    private final Clock zzbqa;
    private final zzaxw zzftb;

    public zzbsa(Clock clock, zzaxw zzaxw) {
        this.zzbqa = clock;
        this.zzftb = zzaxw;
    }

    public final void onAdLeftApplication() {
    }

    public final void onAdOpened() {
    }

    public final void onRewardedVideoCompleted() {
    }

    public final void onRewardedVideoStarted() {
    }

    public final void zzb(zzatw zzatw, String str, String str2) {
    }

    public final void zzd(zzatc zzatc) {
    }

    public final void onAdClicked() {
        this.zzftb.zzwb();
    }

    public final void onAdLoaded() {
        this.zzftb.zzan(true);
    }

    public final void onAdImpression() {
        this.zzftb.zzwa();
    }

    public final void zzf(zzvg zzvg) {
        this.zzftb.zze(zzvg);
    }

    public final void zzb(zzdog zzdog) {
        this.zzftb.zzey(this.zzbqa.elapsedRealtime());
    }

    public final void onAdClosed() {
        this.zzftb.zzwc();
    }

    public final String zzwd() {
        return this.zzftb.zzwd();
    }
}

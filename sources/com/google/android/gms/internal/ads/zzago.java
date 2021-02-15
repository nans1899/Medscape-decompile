package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzago extends zzafd {
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzdei;

    public zzago(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzdei = onAppInstallAdLoadedListener;
    }

    public final void zza(zzaes zzaes) {
        this.zzdei.onAppInstallAdLoaded(new zzaet(zzaes));
    }
}

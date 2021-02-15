package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzabz extends zzabx {
    private final OnCustomRenderedAdLoadedListener zzcjv;

    public zzabz(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzcjv = onCustomRenderedAdLoadedListener;
    }

    public final void zza(zzabt zzabt) {
        this.zzcjv.onCustomRenderedAdLoaded(new zzabu(zzabt));
    }
}

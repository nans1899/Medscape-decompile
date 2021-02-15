package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.internal.ads.zzadp;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzd implements zzadp {
    private final UnifiedNativeAdView zzbod;

    zzd(UnifiedNativeAdView unifiedNativeAdView) {
        this.zzbod = unifiedNativeAdView;
    }

    public final void setMediaContent(MediaContent mediaContent) {
        this.zzbod.zza(mediaContent);
    }
}

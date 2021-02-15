package com.google.android.gms.ads.formats;

import android.widget.ImageView;
import com.google.android.gms.internal.ads.zzadr;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zze implements zzadr {
    private final UnifiedNativeAdView zzbod;

    zze(UnifiedNativeAdView unifiedNativeAdView) {
        this.zzbod = unifiedNativeAdView;
    }

    public final void setImageScaleType(ImageView.ScaleType scaleType) {
        this.zzbod.zza(scaleType);
    }
}

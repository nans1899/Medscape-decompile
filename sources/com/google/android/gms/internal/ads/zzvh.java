package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvh extends zzxi {
    private final AdMetadataListener zzchq;

    public zzvh(AdMetadataListener adMetadataListener) {
        this.zzchq = adMetadataListener;
    }

    public final void onAdMetadataChanged() {
        AdMetadataListener adMetadataListener = this.zzchq;
        if (adMetadataListener != null) {
            adMetadataListener.onAdMetadataChanged();
        }
    }
}

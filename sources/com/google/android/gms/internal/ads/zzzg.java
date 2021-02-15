package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.AdapterStatus;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzzg implements AdapterStatus {
    zzzg(zzzd zzzd) {
    }

    public final String getDescription() {
        return "Google Mobile Ads SDK initialization functionality unavailable for this session. Ad requests can be made at any time.";
    }

    public final int getLatency() {
        return 0;
    }

    public final AdapterStatus.State getInitializationState() {
        return AdapterStatus.State.READY;
    }
}

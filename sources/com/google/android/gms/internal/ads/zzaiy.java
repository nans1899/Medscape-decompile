package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.AdapterStatus;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaiy implements AdapterStatus {
    private final String description;
    private final int zzdgb;
    private final AdapterStatus.State zzdgd;

    public zzaiy(AdapterStatus.State state, String str, int i) {
        this.zzdgd = state;
        this.description = str;
        this.zzdgb = i;
    }

    public final AdapterStatus.State getInitializationState() {
        return this.zzdgd;
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getLatency() {
        return this.zzdgb;
    }
}

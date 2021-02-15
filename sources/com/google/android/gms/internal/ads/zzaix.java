package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaix implements InitializationStatus {
    private final Map<String, AdapterStatus> zzdgc;

    public zzaix(Map<String, AdapterStatus> map) {
        this.zzdgc = map;
    }

    public final Map<String, AdapterStatus> getAdapterStatusMap() {
        return this.zzdgc;
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.InitializationStatus;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzze implements InitializationStatus {
    private final zzzd zzckg;

    zzze(zzzd zzzd) {
        this.zzckg = zzzd;
    }

    public final Map getAdapterStatusMap() {
        zzzd zzzd = this.zzckg;
        HashMap hashMap = new HashMap();
        hashMap.put("com.google.android.gms.ads.MobileAds", new zzzg(zzzd));
        return hashMap;
    }
}

package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbfo implements Runnable {
    private final /* synthetic */ String zzecs;
    private final /* synthetic */ String zzeln;
    private final /* synthetic */ zzbfl zzelr;
    private final /* synthetic */ long zzelu;

    zzbfo(zzbfl zzbfl, String str, String str2, long j) {
        this.zzelr = zzbfl;
        this.zzecs = str;
        this.zzeln = str2;
        this.zzelu = j;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheComplete");
        hashMap.put("src", this.zzecs);
        hashMap.put("cachedSrc", this.zzeln);
        hashMap.put("totalDuration", Long.toString(this.zzelu));
        this.zzelr.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}

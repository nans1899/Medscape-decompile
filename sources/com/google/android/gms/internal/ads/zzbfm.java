package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbfm implements Runnable {
    private final /* synthetic */ String zzecs;
    private final /* synthetic */ String zzeln;
    private final /* synthetic */ int zzelo;
    private final /* synthetic */ int zzelp;
    private final /* synthetic */ boolean zzelq;
    private final /* synthetic */ zzbfl zzelr;
    private final /* synthetic */ long zzelt;
    private final /* synthetic */ long zzelu;
    private final /* synthetic */ int zzelv;
    private final /* synthetic */ int zzelw;

    zzbfm(zzbfl zzbfl, String str, String str2, int i, int i2, long j, long j2, boolean z, int i3, int i4) {
        this.zzelr = zzbfl;
        this.zzecs = str;
        this.zzeln = str2;
        this.zzelo = i;
        this.zzelp = i2;
        this.zzelt = j;
        this.zzelu = j2;
        this.zzelq = z;
        this.zzelv = i3;
        this.zzelw = i4;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheProgress");
        hashMap.put("src", this.zzecs);
        hashMap.put("cachedSrc", this.zzeln);
        hashMap.put("bytesLoaded", Integer.toString(this.zzelo));
        hashMap.put("totalBytes", Integer.toString(this.zzelp));
        hashMap.put("bufferedDuration", Long.toString(this.zzelt));
        hashMap.put("totalDuration", Long.toString(this.zzelu));
        hashMap.put("cacheReady", this.zzelq ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put("playerCount", Integer.toString(this.zzelv));
        hashMap.put("playerPreparedCount", Integer.toString(this.zzelw));
        this.zzelr.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}

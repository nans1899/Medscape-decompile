package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbfk implements Runnable {
    private final /* synthetic */ String zzecs;
    private final /* synthetic */ String zzeln;
    private final /* synthetic */ int zzelo;
    private final /* synthetic */ int zzelp;
    private final /* synthetic */ boolean zzelq = false;
    private final /* synthetic */ zzbfl zzelr;

    zzbfk(zzbfl zzbfl, String str, String str2, int i, int i2, boolean z) {
        this.zzelr = zzbfl;
        this.zzecs = str;
        this.zzeln = str2;
        this.zzelo = i;
        this.zzelp = i2;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheProgress");
        hashMap.put("src", this.zzecs);
        hashMap.put("cachedSrc", this.zzeln);
        hashMap.put("bytesLoaded", Integer.toString(this.zzelo));
        hashMap.put("totalBytes", Integer.toString(this.zzelp));
        hashMap.put("cacheReady", this.zzelq ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.zzelr.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}

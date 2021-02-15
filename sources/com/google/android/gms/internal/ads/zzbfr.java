package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbfr implements Runnable {
    private final /* synthetic */ String val$message;
    private final /* synthetic */ String zzecs;
    private final /* synthetic */ String zzeln;
    private final /* synthetic */ zzbfl zzelr;
    private final /* synthetic */ String zzelx;

    zzbfr(zzbfl zzbfl, String str, String str2, String str3, String str4) {
        this.zzelr = zzbfl;
        this.zzecs = str;
        this.zzeln = str2;
        this.zzelx = str3;
        this.val$message = str4;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheCanceled");
        hashMap.put("src", this.zzecs);
        if (!TextUtils.isEmpty(this.zzeln)) {
            hashMap.put("cachedSrc", this.zzeln);
        }
        hashMap.put("type", zzbfl.zzfp(this.zzelx));
        hashMap.put("reason", this.zzelx);
        if (!TextUtils.isEmpty(this.val$message)) {
            hashMap.put(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, this.val$message);
        }
        this.zzelr.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}

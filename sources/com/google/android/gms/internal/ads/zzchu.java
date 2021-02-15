package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzchu implements zzqu {
    private final zzbgj zzeqk;

    zzchu(zzbgj zzbgj) {
        this.zzeqk = zzbgj;
    }

    public final void zza(zzqr zzqr) {
        zzbgj zzbgj = this.zzeqk;
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", zzqr.zzbro ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zzbgj.zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }
}

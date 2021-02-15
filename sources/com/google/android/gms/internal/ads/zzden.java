package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.facebook.appevents.AppEventsConstants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzden implements zzdgu<Bundle> {
    private final String zzdrr;
    private final boolean zzdrt;

    public zzden(String str, boolean z) {
        this.zzdrr = str;
        this.zzdrt = z;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        bundle.putString("gct", this.zzdrr);
        if (this.zzdrt) {
            bundle.putString("de", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
    }
}

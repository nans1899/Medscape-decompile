package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzahi implements zzahq<zzbgj> {
    zzahi() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        ((zzbgj) obj).zzal(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
    }
}

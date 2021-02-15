package com.google.android.gms.internal.ads;

import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzagw implements zzahq<Object> {
    private final zzagv zzdeq;

    public zzagw(zzagv zzagv) {
        this.zzdeq = zzagv;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            zzayp.zzfe("App event with no name parameter.");
        } else {
            this.zzdeq.onAppEvent(str, map.get(OmnitureConstants.OMNITURE_LINK_EVENT_INFO));
        }
    }
}

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzagu implements zzahq<Object> {
    private final zzagt zzdep;

    public zzagu(zzagt zzagt) {
        this.zzdep = zzagt;
    }

    public final void zza(Object obj, Map<String, String> map) {
        if (this.zzdep != null) {
            String str = map.get("name");
            if (str == null) {
                zzayp.zzfd("Ad metadata with no name parameter.");
                str = "";
            }
            Bundle bundle = null;
            if (map.containsKey(OmnitureConstants.OMNITURE_LINK_EVENT_INFO)) {
                try {
                    bundle = zzbao.zzh(new JSONObject(map.get(OmnitureConstants.OMNITURE_LINK_EVENT_INFO)));
                } catch (JSONException e) {
                    zzayp.zzc("Failed to convert ad metadata to JSON.", e);
                }
            }
            if (bundle == null) {
                zzayp.zzfc("Failed to convert ad metadata to Bundle.");
            } else {
                this.zzdep.zza(str, bundle);
            }
        }
    }
}

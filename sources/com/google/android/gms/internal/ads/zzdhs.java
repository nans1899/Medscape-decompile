package com.google.android.gms.internal.ads;

import com.medscape.android.analytics.FirebaseEventsConstants;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhs implements zzdgu<JSONObject> {
    private final JSONObject zzgyv;

    public zzdhs(JSONObject jSONObject) {
        this.zzgyv = jSONObject;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            JSONObject zzb = zzbao.zzb((JSONObject) obj, FirebaseEventsConstants.CONTENT_INFO);
            JSONObject jSONObject = this.zzgyv;
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                zzb.put(next, jSONObject.get(next));
            }
        } catch (JSONException unused) {
            zzayp.zzei("Failed putting app indexing json.");
        }
    }
}

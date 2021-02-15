package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdic implements zzdgu<JSONObject> {
    private JSONObject zzgzb;

    public zzdic(JSONObject jSONObject) {
        this.zzgzb = jSONObject;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            ((JSONObject) obj).put("cache_state", this.zzgzb);
        } catch (JSONException unused) {
            zzayp.zzei("Unable to get cache_state");
        }
    }
}

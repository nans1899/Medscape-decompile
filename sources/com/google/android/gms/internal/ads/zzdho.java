package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdho implements zzdgu<JSONObject> {
    private final String zzgyt;

    public zzdho(String str) {
        this.zzgyt = str;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            ((JSONObject) obj).put("ms", this.zzgyt);
        } catch (JSONException e) {
            zzayp.zza("Failed putting Ad ID.", e);
        }
    }
}

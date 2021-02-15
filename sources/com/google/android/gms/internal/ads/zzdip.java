package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdip implements zzdgu<JSONObject> {
    private List<String> zzdrc;

    public zzdip(List<String> list) {
        this.zzdrc = list;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            ((JSONObject) obj).put("eid", TextUtils.join(",", this.zzdrc));
        } catch (JSONException unused) {
            zzayp.zzei("Failed putting experiment ids.");
        }
    }
}

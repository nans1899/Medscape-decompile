package com.google.android.gms.internal.ads;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdig implements zzdgx<zzdgu<JSONObject>> {
    private final JSONObject zzgzf;

    zzdig(Context context) {
        this.zzgzf = zzato.zzz(context);
    }

    public final zzdzc<zzdgu<JSONObject>> zzarj() {
        return zzdyq.zzaf(new zzdij(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(JSONObject jSONObject) {
        try {
            jSONObject.put("gms_sdk_env", this.zzgzf);
        } catch (JSONException unused) {
            zzayp.zzei("Failed putting version constants.");
        }
    }
}

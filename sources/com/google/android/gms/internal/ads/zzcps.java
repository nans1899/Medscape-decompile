package com.google.android.gms.internal.ads;

import com.facebook.share.internal.ShareConstants;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcps implements zzdya {
    private final zzcpp zzgkc;
    private final zzuy zzgkd;

    zzcps(zzcpp zzcpp, zzuy zzuy) {
        this.zzgkc = zzcpp;
        this.zzgkd = zzuy;
    }

    public final zzdzc zzf(Object obj) {
        zzuy zzuy = this.zzgkd;
        String str = (String) obj;
        String str2 = zzuy.zzcgt;
        String str3 = zzuy.zzcgu;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("headers", new JSONObject());
        jSONObject3.put("body", str2);
        jSONObject2.put("base_url", "");
        jSONObject2.put("signals", new JSONObject(str3));
        jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject2);
        jSONObject.put("response", jSONObject3);
        jSONObject.put("flags", new JSONObject());
        return zzdyq.zzaf(jSONObject);
    }
}

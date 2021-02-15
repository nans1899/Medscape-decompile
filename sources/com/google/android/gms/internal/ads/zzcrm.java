package com.google.android.gms.internal.ads;

import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcrm implements zzaly<zzcrj> {
    zzcrm() {
    }

    public final /* synthetic */ JSONObject zzj(Object obj) throws JSONException {
        zzcrj zzcrj = (zzcrj) obj;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject2.put("base_url", zzcrj.zzgll.zzvi());
        jSONObject2.put("signals", zzcrj.zzglk);
        jSONObject3.put("body", zzcrj.zzglj.zzdsl);
        jSONObject3.put("headers", zzp.zzkr().zzj((Map<String, ?>) zzcrj.zzglj.zzam));
        jSONObject3.put("response_code", zzcrj.zzglj.zzglt);
        jSONObject3.put("latency", zzcrj.zzglj.zzglu);
        jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject2);
        jSONObject.put("response", jSONObject3);
        jSONObject.put("flags", zzcrj.zzgll.zzvl());
        return jSONObject;
    }
}

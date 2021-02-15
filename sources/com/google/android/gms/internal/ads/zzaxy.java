package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaxy {
    private String zzdro;
    private final long zzdyp;
    private final List<String> zzdyq = new ArrayList();
    private final List<String> zzdyr = new ArrayList();
    private final Map<String, zzana> zzdys = new HashMap();
    private String zzdyt;
    private JSONObject zzdyu;
    private boolean zzdyv;

    public zzaxy(String str, long j) {
        JSONObject optJSONObject;
        this.zzdyv = false;
        this.zzdro = str;
        this.zzdyp = j;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.zzdyu = jSONObject;
                if (jSONObject.optInt("status", -1) != 1) {
                    this.zzdyv = false;
                    zzayp.zzfe("App settings could not be fetched successfully.");
                    return;
                }
                this.zzdyv = true;
                this.zzdyt = this.zzdyu.optString("app_id");
                JSONArray optJSONArray = this.zzdyu.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        String optString = jSONObject2.optString("format");
                        String optString2 = jSONObject2.optString("ad_unit_id");
                        if (!TextUtils.isEmpty(optString)) {
                            if (!TextUtils.isEmpty(optString2)) {
                                if ("interstitial".equalsIgnoreCase(optString)) {
                                    this.zzdyr.add(optString2);
                                } else if ("rewarded".equalsIgnoreCase(optString) && (optJSONObject = jSONObject2.optJSONObject("mediation_config")) != null) {
                                    this.zzdys.put(optString2, new zzana(optJSONObject));
                                }
                            }
                        }
                    }
                }
                JSONArray optJSONArray2 = this.zzdyu.optJSONArray("persistable_banner_ad_unit_ids");
                if (optJSONArray2 != null) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        this.zzdyq.add(optJSONArray2.optString(i2));
                    }
                }
            } catch (JSONException e) {
                zzayp.zzd("Exception occurred while processing app setting json", e);
                zzp.zzkv().zza(e, "AppSettings.parseAppSettingsJson");
            }
        }
    }

    public final long zzwe() {
        return this.zzdyp;
    }

    public final boolean zzwf() {
        return this.zzdyv;
    }

    public final String zzwg() {
        return this.zzdro;
    }

    public final String zzwh() {
        return this.zzdyt;
    }

    public final Map<String, zzana> zzwi() {
        return this.zzdys;
    }

    public final JSONObject zzwj() {
        return this.zzdyu;
    }
}

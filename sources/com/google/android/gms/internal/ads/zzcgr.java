package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcgr implements Callable {
    private final zzdnv zzfqn;
    private final zzcgs zzgcw;
    private final zzdog zzgcx;
    private final JSONObject zzgcy;

    zzcgr(zzcgs zzcgs, zzdog zzdog, zzdnv zzdnv, JSONObject jSONObject) {
        this.zzgcw = zzcgs;
        this.zzgcx = zzdog;
        this.zzfqn = zzdnv;
        this.zzgcy = jSONObject;
    }

    public final Object call() {
        zzdog zzdog = this.zzgcx;
        zzdnv zzdnv = this.zzfqn;
        JSONObject jSONObject = this.zzgcy;
        zzcei zzcei = new zzcei();
        zzcei.zzdy(jSONObject.optInt("template_id", -1));
        zzcei.zzga(jSONObject.optString("custom_template_id"));
        JSONObject optJSONObject = jSONObject.optJSONObject("omid_settings");
        zzcei.zzgb(optJSONObject != null ? optJSONObject.optString("omid_partner_name") : null);
        zzdok zzdok = zzdog.zzhev.zzfsk;
        if (zzdok.zzhfc.contains(Integer.toString(zzcei.zzama()))) {
            if (zzcei.zzama() == 3) {
                if (zzcei.getCustomTemplateId() == null) {
                    throw new zzcwn(zzdpg.INTERNAL_ERROR, "No custom template id for custom template ad response.");
                } else if (!zzdok.zzhfd.contains(zzcei.getCustomTemplateId())) {
                    throw new zzcwn(zzdpg.INTERNAL_ERROR, "Unexpected custom template id in the response.");
                }
            }
            zzcei.setStarRating(jSONObject.optDouble("rating", -1.0d));
            String optString = jSONObject.optString("headline", (String) null);
            if (zzdnv.zzduj) {
                zzp.zzkr();
                String zzxv = zzayu.zzxv();
                StringBuilder sb = new StringBuilder(String.valueOf(zzxv).length() + 3 + String.valueOf(optString).length());
                sb.append(zzxv);
                sb.append(" : ");
                sb.append(optString);
                optString = sb.toString();
            }
            zzcei.zzn("headline", optString);
            zzcei.zzn("body", jSONObject.optString("body", (String) null));
            zzcei.zzn("call_to_action", jSONObject.optString("call_to_action", (String) null));
            zzcei.zzn("store", jSONObject.optString("store", (String) null));
            zzcei.zzn(FirebaseAnalytics.Param.PRICE, jSONObject.optString(FirebaseAnalytics.Param.PRICE, (String) null));
            zzcei.zzn("advertiser", jSONObject.optString("advertiser", (String) null));
            return zzcei;
        }
        zzdpg zzdpg = zzdpg.INTERNAL_ERROR;
        int zzama = zzcei.zzama();
        StringBuilder sb2 = new StringBuilder(32);
        sb2.append("Invalid template ID: ");
        sb2.append(zzama);
        throw new zzcwn(zzdpg, sb2.toString());
    }
}

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcel extends zzcem {
    private final boolean zzdkw;
    private final boolean zzdkx;
    private final boolean zzdtm;
    private final JSONObject zzgaf;
    private final boolean zzgag;

    public zzcel(zzdnv zzdnv, JSONObject jSONObject) {
        super(zzdnv);
        this.zzgaf = zzbao.zza(jSONObject, "tracking_urls_and_actions", "active_view");
        boolean z = false;
        this.zzdkx = zzbao.zza(false, jSONObject, "allow_pub_owned_ad_view");
        this.zzdkw = zzbao.zza(false, jSONObject, "attribution", "allow_pub_rendering");
        this.zzdtm = zzbao.zza(false, jSONObject, "enable_omid");
        if (!(jSONObject == null || jSONObject.optJSONObject("overlay") == null)) {
            z = true;
        }
        this.zzgag = z;
    }

    public final JSONObject zzaml() {
        JSONObject jSONObject = this.zzgaf;
        if (jSONObject != null) {
            return jSONObject;
        }
        try {
            return new JSONObject(this.zzgah.zzdsv);
        } catch (JSONException unused) {
            return null;
        }
    }

    public final boolean zzamm() {
        return this.zzgag;
    }

    public final boolean zzamn() {
        return this.zzdkx;
    }

    public final boolean zzalw() {
        return this.zzdtm;
    }

    public final boolean zzamo() {
        return this.zzdkw;
    }
}

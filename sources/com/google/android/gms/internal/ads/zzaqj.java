package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaqj {
    private final boolean zzdnv;
    private final boolean zzdnw;
    private final boolean zzdnx;
    private final boolean zzdny;
    private final boolean zzdnz;

    private zzaqj(zzaql zzaql) {
        this.zzdnv = zzaql.zzdnv;
        this.zzdnw = zzaql.zzdnw;
        this.zzdnx = zzaql.zzdnx;
        this.zzdny = zzaql.zzdny;
        this.zzdnz = zzaql.zzdnz;
    }

    public final JSONObject zzdp() {
        try {
            return new JSONObject().put("sms", this.zzdnv).put("tel", this.zzdnw).put("calendar", this.zzdnx).put("storePicture", this.zzdny).put("inlineVideo", this.zzdnz);
        } catch (JSONException e) {
            zzayp.zzc("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}

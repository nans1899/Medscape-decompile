package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzatv implements zzatg {
    private zzalt<JSONObject, JSONObject> zzdvo;
    private zzalt<JSONObject, JSONObject> zzdvt;

    public zzatv(Context context) {
        this.zzdvt = zzp.zzle().zza(context, zzbbx.zzyv()).zza("google.afma.request.getAdDictionary", zzama.zzdio, zzama.zzdio);
        this.zzdvo = zzp.zzle().zza(context, zzbbx.zzyv()).zza("google.afma.sdkConstants.getSdkConstants", zzama.zzdio, zzama.zzdio);
    }

    public final zzalt<JSONObject, JSONObject> zzvg() {
        return this.zzdvo;
    }
}

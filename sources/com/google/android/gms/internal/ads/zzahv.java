package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzahv implements zzahy {
    private final /* synthetic */ zzbcg zzdfq;

    zzahv(zzahw zzahw, zzbcg zzbcg) {
        this.zzdfq = zzbcg;
    }

    public final void zzc(JSONObject jSONObject) {
        this.zzdfq.set(jSONObject);
    }

    public final void onFailure(String str) {
        this.zzdfq.setException(new zzalu(str));
    }
}

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzamp implements zzahy {
    private final zzbcg<O> zzdja;
    private final /* synthetic */ zzamn zzdjd;

    public zzamp(zzamn zzamn, zzbcg<O> zzbcg) {
        this.zzdjd = zzamn;
        this.zzdja = zzbcg;
    }

    public final void zzc(JSONObject jSONObject) {
        try {
            this.zzdja.set(this.zzdjd.zzdiw.zzd(jSONObject));
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.zzdja.setException(e);
        }
    }

    public final void onFailure(String str) {
        if (str == null) {
            try {
                this.zzdja.setException(new zzalu());
            } catch (IllegalStateException unused) {
            }
        } else {
            this.zzdja.setException(new zzalu(str));
        }
    }
}

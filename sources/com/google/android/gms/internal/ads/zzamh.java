package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzamh implements zzahy {
    private final /* synthetic */ zzamg zzdiv;
    private final zzali zzdiz;
    private final zzbcg<O> zzdja;

    public zzamh(zzamg zzamg, zzali zzali, zzbcg<O> zzbcg) {
        this.zzdiv = zzamg;
        this.zzdiz = zzali;
        this.zzdja = zzbcg;
    }

    public final void zzc(JSONObject jSONObject) {
        try {
            this.zzdja.set(this.zzdiv.zzdiw.zzd(jSONObject));
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.zzdja.setException(e);
        } finally {
            this.zzdiz.release();
        }
    }

    public final void onFailure(String str) {
        if (str == null) {
            try {
                this.zzdja.setException(new zzalu());
            } catch (IllegalStateException unused) {
                this.zzdiz.release();
                return;
            } catch (Throwable th) {
                this.zzdiz.release();
                throw th;
            }
        } else {
            this.zzdja.setException(new zzalu(str));
        }
        this.zzdiz.release();
    }
}

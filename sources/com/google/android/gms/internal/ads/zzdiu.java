package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzp;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdiu implements zzdgu<JSONObject> {
    private Bundle zzgzm;

    public zzdiu(Bundle bundle) {
        this.zzgzm = bundle;
    }

    public final /* synthetic */ void zzs(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (this.zzgzm != null) {
            try {
                zzbao.zzb(zzbao.zzb(jSONObject, "device"), "play_store").put("parental_controls", zzp.zzkr().zzd(this.zzgzm));
            } catch (JSONException unused) {
                zzayp.zzei("Failed putting parental controls bundle.");
            }
        }
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdji implements zzdgu<JSONObject> {
    private final Map<String, Object> zzgzr;

    public zzdji(Map<String, Object> map) {
        this.zzgzr = map;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            ((JSONObject) obj).put("video_decoders", zzp.zzkr().zzj((Map<String, ?>) this.zzgzr));
        } catch (JSONException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzayp.zzei(valueOf.length() != 0 ? "Could not encode video decoder properties: ".concat(valueOf) : new String("Could not encode video decoder properties: "));
        }
    }
}

package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhi implements zzdgu<JSONObject> {
    private final AdvertisingIdClient.Info zzgyp;
    private final String zzgyq;

    public zzdhi(AdvertisingIdClient.Info info, String str) {
        this.zzgyp = info;
        this.zzgyq = str;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            JSONObject zzb = zzbao.zzb((JSONObject) obj, "pii");
            if (this.zzgyp == null || TextUtils.isEmpty(this.zzgyp.getId())) {
                zzb.put("pdid", this.zzgyq);
                zzb.put("pdidtype", "ssaid");
                return;
            }
            zzb.put("rdid", this.zzgyp.getId());
            zzb.put("is_lat", this.zzgyp.isLimitAdTrackingEnabled());
            zzb.put("idtype", "adid");
        } catch (JSONException e) {
            zzayp.zza("Failed putting Ad ID.", e);
        }
    }
}

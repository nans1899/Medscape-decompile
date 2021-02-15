package com.google.android.gms.internal.ads;

import android.location.Location;
import com.medscape.android.cache.Cache;
import com.medscape.android.settings.Settings;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdiq implements zzdgu<JSONObject> {
    private final Location zznb;

    public zzdiq(Location location) {
        this.zznb = location;
    }

    public final /* synthetic */ void zzs(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        try {
            if (this.zznb != null) {
                JSONObject jSONObject2 = new JSONObject();
                Float valueOf = Float.valueOf(this.zznb.getAccuracy() * 1000.0f);
                Long valueOf2 = Long.valueOf(this.zznb.getTime() * 1000);
                Long valueOf3 = Long.valueOf((long) (this.zznb.getLatitude() * 1.0E7d));
                Long valueOf4 = Long.valueOf((long) (this.zznb.getLongitude() * 1.0E7d));
                jSONObject2.put("radius", valueOf);
                jSONObject2.put(Settings.LATITUDE_KEY, valueOf3);
                jSONObject2.put(Settings.LONGITUDE_KEY, valueOf4);
                jSONObject2.put(Cache.Caches.TIME, valueOf2);
                jSONObject.put("uule", jSONObject2);
            }
        } catch (JSONException e) {
            zzayp.zza("Failed adding location to the request JSON.", e);
        }
    }
}

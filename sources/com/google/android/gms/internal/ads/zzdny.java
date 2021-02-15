package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import android.util.JsonWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdny implements zzbat {
    public final String zzdpn = this.zzhen.optString("ad_base_url", (String) null);
    public final String zzdpp;
    public final JSONObject zzfxs = this.zzhen.optJSONObject("ad_json");
    private final JSONObject zzhen;

    zzdny(JsonReader jsonReader) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        JSONObject zzc = zzbao.zzc(jsonReader);
        this.zzhen = zzc;
        this.zzdpp = zzc.optString("ad_html", (String) null);
    }

    public final void zza(JsonWriter jsonWriter) throws IOException {
        zzbao.zza(jsonWriter, this.zzhen);
    }
}

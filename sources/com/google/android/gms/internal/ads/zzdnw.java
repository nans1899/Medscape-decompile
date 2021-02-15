package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdnw {
    public final int responseCode;
    public final List<String> zzdkm;
    public final String zzdtb;
    public final String zzdto;
    public final long zzglu;
    public final int zzhel;
    public final zzdnx zzhem;

    zzdnw(JsonReader jsonReader) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        List<String> emptyList = Collections.emptyList();
        jsonReader.beginObject();
        String str = "";
        String str2 = str;
        zzdnx zzdnx = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if ("nofill_urls".equals(nextName)) {
                emptyList = zzbao.zza(jsonReader);
            } else if ("refresh_interval".equals(nextName)) {
                i = jsonReader.nextInt();
            } else if ("gws_query_id".equals(nextName)) {
                str = jsonReader.nextString();
            } else if ("analytics_query_ad_event_id".equals(nextName)) {
                str2 = jsonReader.nextString();
            } else if ("response_code".equals(nextName)) {
                i2 = jsonReader.nextInt();
            } else if ("latency".equals(nextName)) {
                j = jsonReader.nextLong();
            } else {
                if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcyo)).booleanValue() || !"public_error".equals(nextName) || jsonReader.peek() != JsonToken.BEGIN_OBJECT) {
                    jsonReader.skipValue();
                } else {
                    zzdnx = new zzdnx(jsonReader);
                }
            }
        }
        jsonReader.endObject();
        this.zzdkm = emptyList;
        this.zzhel = i;
        this.zzdtb = str;
        this.zzdto = str2;
        this.responseCode = i2;
        this.zzglu = j;
        this.zzhem = zzdnx;
    }
}

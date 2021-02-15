package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.util.JsonReader;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzp;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbm {
    public final String zzguv;
    public String zzguw;
    public Bundle zzgux = new Bundle();

    public zzdbm(JsonReader jsonReader) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        Map<String, String> hashMap = new HashMap<>();
        jsonReader.beginObject();
        String str = "";
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName = nextName == null ? "" : nextName;
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != -995427962) {
                if (hashCode == -271442291 && nextName.equals("signal_dictionary")) {
                    c = 1;
                }
            } else if (nextName.equals(NativeProtocol.WEB_DIALOG_PARAMS)) {
                c = 0;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c != 1) {
                jsonReader.skipValue();
            } else {
                hashMap = zzbao.zzb(jsonReader);
            }
        }
        this.zzguv = str;
        jsonReader.endObject();
        for (Map.Entry next : hashMap.entrySet()) {
            if (!(next.getKey() == null || next.getValue() == null)) {
                this.zzgux.putString((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final zzdbm zzo(Bundle bundle) {
        try {
            this.zzguw = zzp.zzkr().zzd(bundle).toString();
        } catch (JSONException unused) {
            this.zzguw = "{}";
        }
        return this;
    }
}

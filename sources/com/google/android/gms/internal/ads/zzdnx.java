package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import java.io.IOException;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdnx {
    private int code;
    private String description;

    public final String getDescription() {
        return this.description;
    }

    zzdnx(JsonReader jsonReader) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        jsonReader.beginObject();
        int i = 3;
        String str = "";
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != -1724546052) {
                if (hashCode == 3059181 && nextName.equals("code")) {
                    c = 0;
                }
            } else if (nextName.equals("description")) {
                c = 1;
            }
            if (c == 0) {
                i = jsonReader.nextInt();
            } else if (c != 1) {
                jsonReader.skipValue();
            } else {
                str = jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        this.code = i;
        this.description = str;
    }
}

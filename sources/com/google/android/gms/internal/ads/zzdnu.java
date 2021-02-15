package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdnu {
    public final int height;
    public final int width;
    public final boolean zzhdh;

    public zzdnu(int i, int i2, boolean z) {
        this.width = i;
        this.height = i2;
        this.zzhdh = z;
    }

    static List<zzdnu> zze(JsonReader jsonReader) throws IllegalStateException, IOException, NumberFormatException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            int i = 0;
            int i2 = 0;
            boolean z = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH.equals(nextName)) {
                    i = jsonReader.nextInt();
                } else if (GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT.equals(nextName)) {
                    i2 = jsonReader.nextInt();
                } else if ("is_fluid_height".equals(nextName)) {
                    z = jsonReader.nextBoolean();
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            arrayList.add(new zzdnu(i, i2, z));
        }
        jsonReader.endArray();
        return arrayList;
    }
}

package com.google.android.gms.internal.ads;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchg {
    private final Executor executor;
    private final zzcgw zzgda;

    public zzchg(Executor executor2, zzcgw zzcgw) {
        this.executor = executor2;
        this.zzgda = zzcgw;
    }

    public final zzdzc<List<zzchh>> zzg(JSONObject jSONObject, String str) {
        zzdzc<O> zzdzc;
        String optString;
        char c;
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return zzdyq.zzaf(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (!(optJSONObject == null || (optString = optJSONObject.optString("name")) == null)) {
                String optString2 = optJSONObject.optString("type");
                if ("string".equals(optString2)) {
                    c = 1;
                } else {
                    c = MessengerShareContentUtility.MEDIA_IMAGE.equals(optString2) ? (char) 2 : 0;
                }
                if (c == 1) {
                    zzdzc = zzdyq.zzaf(new zzchh(optString, optJSONObject.optString("string_value")));
                } else if (c == 2) {
                    zzdzc = zzdyq.zzb(this.zzgda.zzc(optJSONObject, "image_value"), new zzchi(optString), this.executor);
                }
                arrayList.add(zzdzc);
            }
            zzdzc = zzdyq.zzaf(null);
            arrayList.add(zzdzc);
        }
        return zzdyq.zzb(zzdyq.zzi(arrayList), zzchf.zzdwz, this.executor);
    }
}

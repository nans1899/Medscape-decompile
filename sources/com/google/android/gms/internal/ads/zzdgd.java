package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdgd implements Callable {
    private final List zzgxm;

    zzdgd(List list) {
        this.zzgxm = list;
    }

    public final Object call() {
        List<zzdzc> list = this.zzgxm;
        JSONArray jSONArray = new JSONArray();
        for (zzdzc zzdzc : list) {
            if (((JSONObject) zzdzc.get()) != null) {
                jSONArray.put(zzdzc.get());
            }
        }
        if (jSONArray.length() == 0) {
            return null;
        }
        return new zzdfw(jSONArray.toString());
    }
}

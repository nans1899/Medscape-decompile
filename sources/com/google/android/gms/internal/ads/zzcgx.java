package com.google.android.gms.internal.ads;

import java.util.List;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcgx implements zzdvu {
    private final JSONObject zzfnx;
    private final zzcgw zzgdo;

    zzcgx(zzcgw zzcgw, JSONObject jSONObject) {
        this.zzgdo = zzcgw;
        this.zzfnx = jSONObject;
    }

    public final Object apply(Object obj) {
        return this.zzgdo.zza(this.zzfnx, (List) obj);
    }
}

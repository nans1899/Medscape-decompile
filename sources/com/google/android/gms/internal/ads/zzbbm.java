package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzbbm implements zzbbr {
    private final int zzeeh;
    private final Map zzeei;

    zzbbm(int i, Map map) {
        this.zzeeh = i;
        this.zzeei = map;
    }

    public final void zzb(JsonWriter jsonWriter) {
        zzbbk.zza(this.zzeeh, this.zzeei, jsonWriter);
    }
}

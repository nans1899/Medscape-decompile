package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzahl implements zzahq<zzbgj> {
    zzahl() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        if (map.keySet().contains("start")) {
            zzbgj.zzabw().zzabj();
        } else if (map.keySet().contains("stop")) {
            zzbgj.zzabw().zzabk();
        } else if (map.keySet().contains("cancel")) {
            zzbgj.zzabw().zzabl();
        }
    }
}

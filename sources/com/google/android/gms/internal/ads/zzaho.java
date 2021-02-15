package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaho implements zzahq<zzbgj> {
    zzaho() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        if (map.keySet().contains("start")) {
            zzbgj.zzbc(true);
        }
        if (map.keySet().contains("stop")) {
            zzbgj.zzbc(false);
        }
    }
}

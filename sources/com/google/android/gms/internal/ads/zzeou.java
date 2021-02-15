package com.google.android.gms.internal.ads;

import java.util.LinkedHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzeou<K, V, V2> {
    final LinkedHashMap<K, zzeph<V>> zziun;

    zzeou(int i) {
        this.zziun = zzeot.zzia(i);
    }

    /* access modifiers changed from: package-private */
    public zzeou<K, V, V2> zza(K k, zzeph<V> zzeph) {
        this.zziun.put(zzepe.zza(k, "key"), zzepe.zza(zzeph, "provider"));
        return this;
    }
}

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzeor<K, V, V2> implements zzeoy<Map<K, V2>> {
    private final Map<K, zzeph<V>> zzium;

    zzeor(Map<K, zzeph<V>> map) {
        this.zzium = Collections.unmodifiableMap(map);
    }

    /* access modifiers changed from: package-private */
    public final Map<K, zzeph<V>> zzbki() {
        return this.zzium;
    }
}

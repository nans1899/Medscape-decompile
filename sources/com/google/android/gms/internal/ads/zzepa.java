package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzepa<K, V> extends zzeor<K, V, V> {
    private static final zzeph<Map<Object, Object>> zzius = zzeox.zzba(Collections.emptyMap());

    public static <K, V> zzepc<K, V> zzic(int i) {
        return new zzepc<>(i);
    }

    private zzepa(Map<K, zzeph<V>> map) {
        super(map);
    }

    public final /* synthetic */ Object get() {
        LinkedHashMap zzia = zzeot.zzia(zzbki().size());
        for (Map.Entry entry : zzbki().entrySet()) {
            zzia.put(entry.getKey(), ((zzeph) entry.getValue()).get());
        }
        return Collections.unmodifiableMap(zzia);
    }
}

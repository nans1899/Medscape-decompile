package com.google.android.gms.internal.vision;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zziq<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzio> zzzm;

    private zziq(Map.Entry<K, zzio> entry) {
        this.zzzm = entry;
    }

    public final K getKey() {
        return this.zzzm.getKey();
    }

    public final Object getValue() {
        if (this.zzzm.getValue() == null) {
            return null;
        }
        return zzio.zzhp();
    }

    public final zzio zzhr() {
        return this.zzzm.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzjn) {
            return this.zzzm.getValue().zzi((zzjn) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}

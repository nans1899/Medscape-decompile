package com.google.android.gms.internal.icing;

import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzek<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzei> zzls;

    private zzek(Map.Entry<K, zzei> entry) {
        this.zzls = entry;
    }

    public final K getKey() {
        return this.zzls.getKey();
    }

    public final Object getValue() {
        if (this.zzls.getValue() == null) {
            return null;
        }
        return zzei.zzca();
    }

    public final zzei zzcc() {
        return this.zzls.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzfh) {
            return this.zzls.getValue().zzh((zzfh) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}

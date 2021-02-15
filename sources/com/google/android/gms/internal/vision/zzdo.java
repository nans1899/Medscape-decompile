package com.google.android.gms.internal.vision;

import java.util.Collection;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
abstract class zzdo<K, V> implements zzen<K, V> {
    @NullableDecl
    private transient Map<K, Collection<V>> zzme;

    zzdo() {
    }

    /* access modifiers changed from: package-private */
    public abstract Map<K, Collection<V>> zzcd();

    public boolean containsValue(@NullableDecl Object obj) {
        for (Collection contains : zzcc().values()) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public Map<K, Collection<V>> zzcc() {
        Map<K, Collection<V>> map = this.zzme;
        if (map != null) {
            return map;
        }
        Map<K, Collection<V>> zzcd = zzcd();
        this.zzme = zzcd;
        return zzcd;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzen) {
            return zzcc().equals(((zzen) obj).zzcc());
        }
        return false;
    }

    public int hashCode() {
        return zzcc().hashCode();
    }

    public String toString() {
        return zzcc().toString();
    }
}

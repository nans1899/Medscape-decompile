package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public class zzeh<K, V> extends zzdn<K, V> implements Serializable {
    private final transient int size;
    private final transient zzef<K, ? extends zzeb<V>> zznc;

    zzeh(zzef<K, ? extends zzeb<V>> zzef, int i) {
        this.zznc = zzef;
        this.size = i;
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        return obj != null && super.containsValue(obj);
    }

    /* access modifiers changed from: package-private */
    public final Map<K, Collection<V>> zzcd() {
        throw new AssertionError("should never be called");
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Map zzcc() {
        return this.zznc;
    }
}

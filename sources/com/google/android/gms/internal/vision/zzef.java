package com.google.android.gms.internal.vision;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzef<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zzmx = new Map.Entry[0];
    private transient zzej<Map.Entry<K, V>> zzmy;
    private transient zzej<K> zzmz;
    private transient zzeb<V> zzna;

    zzef() {
    }

    public abstract V get(@NullableDecl Object obj);

    /* access modifiers changed from: package-private */
    public abstract zzej<Map.Entry<K, V>> zzcw();

    /* access modifiers changed from: package-private */
    public abstract zzej<K> zzcx();

    /* access modifiers changed from: package-private */
    public abstract zzeb<V> zzcy();

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@NullableDecl Object obj) {
        return ((zzeb) values()).contains(obj);
    }

    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public int hashCode() {
        return zzey.zza((zzej) entrySet());
    }

    public String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) << 3, FileUtils.ONE_GB));
            sb.append(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                z = false;
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            sb.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder("size".length() + 40);
        sb2.append("size");
        sb2.append(" cannot be negative but was: ");
        sb2.append(size);
        throw new IllegalArgumentException(sb2.toString());
    }

    public /* synthetic */ Set entrySet() {
        zzej<Map.Entry<K, V>> zzej = this.zzmy;
        if (zzej != null) {
            return zzej;
        }
        zzej<Map.Entry<K, V>> zzcw = zzcw();
        this.zzmy = zzcw;
        return zzcw;
    }

    public /* synthetic */ Collection values() {
        zzeb<V> zzeb = this.zzna;
        if (zzeb != null) {
            return zzeb;
        }
        zzeb<V> zzcy = zzcy();
        this.zzna = zzcy;
        return zzcy;
    }

    public /* synthetic */ Set keySet() {
        zzej<K> zzej = this.zzmz;
        if (zzej != null) {
            return zzej;
        }
        zzej<K> zzcx = zzcx();
        this.zzmz = zzcx;
        return zzcx;
    }
}

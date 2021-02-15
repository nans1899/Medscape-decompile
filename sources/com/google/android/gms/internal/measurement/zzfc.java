package com.google.android.gms.internal.measurement;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public abstract class zzfc<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zza = new Map.Entry[0];
    private transient zzfg<Map.Entry<K, V>> zzb;
    private transient zzfg<K> zzc;
    private transient zzey<V> zzd;

    zzfc() {
    }

    public abstract V get(@NullableDecl Object obj);

    /* access modifiers changed from: package-private */
    public abstract zzfg<Map.Entry<K, V>> zza();

    /* access modifiers changed from: package-private */
    public abstract zzfg<K> zzb();

    /* access modifiers changed from: package-private */
    public abstract zzey<V> zzc();

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
        return ((zzey) values()).contains(obj);
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
        return zzfv.zza((zzfg) entrySet());
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
        zzfg<Map.Entry<K, V>> zzfg = this.zzb;
        if (zzfg != null) {
            return zzfg;
        }
        zzfg<Map.Entry<K, V>> zza2 = zza();
        this.zzb = zza2;
        return zza2;
    }

    public /* synthetic */ Collection values() {
        zzey<V> zzey = this.zzd;
        if (zzey != null) {
            return zzey;
        }
        zzey<V> zzc2 = zzc();
        this.zzd = zzc2;
        return zzc2;
    }

    public /* synthetic */ Set keySet() {
        zzfg<K> zzfg = this.zzc;
        if (zzfg != null) {
            return zzfg;
        }
        zzfg<K> zzb2 = zzb();
        this.zzc = zzb2;
        return zzb2;
    }
}

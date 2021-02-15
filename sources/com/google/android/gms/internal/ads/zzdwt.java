package com.google.android.gms.internal.ads;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public abstract class zzdwt<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zzhqq = new Map.Entry[0];
    private transient zzdws<Map.Entry<K, V>> zzhqr;
    private transient zzdws<K> zzhqs;
    private transient zzdwk<V> zzhqt;

    public static <K, V> zzdwt<K, V> zzc(K k, V v) {
        zzdwj.zzb(k, v);
        return zzdwy.zzc(1, new Object[]{k, v});
    }

    public abstract V get(@NullableDecl Object obj);

    /* access modifiers changed from: package-private */
    public abstract zzdws<Map.Entry<K, V>> zzaxr();

    /* access modifiers changed from: package-private */
    public abstract zzdws<K> zzaxs();

    /* access modifiers changed from: package-private */
    public abstract zzdwk<V> zzaxt();

    public static <K, V> zzdwt<K, V> zza(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        zzdwj.zzb(k, v);
        zzdwj.zzb(k2, v2);
        zzdwj.zzb(k3, v3);
        zzdwj.zzb(k4, v4);
        zzdwj.zzb(k5, v5);
        return zzdwy.zzc(5, new Object[]{k, v, k2, v2, k3, v3, k4, v4, k5, v5});
    }

    zzdwt() {
    }

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
        return ((zzdwk) values()).contains(obj);
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
        return zzdxe.zzg((zzdws) entrySet());
    }

    public String toString() {
        int size = size();
        zzdwj.zzh(size, "size");
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

    public /* synthetic */ Set entrySet() {
        zzdws<Map.Entry<K, V>> zzdws = this.zzhqr;
        if (zzdws != null) {
            return zzdws;
        }
        zzdws<Map.Entry<K, V>> zzaxr = zzaxr();
        this.zzhqr = zzaxr;
        return zzaxr;
    }

    public /* synthetic */ Collection values() {
        zzdwk<V> zzdwk = this.zzhqt;
        if (zzdwk != null) {
            return zzdwk;
        }
        zzdwk<V> zzaxt = zzaxt();
        this.zzhqt = zzaxt;
        return zzaxt;
    }

    public /* synthetic */ Set keySet() {
        zzdws<K> zzdws = this.zzhqs;
        if (zzdws != null) {
            return zzdws;
        }
        zzdws<K> zzaxs = zzaxs();
        this.zzhqs = zzaxs;
        return zzaxs;
    }
}

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdxb<K, V> extends zzdws<Map.Entry<K, V>> {
    /* access modifiers changed from: private */
    public final transient int size;
    /* access modifiers changed from: private */
    public final transient Object[] zzhqz;
    private final transient zzdwt<K, V> zzhrd;
    private final transient int zzhre = 0;

    zzdxb(zzdwt<K, V> zzdwt, Object[] objArr, int i, int i2) {
        this.zzhrd = zzdwt;
        this.zzhqz = objArr;
        this.size = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxn() {
        return true;
    }

    public final zzdxg<Map.Entry<K, V>> zzaxi() {
        return (zzdxg) zzaxm().iterator();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return zzaxm().zza(objArr, i);
    }

    /* access modifiers changed from: package-private */
    public final zzdwp<Map.Entry<K, V>> zzaxq() {
        return new zzdxa(this);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.zzhrd.get(key))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}

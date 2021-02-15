package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzer<K, V> extends zzej<Map.Entry<K, V>> {
    /* access modifiers changed from: private */
    public final transient int size;
    private final transient zzef<K, V> zznc;
    /* access modifiers changed from: private */
    public final transient Object[] zznd;
    private final transient int zznm = 0;

    zzer(zzef<K, V> zzef, Object[] objArr, int i, int i2) {
        this.zznc = zzef;
        this.zznd = objArr;
        this.size = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcu() {
        return true;
    }

    public final zzfa<Map.Entry<K, V>> zzcp() {
        return (zzfa) zzct().iterator();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return zzct().zza(objArr, i);
    }

    /* access modifiers changed from: package-private */
    public final zzee<Map.Entry<K, V>> zzda() {
        return new zzeu(this);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.zznc.get(key))) {
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

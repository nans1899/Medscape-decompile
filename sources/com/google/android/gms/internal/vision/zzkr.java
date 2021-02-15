package com.google.android.gms.internal.vision;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
class zzkr extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzkg zzabv;

    private zzkr(zzkg zzkg) {
        this.zzabv = zzkg;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzko(this.zzabv, (zzkj) null);
    }

    public int size() {
        return this.zzabv.size();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzabv.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzabv.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzabv.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzabv.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzkr(zzkg zzkg, zzkj zzkj) {
        this(zzkg);
    }
}

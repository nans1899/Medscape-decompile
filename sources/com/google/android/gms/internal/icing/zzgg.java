package com.google.android.gms.internal.icing;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
class zzgg extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzfz zznx;

    private zzgg(zzfz zzfz) {
        this.zznx = zzfz;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzgh(this.zznx, (zzfy) null);
    }

    public int size() {
        return this.zznx.size();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zznx.get(entry.getKey());
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
        this.zznx.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zznx.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zznx.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzgg(zzfz zzfz, zzfy zzfy) {
        this(zzfz);
    }
}

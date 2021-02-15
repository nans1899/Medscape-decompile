package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
class zzemr extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzemk zzinm;

    private zzemr(zzemk zzemk) {
        this.zzinm = zzemk;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzems(this.zzinm, (zzemj) null);
    }

    public int size() {
        return this.zzinm.size();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzinm.get(entry.getKey());
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
        this.zzinm.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzinm.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzinm.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzemr(zzemk zzemk, zzemj zzemj) {
        this(zzemk);
    }
}

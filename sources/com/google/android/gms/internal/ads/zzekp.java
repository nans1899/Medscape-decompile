package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzekp<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzikr;

    public zzekp(Iterator<Map.Entry<K, Object>> it) {
        this.zzikr = it;
    }

    public final boolean hasNext() {
        return this.zzikr.hasNext();
    }

    public final void remove() {
        this.zzikr.remove();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzikr.next();
        return next.getValue() instanceof zzeko ? new zzekq(next) : next;
    }
}

package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzit<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzzq;

    public zzit(Iterator<Map.Entry<K, Object>> it) {
        this.zzzq = it;
    }

    public final boolean hasNext() {
        return this.zzzq.hasNext();
    }

    public final void remove() {
        this.zzzq.remove();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzzq.next();
        return next.getValue() instanceof zzio ? new zziq(next) : next;
    }
}

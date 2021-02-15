package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzen<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzlw;

    public zzen(Iterator<Map.Entry<K, Object>> it) {
        this.zzlw = it;
    }

    public final boolean hasNext() {
        return this.zzlw.hasNext();
    }

    public final void remove() {
        this.zzlw.remove();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzlw.next();
        return next.getValue() instanceof zzei ? new zzek(next) : next;
    }
}

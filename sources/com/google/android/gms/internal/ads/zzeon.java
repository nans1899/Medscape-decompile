package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzeon implements Iterator<E> {
    private int pos = 0;
    private final /* synthetic */ zzeoo zziub;

    zzeon(zzeoo zzeoo) {
        this.zziub = zzeoo;
    }

    public final boolean hasNext() {
        return this.pos < this.zziub.zziuc.size() || this.zziub.zziud.hasNext();
    }

    public final E next() {
        while (this.pos >= this.zziub.zziuc.size()) {
            this.zziub.zziuc.add(this.zziub.zziud.next());
        }
        List<E> list = this.zziub.zziuc;
        int i = this.pos;
        this.pos = i + 1;
        return list.get(i);
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

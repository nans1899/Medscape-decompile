package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzene implements Iterator<String> {
    private final /* synthetic */ zzenc zzinx;
    private Iterator<String> zziot = this.zzinx.zziny.iterator();

    zzene(zzenc zzenc) {
        this.zzinx = zzenc;
    }

    public final boolean hasNext() {
        return this.zziot.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return this.zziot.next();
    }
}

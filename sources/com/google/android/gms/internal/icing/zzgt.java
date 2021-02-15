package com.google.android.gms.internal.icing;

import java.util.Iterator;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzgt implements Iterator<String> {
    private final /* synthetic */ zzgr zzoj;
    private Iterator<String> zzpf = this.zzoj.zzok.iterator();

    zzgt(zzgr zzgr) {
        this.zzoj = zzgr;
    }

    public final boolean hasNext() {
        return this.zzpf.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return this.zzpf.next();
    }
}

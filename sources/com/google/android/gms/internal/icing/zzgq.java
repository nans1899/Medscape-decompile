package com.google.android.gms.internal.icing;

import java.util.ListIterator;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzgq implements ListIterator<String> {
    private ListIterator<String> zzoh = this.zzoj.zzok.listIterator(this.zzoi);
    private final /* synthetic */ int zzoi;
    private final /* synthetic */ zzgr zzoj;

    zzgq(zzgr zzgr, int i) {
        this.zzoj = zzgr;
        this.zzoi = i;
    }

    public final boolean hasNext() {
        return this.zzoh.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzoh.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzoh.nextIndex();
    }

    public final int previousIndex() {
        return this.zzoh.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object previous() {
        return this.zzoh.previous();
    }

    public final /* synthetic */ Object next() {
        return this.zzoh.next();
    }
}

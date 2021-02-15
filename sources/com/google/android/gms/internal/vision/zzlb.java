package com.google.android.gms.internal.vision;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzlb implements ListIterator<String> {
    private final /* synthetic */ zzky zzacg;
    private ListIterator<String> zzach = this.zzacg.zzace.listIterator(this.zzaci);
    private final /* synthetic */ int zzaci;

    zzlb(zzky zzky, int i) {
        this.zzacg = zzky;
        this.zzaci = i;
    }

    public final boolean hasNext() {
        return this.zzach.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzach.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzach.nextIndex();
    }

    public final int previousIndex() {
        return this.zzach.previousIndex();
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
        return this.zzach.previous();
    }

    public final /* synthetic */ Object next() {
        return this.zzach.next();
    }
}

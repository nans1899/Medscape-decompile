package com.google.android.gms.internal.ads;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzenb implements ListIterator<String> {
    private final /* synthetic */ int zzhso;
    private ListIterator<String> zzinw = this.zzinx.zziny.listIterator(this.zzhso);
    private final /* synthetic */ zzenc zzinx;

    zzenb(zzenc zzenc, int i) {
        this.zzinx = zzenc;
        this.zzhso = i;
    }

    public final boolean hasNext() {
        return this.zzinw.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzinw.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzinw.nextIndex();
    }

    public final int previousIndex() {
        return this.zzinw.previousIndex();
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
        return this.zzinw.previous();
    }

    public final /* synthetic */ Object next() {
        return this.zzinw.next();
    }
}

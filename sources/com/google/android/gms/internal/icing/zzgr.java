package com.google.android.gms.internal.icing;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzgr extends AbstractList<String> implements zzeo, RandomAccess {
    /* access modifiers changed from: private */
    public final zzeo zzok;

    public zzgr(zzeo zzeo) {
        this.zzok = zzeo;
    }

    public final zzeo zzce() {
        return this;
    }

    public final Object zzad(int i) {
        return this.zzok.zzad(i);
    }

    public final int size() {
        return this.zzok.size();
    }

    public final void zzc(zzct zzct) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzgq(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzgt(this);
    }

    public final List<?> zzcd() {
        return this.zzok.zzcd();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzok.get(i);
    }
}

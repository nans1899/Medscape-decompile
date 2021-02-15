package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzky extends AbstractList<String> implements zziu, RandomAccess {
    /* access modifiers changed from: private */
    public final zziu zzace;

    public zzky(zziu zziu) {
        this.zzace = zziu;
    }

    public final zziu zzht() {
        return this;
    }

    public final Object zzbt(int i) {
        return this.zzace.zzbt(i);
    }

    public final int size() {
        return this.zzace.size();
    }

    public final void zzc(zzgs zzgs) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzlb(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzla(this);
    }

    public final List<?> zzhs() {
        return this.zzace.zzhs();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzace.get(i);
    }
}

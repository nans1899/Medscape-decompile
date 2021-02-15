package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzenc extends AbstractList<String> implements zzeku, RandomAccess {
    /* access modifiers changed from: private */
    public final zzeku zziny;

    public zzenc(zzeku zzeku) {
        this.zziny = zzeku;
    }

    public final zzeku zzbhk() {
        return this;
    }

    public final Object zzhe(int i) {
        return this.zziny.zzhe(i);
    }

    public final int size() {
        return this.zziny.size();
    }

    public final void zzaj(zzeip zzeip) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzenb(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzene(this);
    }

    public final List<?> zzbhj() {
        return this.zziny.zzbhj();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zziny.get(i);
    }
}

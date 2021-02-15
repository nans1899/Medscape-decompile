package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzekr extends zzeij<String> implements zzeku, RandomAccess {
    private static final zzekr zzikt;
    private static final zzeku zziku = zzikt;
    private final List<Object> zzikv;

    public zzekr() {
        this(10);
    }

    public zzekr(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzekr(ArrayList<Object> arrayList) {
        this.zzikv = arrayList;
    }

    public final int size() {
        return this.zzikv.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzbed();
        if (collection instanceof zzeku) {
            collection = ((zzeku) collection).zzbhj();
        }
        boolean addAll = this.zzikv.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzbed();
        this.zzikv.clear();
        this.modCount++;
    }

    public final void zzaj(zzeip zzeip) {
        zzbed();
        this.zzikv.add(zzeip);
        this.modCount++;
    }

    public final Object zzhe(int i) {
        return this.zzikv.get(i);
    }

    private static String zzal(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzeip) {
            return ((zzeip) obj).zzbef();
        }
        return zzekb.zzz((byte[]) obj);
    }

    public final List<?> zzbhj() {
        return Collections.unmodifiableList(this.zzikv);
    }

    public final zzeku zzbhk() {
        return zzbeb() ? new zzenc(this) : this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        zzbed();
        return zzal(this.zzikv.set(i, (String) obj));
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbed();
        Object remove = this.zzikv.remove(i);
        this.modCount++;
        return zzal(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzbeb() {
        return super.zzbeb();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzbed();
        this.zzikv.add(i, (String) obj);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ zzekk zzft(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzikv);
            return new zzekr((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzikv.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzeip) {
            zzeip zzeip = (zzeip) obj;
            String zzbef = zzeip.zzbef();
            if (zzeip.zzbeg()) {
                this.zzikv.set(i, zzbef);
            }
            return zzbef;
        }
        byte[] bArr = (byte[]) obj;
        String zzz = zzekb.zzz(bArr);
        if (zzekb.zzy(bArr)) {
            this.zzikv.set(i, zzz);
        }
        return zzz;
    }

    static {
        zzekr zzekr = new zzekr();
        zzikt = zzekr;
        zzekr.zzbec();
    }
}

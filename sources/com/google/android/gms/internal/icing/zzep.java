package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzep extends zzcp<String> implements zzeo, RandomAccess {
    private static final zzep zzlx;
    private static final zzeo zzly = zzlx;
    private final List<Object> zzlz;

    public zzep() {
        this(10);
    }

    public zzep(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzep(ArrayList<Object> arrayList) {
        this.zzlz = arrayList;
    }

    public final int size() {
        return this.zzlz.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzaj();
        if (collection instanceof zzeo) {
            collection = ((zzeo) collection).zzcd();
        }
        boolean addAll = this.zzlz.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzaj();
        this.zzlz.clear();
        this.modCount++;
    }

    public final void zzc(zzct zzct) {
        zzaj();
        this.zzlz.add(zzct);
        this.modCount++;
    }

    public final Object zzad(int i) {
        return this.zzlz.get(i);
    }

    private static String zzh(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzct) {
            return ((zzct) obj).zzan();
        }
        return zzeb.zze((byte[]) obj);
    }

    public final List<?> zzcd() {
        return Collections.unmodifiableList(this.zzlz);
    }

    public final zzeo zzce() {
        return zzah() ? new zzgr(this) : this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        zzaj();
        return zzh(this.zzlz.set(i, (String) obj));
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
        zzaj();
        Object remove = this.zzlz.remove(i);
        this.modCount++;
        return zzh(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzah() {
        return super.zzah();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzaj();
        this.zzlz.add(i, (String) obj);
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

    public final /* synthetic */ zzee zzj(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzlz);
            return new zzep((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzlz.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzct) {
            zzct zzct = (zzct) obj;
            String zzan = zzct.zzan();
            if (zzct.zzao()) {
                this.zzlz.set(i, zzan);
            }
            return zzan;
        }
        byte[] bArr = (byte[]) obj;
        String zze = zzeb.zze(bArr);
        if (zzeb.zzd(bArr)) {
            this.zzlz.set(i, zze);
        }
        return zze;
    }

    static {
        zzep zzep = new zzep();
        zzlx = zzep;
        zzep.zzai();
    }
}

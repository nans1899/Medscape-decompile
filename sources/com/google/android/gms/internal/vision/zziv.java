package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zziv extends zzgi<String> implements zziu, RandomAccess {
    private static final zziv zzzr;
    private static final zziu zzzs = zzzr;
    private final List<Object> zzzt;

    public zziv() {
        this(10);
    }

    public zziv(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zziv(ArrayList<Object> arrayList) {
        this.zzzt = arrayList;
    }

    public final int size() {
        return this.zzzt.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzek();
        if (collection instanceof zziu) {
            collection = ((zziu) collection).zzhs();
        }
        boolean addAll = this.zzzt.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzek();
        this.zzzt.clear();
        this.modCount++;
    }

    public final void zzc(zzgs zzgs) {
        zzek();
        this.zzzt.add(zzgs);
        this.modCount++;
    }

    public final Object zzbt(int i) {
        return this.zzzt.get(i);
    }

    private static String zzm(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzgs) {
            return ((zzgs) obj).zzfl();
        }
        return zzie.zzh((byte[]) obj);
    }

    public final List<?> zzhs() {
        return Collections.unmodifiableList(this.zzzt);
    }

    public final zziu zzht() {
        return zzei() ? new zzky(this) : this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        zzek();
        return zzm(this.zzzt.set(i, (String) obj));
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
        zzek();
        Object remove = this.zzzt.remove(i);
        this.modCount++;
        return zzm(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzei() {
        return super.zzei();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzek();
        this.zzzt.add(i, (String) obj);
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

    public final /* synthetic */ zzik zzan(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzzt);
            return new zziv((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzzt.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzgs) {
            zzgs zzgs = (zzgs) obj;
            String zzfl = zzgs.zzfl();
            if (zzgs.zzfm()) {
                this.zzzt.set(i, zzfl);
            }
            return zzfl;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzie.zzh(bArr);
        if (zzie.zzg(bArr)) {
            this.zzzt.set(i, zzh);
        }
        return zzh;
    }

    static {
        zziv zziv = new zziv();
        zzzr = zziv;
        zziv.zzej();
    }
}

package com.google.android.gms.internal.icing;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
class zzfz<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzhl;
    private final int zznr;
    /* access modifiers changed from: private */
    public List<zzge> zzns;
    /* access modifiers changed from: private */
    public Map<K, V> zznt;
    private volatile zzgg zznu;
    /* access modifiers changed from: private */
    public Map<K, V> zznv;
    private volatile zzga zznw;

    static <FieldDescriptorType extends zzdu<FieldDescriptorType>> zzfz<FieldDescriptorType, Object> zzai(int i) {
        return new zzfy(i);
    }

    private zzfz(int i) {
        this.zznr = i;
        this.zzns = Collections.emptyList();
        this.zznt = Collections.emptyMap();
        this.zznv = Collections.emptyMap();
    }

    public void zzai() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzhl) {
            if (this.zznt.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zznt);
            }
            this.zznt = map;
            if (this.zznv.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zznv);
            }
            this.zznv = map2;
            this.zzhl = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzhl;
    }

    public final int zzdd() {
        return this.zzns.size();
    }

    public final Map.Entry<K, V> zzaj(int i) {
        return this.zzns.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzde() {
        if (this.zznt.isEmpty()) {
            return zzgd.zzdj();
        }
        return this.zznt.entrySet();
    }

    public int size() {
        return this.zzns.size() + this.zznt.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zznt.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return this.zzns.get(zza).getValue();
        }
        return this.zznt.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzdg();
        int zza = zza(k);
        if (zza >= 0) {
            return this.zzns.get(zza).setValue(v);
        }
        zzdg();
        if (this.zzns.isEmpty() && !(this.zzns instanceof ArrayList)) {
            this.zzns = new ArrayList(this.zznr);
        }
        int i = -(zza + 1);
        if (i >= this.zznr) {
            return zzdh().put(k, v);
        }
        int size = this.zzns.size();
        int i2 = this.zznr;
        if (size == i2) {
            zzge remove = this.zzns.remove(i2 - 1);
            zzdh().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzns.add(i, new zzge(this, k, v));
        return null;
    }

    public void clear() {
        zzdg();
        if (!this.zzns.isEmpty()) {
            this.zzns.clear();
        }
        if (!this.zznt.isEmpty()) {
            this.zznt.clear();
        }
    }

    public V remove(Object obj) {
        zzdg();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzak(zza);
        }
        if (this.zznt.isEmpty()) {
            return null;
        }
        return this.zznt.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzak(int i) {
        zzdg();
        V value = this.zzns.remove(i).getValue();
        if (!this.zznt.isEmpty()) {
            Iterator it = zzdh().entrySet().iterator();
            this.zzns.add(new zzge(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zzns.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzns.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzns.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zznu == null) {
            this.zznu = new zzgg(this, (zzfy) null);
        }
        return this.zznu;
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzdf() {
        if (this.zznw == null) {
            this.zznw = new zzga(this, (zzfy) null);
        }
        return this.zznw;
    }

    /* access modifiers changed from: private */
    public final void zzdg() {
        if (this.zzhl) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzdh() {
        zzdg();
        if (this.zznt.isEmpty() && !(this.zznt instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zznt = treeMap;
            this.zznv = treeMap.descendingMap();
        }
        return (SortedMap) this.zznt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfz)) {
            return super.equals(obj);
        }
        zzfz zzfz = (zzfz) obj;
        int size = size();
        if (size != zzfz.size()) {
            return false;
        }
        int zzdd = zzdd();
        if (zzdd != zzfz.zzdd()) {
            return entrySet().equals(zzfz.entrySet());
        }
        for (int i = 0; i < zzdd; i++) {
            if (!zzaj(i).equals(zzfz.zzaj(i))) {
                return false;
            }
        }
        if (zzdd != size) {
            return this.zznt.equals(zzfz.zznt);
        }
        return true;
    }

    public int hashCode() {
        int zzdd = zzdd();
        int i = 0;
        for (int i2 = 0; i2 < zzdd; i2++) {
            i += this.zzns.get(i2).hashCode();
        }
        return this.zznt.size() > 0 ? i + this.zznt.hashCode() : i;
    }

    /* synthetic */ zzfz(int i, zzfy zzfy) {
        this(i);
    }
}

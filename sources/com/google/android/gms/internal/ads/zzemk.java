package com.google.android.gms.internal.ads;

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

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
class zzemk<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzigi;
    private final int zzing;
    /* access modifiers changed from: private */
    public List<zzemp> zzinh;
    /* access modifiers changed from: private */
    public Map<K, V> zzini;
    private volatile zzemr zzinj;
    /* access modifiers changed from: private */
    public Map<K, V> zzink;
    private volatile zzeml zzinl;

    static <FieldDescriptorType extends zzeju<FieldDescriptorType>> zzemk<FieldDescriptorType, Object> zzho(int i) {
        return new zzemj(i);
    }

    private zzemk(int i) {
        this.zzing = i;
        this.zzinh = Collections.emptyList();
        this.zzini = Collections.emptyMap();
        this.zzink = Collections.emptyMap();
    }

    public void zzbec() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzigi) {
            if (this.zzini.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzini);
            }
            this.zzini = map;
            if (this.zzink.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzink);
            }
            this.zzink = map2;
            this.zzigi = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzigi;
    }

    public final int zzbim() {
        return this.zzinh.size();
    }

    public final Map.Entry<K, V> zzhp(int i) {
        return this.zzinh.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzbin() {
        if (this.zzini.isEmpty()) {
            return zzemo.zzbis();
        }
        return this.zzini.entrySet();
    }

    public int size() {
        return this.zzinh.size() + this.zzini.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzini.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return this.zzinh.get(zza).getValue();
        }
        return this.zzini.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzbip();
        int zza = zza(k);
        if (zza >= 0) {
            return this.zzinh.get(zza).setValue(v);
        }
        zzbip();
        if (this.zzinh.isEmpty() && !(this.zzinh instanceof ArrayList)) {
            this.zzinh = new ArrayList(this.zzing);
        }
        int i = -(zza + 1);
        if (i >= this.zzing) {
            return zzbiq().put(k, v);
        }
        int size = this.zzinh.size();
        int i2 = this.zzing;
        if (size == i2) {
            zzemp remove = this.zzinh.remove(i2 - 1);
            zzbiq().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzinh.add(i, new zzemp(this, k, v));
        return null;
    }

    public void clear() {
        zzbip();
        if (!this.zzinh.isEmpty()) {
            this.zzinh.clear();
        }
        if (!this.zzini.isEmpty()) {
            this.zzini.clear();
        }
    }

    public V remove(Object obj) {
        zzbip();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzhq(zza);
        }
        if (this.zzini.isEmpty()) {
            return null;
        }
        return this.zzini.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzhq(int i) {
        zzbip();
        V value = this.zzinh.remove(i).getValue();
        if (!this.zzini.isEmpty()) {
            Iterator it = zzbiq().entrySet().iterator();
            this.zzinh.add(new zzemp(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zzinh.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzinh.get(size).getKey());
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
            int compareTo2 = k.compareTo((Comparable) this.zzinh.get(i2).getKey());
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
        if (this.zzinj == null) {
            this.zzinj = new zzemr(this, (zzemj) null);
        }
        return this.zzinj;
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzbio() {
        if (this.zzinl == null) {
            this.zzinl = new zzeml(this, (zzemj) null);
        }
        return this.zzinl;
    }

    /* access modifiers changed from: private */
    public final void zzbip() {
        if (this.zzigi) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzbiq() {
        zzbip();
        if (this.zzini.isEmpty() && !(this.zzini instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzini = treeMap;
            this.zzink = treeMap.descendingMap();
        }
        return (SortedMap) this.zzini;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzemk)) {
            return super.equals(obj);
        }
        zzemk zzemk = (zzemk) obj;
        int size = size();
        if (size != zzemk.size()) {
            return false;
        }
        int zzbim = zzbim();
        if (zzbim != zzemk.zzbim()) {
            return entrySet().equals(zzemk.entrySet());
        }
        for (int i = 0; i < zzbim; i++) {
            if (!zzhp(i).equals(zzemk.zzhp(i))) {
                return false;
            }
        }
        if (zzbim != size) {
            return this.zzini.equals(zzemk.zzini);
        }
        return true;
    }

    public int hashCode() {
        int zzbim = zzbim();
        int i = 0;
        for (int i2 = 0; i2 < zzbim; i2++) {
            i += this.zzinh.get(i2).hashCode();
        }
        return this.zzini.size() > 0 ? i + this.zzini.hashCode() : i;
    }

    /* synthetic */ zzemk(int i, zzemj zzemj) {
        this(i);
    }
}

package com.google.android.gms.internal.vision;

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

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
class zzkg<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zzabk;
    /* access modifiers changed from: private */
    public List<zzkp> zzabl;
    /* access modifiers changed from: private */
    public Map<K, V> zzabm;
    private volatile zzkr zzabn;
    /* access modifiers changed from: private */
    public Map<K, V> zzabo;
    private volatile zzkl zzabp;
    private boolean zzuy;

    static <FieldDescriptorType extends zzhv<FieldDescriptorType>> zzkg<FieldDescriptorType, Object> zzcb(int i) {
        return new zzkj(i);
    }

    private zzkg(int i) {
        this.zzabk = i;
        this.zzabl = Collections.emptyList();
        this.zzabm = Collections.emptyMap();
        this.zzabo = Collections.emptyMap();
    }

    public void zzej() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzuy) {
            if (this.zzabm.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzabm);
            }
            this.zzabm = map;
            if (this.zzabo.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzabo);
            }
            this.zzabo = map2;
            this.zzuy = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzuy;
    }

    public final int zzin() {
        return this.zzabl.size();
    }

    public final Map.Entry<K, V> zzcc(int i) {
        return this.zzabl.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzio() {
        if (this.zzabm.isEmpty()) {
            return zzkk.zziy();
        }
        return this.zzabm.entrySet();
    }

    public int size() {
        return this.zzabl.size() + this.zzabm.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzabm.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return this.zzabl.get(zza).getValue();
        }
        return this.zzabm.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zziq();
        int zza = zza(k);
        if (zza >= 0) {
            return this.zzabl.get(zza).setValue(v);
        }
        zziq();
        if (this.zzabl.isEmpty() && !(this.zzabl instanceof ArrayList)) {
            this.zzabl = new ArrayList(this.zzabk);
        }
        int i = -(zza + 1);
        if (i >= this.zzabk) {
            return zzir().put(k, v);
        }
        int size = this.zzabl.size();
        int i2 = this.zzabk;
        if (size == i2) {
            zzkp remove = this.zzabl.remove(i2 - 1);
            zzir().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzabl.add(i, new zzkp(this, k, v));
        return null;
    }

    public void clear() {
        zziq();
        if (!this.zzabl.isEmpty()) {
            this.zzabl.clear();
        }
        if (!this.zzabm.isEmpty()) {
            this.zzabm.clear();
        }
    }

    public V remove(Object obj) {
        zziq();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzcd(zza);
        }
        if (this.zzabm.isEmpty()) {
            return null;
        }
        return this.zzabm.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzcd(int i) {
        zziq();
        V value = this.zzabl.remove(i).getValue();
        if (!this.zzabm.isEmpty()) {
            Iterator it = zzir().entrySet().iterator();
            this.zzabl.add(new zzkp(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zzabl.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzabl.get(size).getKey());
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
            int compareTo2 = k.compareTo((Comparable) this.zzabl.get(i2).getKey());
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
        if (this.zzabn == null) {
            this.zzabn = new zzkr(this, (zzkj) null);
        }
        return this.zzabn;
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzip() {
        if (this.zzabp == null) {
            this.zzabp = new zzkl(this, (zzkj) null);
        }
        return this.zzabp;
    }

    /* access modifiers changed from: private */
    public final void zziq() {
        if (this.zzuy) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzir() {
        zziq();
        if (this.zzabm.isEmpty() && !(this.zzabm instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzabm = treeMap;
            this.zzabo = treeMap.descendingMap();
        }
        return (SortedMap) this.zzabm;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkg)) {
            return super.equals(obj);
        }
        zzkg zzkg = (zzkg) obj;
        int size = size();
        if (size != zzkg.size()) {
            return false;
        }
        int zzin = zzin();
        if (zzin != zzkg.zzin()) {
            return entrySet().equals(zzkg.entrySet());
        }
        for (int i = 0; i < zzin; i++) {
            if (!zzcc(i).equals(zzkg.zzcc(i))) {
                return false;
            }
        }
        if (zzin != size) {
            return this.zzabm.equals(zzkg.zzabm);
        }
        return true;
    }

    public int hashCode() {
        int zzin = zzin();
        int i = 0;
        for (int i2 = 0; i2 < zzin; i2++) {
            i += this.zzabl.get(i2).hashCode();
        }
        return this.zzabm.size() > 0 ? i + this.zzabm.hashCode() : i;
    }

    /* synthetic */ zzkg(int i, zzkj zzkj) {
        this(i);
    }
}

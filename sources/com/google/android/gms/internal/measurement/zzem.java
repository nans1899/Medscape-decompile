package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzem<K, V> extends AbstractMap<K, V> implements Serializable {
    /* access modifiers changed from: private */
    public static final Object zzd = new Object();
    @NullableDecl
    transient int[] zza;
    @NullableDecl
    transient Object[] zzb;
    @NullableDecl
    transient Object[] zzc;
    /* access modifiers changed from: private */
    @NullableDecl
    public transient Object zze;
    /* access modifiers changed from: private */
    public transient int zzf = zzfz.zza(3, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    private transient int zzg;
    @NullableDecl
    private transient Set<K> zzh;
    @NullableDecl
    private transient Set<Map.Entry<K, V>> zzi;
    @NullableDecl
    private transient Collection<V> zzj;

    zzem() {
        zzeb.zza(true, (Object) "Expected size must be >= 0");
    }

    static int zzb(int i, int i2) {
        return i - 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza() {
        return this.zze == null;
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public final Map<K, V> zzb() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    private final void zzb(int i) {
        this.zzf = zzex.zza(this.zzf, 32 - Integer.numberOfLeadingZeros(i), 31);
    }

    /* access modifiers changed from: private */
    public final int zzi() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        this.zzf += 32;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x002c, code lost:
        r7 = r7 << 1;
     */
    @org.checkerframework.checker.nullness.compatqual.NullableDecl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V put(@org.checkerframework.checker.nullness.compatqual.NullableDecl K r19, @org.checkerframework.checker.nullness.compatqual.NullableDecl V r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r18.zza()
            r4 = 1
            if (r3 == 0) goto L_0x004e
            boolean r3 = r18.zza()
            java.lang.String r5 = "Arrays already allocated"
            com.google.android.gms.internal.measurement.zzeb.zzb((boolean) r3, (java.lang.Object) r5)
            int r3 = r0.zzf
            r5 = 4
            int r6 = r3 + 1
            r7 = 2
            int r6 = java.lang.Math.max(r6, r7)
            int r7 = java.lang.Integer.highestOneBit(r6)
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r10 = (double) r7
            double r10 = r10 * r8
            int r8 = (int) r10
            if (r6 <= r8) goto L_0x0033
            int r7 = r7 << 1
            if (r7 > 0) goto L_0x0033
            r6 = 1073741824(0x40000000, float:2.0)
            goto L_0x0034
        L_0x0033:
            r6 = r7
        L_0x0034:
            int r5 = java.lang.Math.max(r5, r6)
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzex.zza(r5)
            r0.zze = r6
            int r5 = r5 - r4
            r0.zzb((int) r5)
            int[] r5 = new int[r3]
            r0.zza = r5
            java.lang.Object[] r5 = new java.lang.Object[r3]
            r0.zzb = r5
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r0.zzc = r3
        L_0x004e:
            java.util.Map r3 = r18.zzb()
            if (r3 == 0) goto L_0x0059
            java.lang.Object r1 = r3.put(r1, r2)
            return r1
        L_0x0059:
            int[] r3 = r0.zza
            java.lang.Object[] r5 = r0.zzb
            java.lang.Object[] r6 = r0.zzc
            int r7 = r0.zzg
            int r8 = r7 + 1
            int r9 = com.google.android.gms.internal.measurement.zzez.zza((java.lang.Object) r19)
            int r10 = r18.zzi()
            r11 = r9 & r10
            java.lang.Object r12 = r0.zze
            int r12 = com.google.android.gms.internal.measurement.zzex.zza(r12, r11)
            if (r12 != 0) goto L_0x0086
            if (r8 <= r10) goto L_0x0080
            int r3 = com.google.android.gms.internal.measurement.zzex.zzb(r10)
            int r10 = r0.zza(r10, r3, r9, r7)
            goto L_0x00ef
        L_0x0080:
            java.lang.Object r3 = r0.zze
            com.google.android.gms.internal.measurement.zzex.zza((java.lang.Object) r3, (int) r11, (int) r8)
            goto L_0x00ef
        L_0x0086:
            int r11 = ~r10
            r15 = r9 & r11
            r16 = 0
        L_0x008b:
            int r12 = r12 - r4
            r13 = r3[r12]
            r14 = r13 & r11
            if (r14 != r15) goto L_0x009f
            r14 = r5[r12]
            boolean r14 = com.google.android.gms.internal.measurement.zzdz.zza(r1, r14)
            if (r14 == 0) goto L_0x009f
            r1 = r6[r12]
            r6[r12] = r2
            return r1
        L_0x009f:
            r14 = r13 & r10
            r17 = r5
            int r5 = r16 + 1
            if (r14 != 0) goto L_0x0135
            r6 = 9
            if (r5 < r6) goto L_0x00de
            int r3 = r18.zzi()
            int r3 = r3 + r4
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            r5 = 1065353216(0x3f800000, float:1.0)
            r4.<init>(r3, r5)
            int r3 = r18.zzd()
        L_0x00bb:
            if (r3 < 0) goto L_0x00cd
            java.lang.Object[] r5 = r0.zzb
            r5 = r5[r3]
            java.lang.Object[] r6 = r0.zzc
            r6 = r6[r3]
            r4.put(r5, r6)
            int r3 = r0.zza((int) r3)
            goto L_0x00bb
        L_0x00cd:
            r0.zze = r4
            r3 = 0
            r0.zza = r3
            r0.zzb = r3
            r0.zzc = r3
            r18.zzc()
            java.lang.Object r1 = r4.put(r1, r2)
            return r1
        L_0x00de:
            if (r8 <= r10) goto L_0x00e9
            int r3 = com.google.android.gms.internal.measurement.zzex.zzb(r10)
            int r10 = r0.zza(r10, r3, r9, r7)
            goto L_0x00ef
        L_0x00e9:
            int r5 = com.google.android.gms.internal.measurement.zzex.zza((int) r13, (int) r8, (int) r10)
            r3[r12] = r5
        L_0x00ef:
            int[] r3 = r0.zza
            int r3 = r3.length
            if (r8 <= r3) goto L_0x011d
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            int r6 = r3 >>> 1
            int r6 = java.lang.Math.max(r4, r6)
            int r6 = r6 + r3
            r4 = r4 | r6
            int r4 = java.lang.Math.min(r5, r4)
            if (r4 == r3) goto L_0x011d
            int[] r3 = r0.zza
            int[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.zza = r3
            java.lang.Object[] r3 = r0.zzb
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.zzb = r3
            java.lang.Object[] r3 = r0.zzc
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.zzc = r3
        L_0x011d:
            int[] r3 = r0.zza
            r12 = 0
            int r4 = com.google.android.gms.internal.measurement.zzex.zza((int) r9, (int) r12, (int) r10)
            r3[r7] = r4
            java.lang.Object[] r3 = r0.zzb
            r3[r7] = r1
            java.lang.Object[] r1 = r0.zzc
            r1[r7] = r2
            r0.zzg = r8
            r18.zzc()
            r13 = 0
            return r13
        L_0x0135:
            r16 = r5
            r12 = r14
            r5 = r17
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzem.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    private final int zza(int i, int i2, int i3, int i4) {
        Object zza2 = zzex.zza(i2);
        int i5 = i2 - 1;
        if (i4 != 0) {
            zzex.zza(zza2, i3 & i5, i4 + 1);
        }
        Object obj = this.zze;
        int[] iArr = this.zza;
        for (int i6 = 0; i6 <= i; i6++) {
            int zza3 = zzex.zza(obj, i6);
            while (zza3 != 0) {
                int i7 = zza3 - 1;
                int i8 = iArr[i7];
                int i9 = ((~i) & i8) | i6;
                int i10 = i9 & i5;
                int zza4 = zzex.zza(zza2, i10);
                zzex.zza(zza2, i10, zza3);
                iArr[i7] = zzex.zza(i9, zza4, i5);
                zza3 = i8 & i;
            }
        }
        this.zze = zza2;
        zzb(i5);
        return i5;
    }

    /* access modifiers changed from: private */
    public final int zza(@NullableDecl Object obj) {
        if (zza()) {
            return -1;
        }
        int zza2 = zzez.zza(obj);
        int zzi2 = zzi();
        int zza3 = zzex.zza(this.zze, zza2 & zzi2);
        if (zza3 == 0) {
            return -1;
        }
        int i = ~zzi2;
        int i2 = zza2 & i;
        do {
            int i3 = zza3 - 1;
            int i4 = this.zza[i3];
            if ((i4 & i) == i2 && zzdz.zza(obj, this.zzb[i3])) {
                return i3;
            }
            zza3 = i4 & zzi2;
        } while (zza3 != 0);
        return -1;
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        Map zzb2 = zzb();
        if (zzb2 != null) {
            return zzb2.containsKey(obj);
        }
        return zza(obj) != -1;
    }

    public final V get(@NullableDecl Object obj) {
        Map zzb2 = zzb();
        if (zzb2 != null) {
            return zzb2.get(obj);
        }
        int zza2 = zza(obj);
        if (zza2 == -1) {
            return null;
        }
        return this.zzc[zza2];
    }

    @NullableDecl
    public final V remove(@NullableDecl Object obj) {
        Map zzb2 = zzb();
        if (zzb2 != null) {
            return zzb2.remove(obj);
        }
        V zzb3 = zzb(obj);
        if (zzb3 == zzd) {
            return null;
        }
        return zzb3;
    }

    /* access modifiers changed from: private */
    @NullableDecl
    public final Object zzb(@NullableDecl Object obj) {
        if (zza()) {
            return zzd;
        }
        int zzi2 = zzi();
        int zza2 = zzex.zza(obj, (Object) null, zzi2, this.zze, this.zza, this.zzb, (Object[]) null);
        if (zza2 == -1) {
            return zzd;
        }
        Object obj2 = this.zzc[zza2];
        zza(zza2, zzi2);
        this.zzg--;
        zzc();
        return obj2;
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, int i2) {
        int size = size() - 1;
        if (i < size) {
            Object[] objArr = this.zzb;
            Object obj = objArr[size];
            objArr[i] = obj;
            Object[] objArr2 = this.zzc;
            objArr2[i] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            int[] iArr = this.zza;
            iArr[i] = iArr[size];
            iArr[size] = 0;
            int zza2 = zzez.zza(obj) & i2;
            int zza3 = zzex.zza(this.zze, zza2);
            int i3 = size + 1;
            if (zza3 == i3) {
                zzex.zza(this.zze, zza2, i + 1);
                return;
            }
            while (true) {
                int i4 = zza3 - 1;
                int[] iArr2 = this.zza;
                int i5 = iArr2[i4];
                int i6 = i5 & i2;
                if (i6 == i3) {
                    iArr2[i4] = zzex.zza(i5, i + 1, i2);
                    return;
                }
                zza3 = i6;
            }
        } else {
            this.zzb[i] = null;
            this.zzc[i] = null;
            this.zza[i] = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzd() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public final int zza(int i) {
        int i2 = i + 1;
        if (i2 < this.zzg) {
            return i2;
        }
        return -1;
    }

    public final Set<K> keySet() {
        Set<K> set = this.zzh;
        if (set != null) {
            return set;
        }
        zzes zzes = new zzes(this);
        this.zzh = zzes;
        return zzes;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<K> zze() {
        Map zzb2 = zzb();
        if (zzb2 != null) {
            return zzb2.keySet().iterator();
        }
        return new zzep(this);
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.zzi;
        if (set != null) {
            return set;
        }
        zzeq zzeq = new zzeq(this);
        this.zzi = zzeq;
        return zzeq;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<K, V>> zzf() {
        Map zzb2 = zzb();
        if (zzb2 != null) {
            return zzb2.entrySet().iterator();
        }
        return new zzeo(this);
    }

    public final int size() {
        Map zzb2 = zzb();
        return zzb2 != null ? zzb2.size() : this.zzg;
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        Map zzb2 = zzb();
        if (zzb2 != null) {
            return zzb2.containsValue(obj);
        }
        for (int i = 0; i < this.zzg; i++) {
            if (zzdz.zza(obj, this.zzc[i])) {
                return true;
            }
        }
        return false;
    }

    public final Collection<V> values() {
        Collection<V> collection = this.zzj;
        if (collection != null) {
            return collection;
        }
        zzeu zzeu = new zzeu(this);
        this.zzj = zzeu;
        return zzeu;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<V> zzg() {
        Map zzb2 = zzb();
        if (zzb2 != null) {
            return zzb2.values().iterator();
        }
        return new zzer(this);
    }

    public final void clear() {
        if (!zza()) {
            zzc();
            Map zzb2 = zzb();
            if (zzb2 != null) {
                this.zzf = zzfz.zza(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
                zzb2.clear();
                this.zze = null;
                this.zzg = 0;
                return;
            }
            Arrays.fill(this.zzb, 0, this.zzg, (Object) null);
            Arrays.fill(this.zzc, 0, this.zzg, (Object) null);
            Object obj = this.zze;
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            Arrays.fill(this.zza, 0, this.zzg, 0);
            this.zzg = 0;
        }
    }

    static /* synthetic */ int zzd(zzem zzem) {
        int i = zzem.zzg;
        zzem.zzg = i - 1;
        return i;
    }
}

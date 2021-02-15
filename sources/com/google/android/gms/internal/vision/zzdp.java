package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzdp<K, V> extends AbstractMap<K, V> implements Serializable {
    /* access modifiers changed from: private */
    public static final Object zzmf = new Object();
    private transient int size;
    /* access modifiers changed from: private */
    @NullableDecl
    public transient Object zzmg;
    @NullableDecl
    transient int[] zzmh;
    @NullableDecl
    transient Object[] zzmi;
    @NullableDecl
    transient Object[] zzmj;
    /* access modifiers changed from: private */
    public transient int zzmk = zzfc.zzc(3, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    @NullableDecl
    private transient Set<K> zzml;
    @NullableDecl
    private transient Set<Map.Entry<K, V>> zzmm;
    @NullableDecl
    private transient Collection<V> zzmn;

    zzdp() {
        zzde.checkArgument(true, "Expected size must be >= 0");
    }

    static int zzg(int i, int i2) {
        return i - 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzce() {
        return this.zzmg == null;
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public final Map<K, V> zzcf() {
        Object obj = this.zzmg;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    private final void zzs(int i) {
        this.zzmk = zzea.zzb(this.zzmk, 32 - Integer.numberOfLeadingZeros(i), 31);
    }

    /* access modifiers changed from: private */
    public final int zzcg() {
        return (1 << (this.zzmk & 31)) - 1;
    }

    /* access modifiers changed from: package-private */
    public final void zzch() {
        this.zzmk += 32;
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
            boolean r3 = r18.zzce()
            r4 = 1
            if (r3 == 0) goto L_0x004e
            boolean r3 = r18.zzce()
            java.lang.String r5 = "Arrays already allocated"
            com.google.android.gms.internal.vision.zzde.checkState(r3, r5)
            int r3 = r0.zzmk
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
            java.lang.Object r6 = com.google.android.gms.internal.vision.zzea.zzv(r5)
            r0.zzmg = r6
            int r5 = r5 - r4
            r0.zzs(r5)
            int[] r5 = new int[r3]
            r0.zzmh = r5
            java.lang.Object[] r5 = new java.lang.Object[r3]
            r0.zzmi = r5
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r0.zzmj = r3
        L_0x004e:
            java.util.Map r3 = r18.zzcf()
            if (r3 == 0) goto L_0x0059
            java.lang.Object r1 = r3.put(r1, r2)
            return r1
        L_0x0059:
            int[] r3 = r0.zzmh
            java.lang.Object[] r5 = r0.zzmi
            java.lang.Object[] r6 = r0.zzmj
            int r7 = r0.size
            int r8 = r7 + 1
            int r9 = com.google.android.gms.internal.vision.zzec.zzf(r19)
            int r10 = r18.zzcg()
            r11 = r9 & r10
            java.lang.Object r12 = r0.zzmg
            int r12 = com.google.android.gms.internal.vision.zzea.zza(r12, r11)
            if (r12 != 0) goto L_0x0086
            if (r8 <= r10) goto L_0x0080
            int r3 = com.google.android.gms.internal.vision.zzea.zzw(r10)
            int r10 = r0.zza(r10, r3, r9, r7)
            goto L_0x00ef
        L_0x0080:
            java.lang.Object r3 = r0.zzmg
            com.google.android.gms.internal.vision.zzea.zza(r3, r11, r8)
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
            boolean r14 = com.google.android.gms.internal.vision.zzcz.equal(r1, r14)
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
            int r3 = r18.zzcg()
            int r3 = r3 + r4
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            r5 = 1065353216(0x3f800000, float:1.0)
            r4.<init>(r3, r5)
            int r3 = r18.zzci()
        L_0x00bb:
            if (r3 < 0) goto L_0x00cd
            java.lang.Object[] r5 = r0.zzmi
            r5 = r5[r3]
            java.lang.Object[] r6 = r0.zzmj
            r6 = r6[r3]
            r4.put(r5, r6)
            int r3 = r0.zzt(r3)
            goto L_0x00bb
        L_0x00cd:
            r0.zzmg = r4
            r3 = 0
            r0.zzmh = r3
            r0.zzmi = r3
            r0.zzmj = r3
            r18.zzch()
            java.lang.Object r1 = r4.put(r1, r2)
            return r1
        L_0x00de:
            if (r8 <= r10) goto L_0x00e9
            int r3 = com.google.android.gms.internal.vision.zzea.zzw(r10)
            int r10 = r0.zza(r10, r3, r9, r7)
            goto L_0x00ef
        L_0x00e9:
            int r5 = com.google.android.gms.internal.vision.zzea.zzb(r13, r8, r10)
            r3[r12] = r5
        L_0x00ef:
            int[] r3 = r0.zzmh
            int r3 = r3.length
            if (r8 <= r3) goto L_0x011d
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            int r6 = r3 >>> 1
            int r6 = java.lang.Math.max(r4, r6)
            int r6 = r6 + r3
            r4 = r4 | r6
            int r4 = java.lang.Math.min(r5, r4)
            if (r4 == r3) goto L_0x011d
            int[] r3 = r0.zzmh
            int[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.zzmh = r3
            java.lang.Object[] r3 = r0.zzmi
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.zzmi = r3
            java.lang.Object[] r3 = r0.zzmj
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)
            r0.zzmj = r3
        L_0x011d:
            int[] r3 = r0.zzmh
            r12 = 0
            int r4 = com.google.android.gms.internal.vision.zzea.zzb(r9, r12, r10)
            r3[r7] = r4
            java.lang.Object[] r3 = r0.zzmi
            r3[r7] = r1
            java.lang.Object[] r1 = r0.zzmj
            r1[r7] = r2
            r0.size = r8
            r18.zzch()
            r13 = 0
            return r13
        L_0x0135:
            r16 = r5
            r12 = r14
            r5 = r17
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzdp.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    private final int zza(int i, int i2, int i3, int i4) {
        Object zzv = zzea.zzv(i2);
        int i5 = i2 - 1;
        if (i4 != 0) {
            zzea.zza(zzv, i3 & i5, i4 + 1);
        }
        Object obj = this.zzmg;
        int[] iArr = this.zzmh;
        for (int i6 = 0; i6 <= i; i6++) {
            int zza = zzea.zza(obj, i6);
            while (zza != 0) {
                int i7 = zza - 1;
                int i8 = iArr[i7];
                int i9 = ((~i) & i8) | i6;
                int i10 = i9 & i5;
                int zza2 = zzea.zza(zzv, i10);
                zzea.zza(zzv, i10, zza);
                iArr[i7] = zzea.zzb(i9, zza2, i5);
                zza = i8 & i;
            }
        }
        this.zzmg = zzv;
        zzs(i5);
        return i5;
    }

    /* access modifiers changed from: private */
    public final int indexOf(@NullableDecl Object obj) {
        if (zzce()) {
            return -1;
        }
        int zzf = zzec.zzf(obj);
        int zzcg = zzcg();
        int zza = zzea.zza(this.zzmg, zzf & zzcg);
        if (zza == 0) {
            return -1;
        }
        int i = ~zzcg;
        int i2 = zzf & i;
        do {
            int i3 = zza - 1;
            int i4 = this.zzmh[i3];
            if ((i4 & i) == i2 && zzcz.equal(obj, this.zzmi[i3])) {
                return i3;
            }
            zza = i4 & zzcg;
        } while (zza != 0);
        return -1;
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        Map zzcf = zzcf();
        if (zzcf != null) {
            return zzcf.containsKey(obj);
        }
        return indexOf(obj) != -1;
    }

    public final V get(@NullableDecl Object obj) {
        Map zzcf = zzcf();
        if (zzcf != null) {
            return zzcf.get(obj);
        }
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.zzmj[indexOf];
    }

    @NullableDecl
    public final V remove(@NullableDecl Object obj) {
        Map zzcf = zzcf();
        if (zzcf != null) {
            return zzcf.remove(obj);
        }
        V zze = zze(obj);
        if (zze == zzmf) {
            return null;
        }
        return zze;
    }

    /* access modifiers changed from: private */
    @NullableDecl
    public final Object zze(@NullableDecl Object obj) {
        if (zzce()) {
            return zzmf;
        }
        int zzcg = zzcg();
        int zza = zzea.zza(obj, (Object) null, zzcg, this.zzmg, this.zzmh, this.zzmi, (Object[]) null);
        if (zza == -1) {
            return zzmf;
        }
        Object obj2 = this.zzmj[zza];
        zzf(zza, zzcg);
        this.size--;
        zzch();
        return obj2;
    }

    /* access modifiers changed from: package-private */
    public final void zzf(int i, int i2) {
        int size2 = size() - 1;
        if (i < size2) {
            Object[] objArr = this.zzmi;
            Object obj = objArr[size2];
            objArr[i] = obj;
            Object[] objArr2 = this.zzmj;
            objArr2[i] = objArr2[size2];
            objArr[size2] = null;
            objArr2[size2] = null;
            int[] iArr = this.zzmh;
            iArr[i] = iArr[size2];
            iArr[size2] = 0;
            int zzf = zzec.zzf(obj) & i2;
            int zza = zzea.zza(this.zzmg, zzf);
            int i3 = size2 + 1;
            if (zza == i3) {
                zzea.zza(this.zzmg, zzf, i + 1);
                return;
            }
            while (true) {
                int i4 = zza - 1;
                int[] iArr2 = this.zzmh;
                int i5 = iArr2[i4];
                int i6 = i5 & i2;
                if (i6 == i3) {
                    iArr2[i4] = zzea.zzb(i5, i + 1, i2);
                    return;
                }
                zza = i6;
            }
        } else {
            this.zzmi[i] = null;
            this.zzmj[i] = null;
            this.zzmh[i] = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzci() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public final int zzt(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
    }

    public final Set<K> keySet() {
        Set<K> set = this.zzml;
        if (set != null) {
            return set;
        }
        zzdv zzdv = new zzdv(this);
        this.zzml = zzdv;
        return zzdv;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<K> zzcj() {
        Map zzcf = zzcf();
        if (zzcf != null) {
            return zzcf.keySet().iterator();
        }
        return new zzds(this);
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.zzmm;
        if (set != null) {
            return set;
        }
        zzdt zzdt = new zzdt(this);
        this.zzmm = zzdt;
        return zzdt;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<K, V>> zzck() {
        Map zzcf = zzcf();
        if (zzcf != null) {
            return zzcf.entrySet().iterator();
        }
        return new zzdr(this);
    }

    public final int size() {
        Map zzcf = zzcf();
        return zzcf != null ? zzcf.size() : this.size;
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        Map zzcf = zzcf();
        if (zzcf != null) {
            return zzcf.containsValue(obj);
        }
        for (int i = 0; i < this.size; i++) {
            if (zzcz.equal(obj, this.zzmj[i])) {
                return true;
            }
        }
        return false;
    }

    public final Collection<V> values() {
        Collection<V> collection = this.zzmn;
        if (collection != null) {
            return collection;
        }
        zzdx zzdx = new zzdx(this);
        this.zzmn = zzdx;
        return zzdx;
    }

    /* access modifiers changed from: package-private */
    public final Iterator<V> zzcl() {
        Map zzcf = zzcf();
        if (zzcf != null) {
            return zzcf.values().iterator();
        }
        return new zzdu(this);
    }

    public final void clear() {
        if (!zzce()) {
            zzch();
            Map zzcf = zzcf();
            if (zzcf != null) {
                this.zzmk = zzfc.zzc(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
                zzcf.clear();
                this.zzmg = null;
                this.size = 0;
                return;
            }
            Arrays.fill(this.zzmi, 0, this.size, (Object) null);
            Arrays.fill(this.zzmj, 0, this.size, (Object) null);
            Object obj = this.zzmg;
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            Arrays.fill(this.zzmh, 0, this.size, 0);
            this.size = 0;
        }
    }

    static /* synthetic */ int zzd(zzdp zzdp) {
        int i = zzdp.size;
        zzdp.size = i - 1;
        return i;
    }
}

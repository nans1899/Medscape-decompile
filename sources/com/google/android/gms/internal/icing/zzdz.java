package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzdz extends zzcp<Integer> implements zzee<Integer>, zzfq, RandomAccess {
    private static final zzdz zzkk;
    private int size;
    private int[] zzkl;

    zzdz() {
        this(new int[10], 0);
    }

    private zzdz(int[] iArr, int i) {
        this.zzkl = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzaj();
        if (i2 >= i) {
            int[] iArr = this.zzkl;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdz)) {
            return super.equals(obj);
        }
        zzdz zzdz = (zzdz) obj;
        if (this.size != zzdz.size) {
            return false;
        }
        int[] iArr = zzdz.zzkl;
        for (int i = 0; i < this.size; i++) {
            if (this.zzkl[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzkl[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzh(i);
        return this.zzkl[i];
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzaj();
        zzeb.checkNotNull(collection);
        if (!(collection instanceof zzdz)) {
            return super.addAll(collection);
        }
        zzdz zzdz = (zzdz) collection;
        int i = zzdz.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzkl;
            if (i3 > iArr.length) {
                this.zzkl = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzdz.zzkl, 0, this.zzkl, this.size, zzdz.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzaj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzkl[i]))) {
                int[] iArr = this.zzkl;
                System.arraycopy(iArr, i + 1, iArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzh(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
    }

    private final String zzi(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzaj();
        zzh(i);
        int[] iArr = this.zzkl;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzaj();
        zzh(i);
        int[] iArr = this.zzkl;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        int intValue = ((Integer) obj).intValue();
        zzaj();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        int[] iArr = this.zzkl;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zzkl, i, iArr2, i + 1, this.size - i);
            this.zzkl = iArr2;
        }
        this.zzkl[i] = intValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzaj();
        int i = this.size;
        int[] iArr = this.zzkl;
        if (i == iArr.length) {
            int[] iArr2 = new int[(((i * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.zzkl = iArr2;
        }
        int[] iArr3 = this.zzkl;
        int i2 = this.size;
        this.size = i2 + 1;
        iArr3[i2] = intValue;
        return true;
    }

    public final /* synthetic */ zzee zzj(int i) {
        if (i >= this.size) {
            return new zzdz(Arrays.copyOf(this.zzkl, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzdz zzdz = new zzdz(new int[0], 0);
        zzkk = zzdz;
        zzdz.zzai();
    }
}

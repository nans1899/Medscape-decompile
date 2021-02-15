package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzcr extends zzcp<Boolean> implements zzee<Boolean>, zzfq, RandomAccess {
    private static final zzcr zzge;
    private int size;
    private boolean[] zzgf;

    public static zzcr zzak() {
        return zzge;
    }

    zzcr() {
        this(new boolean[10], 0);
    }

    private zzcr(boolean[] zArr, int i) {
        this.zzgf = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzaj();
        if (i2 >= i) {
            boolean[] zArr = this.zzgf;
            System.arraycopy(zArr, i2, zArr, i, this.size - i2);
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
        if (!(obj instanceof zzcr)) {
            return super.equals(obj);
        }
        zzcr zzcr = (zzcr) obj;
        if (this.size != zzcr.size) {
            return false;
        }
        boolean[] zArr = zzcr.zzgf;
        for (int i = 0; i < this.size; i++) {
            if (this.zzgf[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzeb.zzg(this.zzgf[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzaj();
        zzeb.checkNotNull(collection);
        if (!(collection instanceof zzcr)) {
            return super.addAll(collection);
        }
        zzcr zzcr = (zzcr) collection;
        int i = zzcr.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzgf;
            if (i3 > zArr.length) {
                this.zzgf = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzcr.zzgf, 0, this.zzgf, this.size, zzcr.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzaj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzgf[i]))) {
                boolean[] zArr = this.zzgf;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzaj();
        zzh(i);
        boolean[] zArr = this.zzgf;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzaj();
        zzh(i);
        boolean[] zArr = this.zzgf;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzaj();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        boolean[] zArr = this.zzgf;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zzgf, i, zArr2, i + 1, this.size - i);
            this.zzgf = zArr2;
        }
        this.zzgf[i] = booleanValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzaj();
        int i = this.size;
        boolean[] zArr = this.zzgf;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[(((i * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.zzgf = zArr2;
        }
        boolean[] zArr3 = this.zzgf;
        int i2 = this.size;
        this.size = i2 + 1;
        zArr3[i2] = booleanValue;
        return true;
    }

    public final /* synthetic */ zzee zzj(int i) {
        if (i >= this.size) {
            return new zzcr(Arrays.copyOf(this.zzgf, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Boolean.valueOf(this.zzgf[i]);
    }

    static {
        zzcr zzcr = new zzcr(new boolean[0], 0);
        zzge = zzcr;
        zzcr.zzai();
    }
}

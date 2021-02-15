package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzdl extends zzcp<Double> implements zzee<Double>, zzfq, RandomAccess {
    private static final zzdl zzgz;
    private int size;
    private double[] zzha;

    public static zzdl zzax() {
        return zzgz;
    }

    zzdl() {
        this(new double[10], 0);
    }

    private zzdl(double[] dArr, int i) {
        this.zzha = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzaj();
        if (i2 >= i) {
            double[] dArr = this.zzha;
            System.arraycopy(dArr, i2, dArr, i, this.size - i2);
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
        if (!(obj instanceof zzdl)) {
            return super.equals(obj);
        }
        zzdl zzdl = (zzdl) obj;
        if (this.size != zzdl.size) {
            return false;
        }
        double[] dArr = zzdl.zzha;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzha[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzeb.zzk(Double.doubleToLongBits(this.zzha[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzaj();
        zzeb.checkNotNull(collection);
        if (!(collection instanceof zzdl)) {
            return super.addAll(collection);
        }
        zzdl zzdl = (zzdl) collection;
        int i = zzdl.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzha;
            if (i3 > dArr.length) {
                this.zzha = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzdl.zzha, 0, this.zzha, this.size, zzdl.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzaj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzha[i]))) {
                double[] dArr = this.zzha;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
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
        double doubleValue = ((Double) obj).doubleValue();
        zzaj();
        zzh(i);
        double[] dArr = this.zzha;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzaj();
        zzh(i);
        double[] dArr = this.zzha;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        double doubleValue = ((Double) obj).doubleValue();
        zzaj();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        double[] dArr = this.zzha;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzha, i, dArr2, i + 1, this.size - i);
            this.zzha = dArr2;
        }
        this.zzha[i] = doubleValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzaj();
        int i = this.size;
        double[] dArr = this.zzha;
        if (i == dArr.length) {
            double[] dArr2 = new double[(((i * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.zzha = dArr2;
        }
        double[] dArr3 = this.zzha;
        int i2 = this.size;
        this.size = i2 + 1;
        dArr3[i2] = doubleValue;
        return true;
    }

    public final /* synthetic */ zzee zzj(int i) {
        if (i >= this.size) {
            return new zzdl(Arrays.copyOf(this.zzha, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Double.valueOf(this.zzha[i]);
    }

    static {
        zzdl zzdl = new zzdl(new double[0], 0);
        zzgz = zzdl;
        zzdl.zzai();
    }
}

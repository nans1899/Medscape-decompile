package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzhm extends zzgi<Double> implements zzik<Double>, zzjz, RandomAccess {
    private static final zzhm zzup;
    private int size;
    private double[] zzuq;

    zzhm() {
        this(new double[10], 0);
    }

    private zzhm(double[] dArr, int i) {
        this.zzuq = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzek();
        if (i2 >= i) {
            double[] dArr = this.zzuq;
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
        if (!(obj instanceof zzhm)) {
            return super.equals(obj);
        }
        zzhm zzhm = (zzhm) obj;
        if (this.size != zzhm.size) {
            return false;
        }
        double[] dArr = zzhm.zzuq;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzuq[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzie.zzab(Double.doubleToLongBits(this.zzuq[i2]));
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzuq[i] == doubleValue) {
                return i;
            }
        }
        return -1;
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(double d) {
        zzek();
        int i = this.size;
        double[] dArr = this.zzuq;
        if (i == dArr.length) {
            double[] dArr2 = new double[(((i * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.zzuq = dArr2;
        }
        double[] dArr3 = this.zzuq;
        int i2 = this.size;
        this.size = i2 + 1;
        dArr3[i2] = d;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzek();
        zzie.checkNotNull(collection);
        if (!(collection instanceof zzhm)) {
            return super.addAll(collection);
        }
        zzhm zzhm = (zzhm) collection;
        int i = zzhm.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzuq;
            if (i3 > dArr.length) {
                this.zzuq = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzhm.zzuq, 0, this.zzuq, this.size, zzhm.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzek();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzuq[i]))) {
                double[] dArr = this.zzuq;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzal(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzam(i));
        }
    }

    private final String zzam(int i) {
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
        zzek();
        zzal(i);
        double[] dArr = this.zzuq;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzek();
        zzal(i);
        double[] dArr = this.zzuq;
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
        zzek();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzam(i));
        }
        double[] dArr = this.zzuq;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzuq, i, dArr2, i + 1, this.size - i);
            this.zzuq = dArr2;
        }
        this.zzuq[i] = doubleValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzc(((Double) obj).doubleValue());
        return true;
    }

    public final /* synthetic */ zzik zzan(int i) {
        if (i >= this.size) {
            return new zzhm(Arrays.copyOf(this.zzuq, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzal(i);
        return Double.valueOf(this.zzuq[i]);
    }

    static {
        zzhm zzhm = new zzhm(new double[0], 0);
        zzup = zzhm;
        zzhm.zzej();
    }
}

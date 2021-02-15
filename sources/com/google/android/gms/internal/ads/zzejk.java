package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzejk extends zzeij<Double> implements zzekk<Double>, zzelv, RandomAccess {
    private static final zzejk zzifw;
    private int size;
    private double[] zzifx;

    zzejk() {
        this(new double[10], 0);
    }

    private zzejk(double[] dArr, int i) {
        this.zzifx = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbed();
        if (i2 >= i) {
            double[] dArr = this.zzifx;
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
        if (!(obj instanceof zzejk)) {
            return super.equals(obj);
        }
        zzejk zzejk = (zzejk) obj;
        if (this.size != zzejk.size) {
            return false;
        }
        double[] dArr = zzejk.zzifx;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzifx[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzekb.zzfr(Double.doubleToLongBits(this.zzifx[i2]));
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
            if (this.zzifx[i] == doubleValue) {
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

    public final void zze(double d) {
        zzbed();
        int i = this.size;
        double[] dArr = this.zzifx;
        if (i == dArr.length) {
            double[] dArr2 = new double[(((i * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.zzifx = dArr2;
        }
        double[] dArr3 = this.zzifx;
        int i2 = this.size;
        this.size = i2 + 1;
        dArr3[i2] = d;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzbed();
        zzekb.checkNotNull(collection);
        if (!(collection instanceof zzejk)) {
            return super.addAll(collection);
        }
        zzejk zzejk = (zzejk) collection;
        int i = zzejk.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzifx;
            if (i3 > dArr.length) {
                this.zzifx = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzejk.zzifx, 0, this.zzifx, this.size, zzejk.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzbed();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzifx[i]))) {
                double[] dArr = this.zzifx;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzfr(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzfs(i));
        }
    }

    private final String zzfs(int i) {
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
        zzbed();
        zzfr(i);
        double[] dArr = this.zzifx;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbed();
        zzfr(i);
        double[] dArr = this.zzifx;
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
        zzbed();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfs(i));
        }
        double[] dArr = this.zzifx;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzifx, i, dArr2, i + 1, this.size - i);
            this.zzifx = dArr2;
        }
        this.zzifx[i] = doubleValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zze(((Double) obj).doubleValue());
        return true;
    }

    public final /* synthetic */ zzekk zzft(int i) {
        if (i >= this.size) {
            return new zzejk(Arrays.copyOf(this.zzifx, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzfr(i);
        return Double.valueOf(this.zzifx[i]);
    }

    static {
        zzejk zzejk = new zzejk(new double[0], 0);
        zzifw = zzejk;
        zzejk.zzbec();
    }
}

package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzhz extends zzgi<Float> implements zzik<Float>, zzjz, RandomAccess {
    private static final zzhz zzxm;
    private int size;
    private float[] zzxn;

    zzhz() {
        this(new float[10], 0);
    }

    private zzhz(float[] fArr, int i) {
        this.zzxn = fArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzek();
        if (i2 >= i) {
            float[] fArr = this.zzxn;
            System.arraycopy(fArr, i2, fArr, i, this.size - i2);
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
        if (!(obj instanceof zzhz)) {
            return super.equals(obj);
        }
        zzhz zzhz = (zzhz) obj;
        if (this.size != zzhz.size) {
            return false;
        }
        float[] fArr = zzhz.zzxn;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzxn[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzxn[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzxn[i] == floatValue) {
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

    public final void zzu(float f) {
        zzek();
        int i = this.size;
        float[] fArr = this.zzxn;
        if (i == fArr.length) {
            float[] fArr2 = new float[(((i * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.zzxn = fArr2;
        }
        float[] fArr3 = this.zzxn;
        int i2 = this.size;
        this.size = i2 + 1;
        fArr3[i2] = f;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzek();
        zzie.checkNotNull(collection);
        if (!(collection instanceof zzhz)) {
            return super.addAll(collection);
        }
        zzhz zzhz = (zzhz) collection;
        int i = zzhz.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.zzxn;
            if (i3 > fArr.length) {
                this.zzxn = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(zzhz.zzxn, 0, this.zzxn, this.size, zzhz.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzek();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzxn[i]))) {
                float[] fArr = this.zzxn;
                System.arraycopy(fArr, i + 1, fArr, i, (this.size - i) - 1);
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
        float floatValue = ((Float) obj).floatValue();
        zzek();
        zzal(i);
        float[] fArr = this.zzxn;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    public final /* synthetic */ Object remove(int i) {
        zzek();
        zzal(i);
        float[] fArr = this.zzxn;
        float f = fArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        float floatValue = ((Float) obj).floatValue();
        zzek();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzam(i));
        }
        float[] fArr = this.zzxn;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zzxn, i, fArr2, i + 1, this.size - i);
            this.zzxn = fArr2;
        }
        this.zzxn[i] = floatValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzu(((Float) obj).floatValue());
        return true;
    }

    public final /* synthetic */ zzik zzan(int i) {
        if (i >= this.size) {
            return new zzhz(Arrays.copyOf(this.zzxn, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzal(i);
        return Float.valueOf(this.zzxn[i]);
    }

    static {
        zzhz zzhz = new zzhz(new float[0], 0);
        zzxm = zzhz;
        zzhz.zzej();
    }
}

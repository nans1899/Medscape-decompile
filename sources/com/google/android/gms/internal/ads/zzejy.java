package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzejy extends zzeij<Float> implements zzekk<Float>, zzelv, RandomAccess {
    private static final zzejy zzija;
    private int size;
    private float[] zzijb;

    zzejy() {
        this(new float[10], 0);
    }

    private zzejy(float[] fArr, int i) {
        this.zzijb = fArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbed();
        if (i2 >= i) {
            float[] fArr = this.zzijb;
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
        if (!(obj instanceof zzejy)) {
            return super.equals(obj);
        }
        zzejy zzejy = (zzejy) obj;
        if (this.size != zzejy.size) {
            return false;
        }
        float[] fArr = zzejy.zzijb;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzijb[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzijb[i2]);
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
            if (this.zzijb[i] == floatValue) {
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

    public final void zzh(float f) {
        zzbed();
        int i = this.size;
        float[] fArr = this.zzijb;
        if (i == fArr.length) {
            float[] fArr2 = new float[(((i * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.zzijb = fArr2;
        }
        float[] fArr3 = this.zzijb;
        int i2 = this.size;
        this.size = i2 + 1;
        fArr3[i2] = f;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzbed();
        zzekb.checkNotNull(collection);
        if (!(collection instanceof zzejy)) {
            return super.addAll(collection);
        }
        zzejy zzejy = (zzejy) collection;
        int i = zzejy.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.zzijb;
            if (i3 > fArr.length) {
                this.zzijb = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(zzejy.zzijb, 0, this.zzijb, this.size, zzejy.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzbed();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzijb[i]))) {
                float[] fArr = this.zzijb;
                System.arraycopy(fArr, i + 1, fArr, i, (this.size - i) - 1);
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
        float floatValue = ((Float) obj).floatValue();
        zzbed();
        zzfr(i);
        float[] fArr = this.zzijb;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbed();
        zzfr(i);
        float[] fArr = this.zzijb;
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
        zzbed();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfs(i));
        }
        float[] fArr = this.zzijb;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zzijb, i, fArr2, i + 1, this.size - i);
            this.zzijb = fArr2;
        }
        this.zzijb[i] = floatValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzh(((Float) obj).floatValue());
        return true;
    }

    public final /* synthetic */ zzekk zzft(int i) {
        if (i >= this.size) {
            return new zzejy(Arrays.copyOf(this.zzijb, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzfr(i);
        return Float.valueOf(this.zzijb[i]);
    }

    static {
        zzejy zzejy = new zzejy(new float[0], 0);
        zzija = zzejy;
        zzejy.zzbec();
    }
}

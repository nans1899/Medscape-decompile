package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzgq extends zzgi<Boolean> implements zzik<Boolean>, zzjz, RandomAccess {
    private static final zzgq zzto;
    private int size;
    private boolean[] zztp;

    zzgq() {
        this(new boolean[10], 0);
    }

    private zzgq(boolean[] zArr, int i) {
        this.zztp = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzek();
        if (i2 >= i) {
            boolean[] zArr = this.zztp;
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
        if (!(obj instanceof zzgq)) {
            return super.equals(obj);
        }
        zzgq zzgq = (zzgq) obj;
        if (this.size != zzgq.size) {
            return false;
        }
        boolean[] zArr = zzgq.zztp;
        for (int i = 0; i < this.size; i++) {
            if (this.zztp[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzie.zzm(this.zztp[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zztp[i] == booleanValue) {
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

    public final void addBoolean(boolean z) {
        zzek();
        int i = this.size;
        boolean[] zArr = this.zztp;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[(((i * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.zztp = zArr2;
        }
        boolean[] zArr3 = this.zztp;
        int i2 = this.size;
        this.size = i2 + 1;
        zArr3[i2] = z;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzek();
        zzie.checkNotNull(collection);
        if (!(collection instanceof zzgq)) {
            return super.addAll(collection);
        }
        zzgq zzgq = (zzgq) collection;
        int i = zzgq.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zztp;
            if (i3 > zArr.length) {
                this.zztp = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzgq.zztp, 0, this.zztp, this.size, zzgq.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzek();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zztp[i]))) {
                boolean[] zArr = this.zztp;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzek();
        zzal(i);
        boolean[] zArr = this.zztp;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzek();
        zzal(i);
        boolean[] zArr = this.zztp;
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
        zzek();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzam(i));
        }
        boolean[] zArr = this.zztp;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zztp, i, zArr2, i + 1, this.size - i);
            this.zztp = zArr2;
        }
        this.zztp[i] = booleanValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        addBoolean(((Boolean) obj).booleanValue());
        return true;
    }

    public final /* synthetic */ zzik zzan(int i) {
        if (i >= this.size) {
            return new zzgq(Arrays.copyOf(this.zztp, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzal(i);
        return Boolean.valueOf(this.zztp[i]);
    }

    static {
        zzgq zzgq = new zzgq(new boolean[0], 0);
        zzto = zzgq;
        zzgq.zzej();
    }
}

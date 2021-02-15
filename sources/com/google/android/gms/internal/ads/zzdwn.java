package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
class zzdwn<E> extends zzdwm<E> {
    int size = 0;
    Object[] zzhqk;
    boolean zzhql;

    zzdwn(int i) {
        zzdwj.zzh(i, "initialCapacity");
        this.zzhqk = new Object[i];
    }

    private final void zzem(int i) {
        Object[] objArr = this.zzhqk;
        if (objArr.length < i) {
            int length = objArr.length;
            if (i >= 0) {
                int i2 = length + (length >> 1) + 1;
                if (i2 < i) {
                    i2 = Integer.highestOneBit(i - 1) << 1;
                }
                if (i2 < 0) {
                    i2 = Integer.MAX_VALUE;
                }
                this.zzhqk = Arrays.copyOf(objArr, i2);
                this.zzhql = false;
                return;
            }
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        } else if (this.zzhql) {
            this.zzhqk = (Object[]) objArr.clone();
            this.zzhql = false;
        }
    }

    /* renamed from: zzab */
    public zzdwn<E> zzaa(E e) {
        zzdwd.checkNotNull(e);
        zzem(this.size + 1);
        Object[] objArr = this.zzhqk;
        int i = this.size;
        this.size = i + 1;
        objArr[i] = e;
        return this;
    }

    public zzdwm<E> zzg(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            zzem(this.size + collection.size());
            if (collection instanceof zzdwk) {
                this.size = ((zzdwk) collection).zza(this.zzhqk, this.size);
                return this;
            }
        }
        super.zzg(iterable);
        return this;
    }
}

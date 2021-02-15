package com.google.android.gms.internal.icing;

import java.io.Serializable;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class zzbx<T> implements Serializable {
    public static <T> zzbx<T> zzw() {
        return zzbv.zzdt;
    }

    public abstract T get();

    public abstract boolean isPresent();

    public static <T> zzbx<T> zzb(T t) {
        return new zzbz(zzca.checkNotNull(t));
    }

    zzbx() {
    }
}

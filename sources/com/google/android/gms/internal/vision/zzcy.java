package com.google.android.gms.internal.vision;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzcy<T> implements Serializable {
    public static <T> zzcy<T> zzcb() {
        return zzcv.zzly;
    }

    public abstract T get();

    public abstract boolean isPresent();

    public static <T> zzcy<T> zzb(T t) {
        return new zzdd(zzde.checkNotNull(t));
    }

    zzcy() {
    }
}

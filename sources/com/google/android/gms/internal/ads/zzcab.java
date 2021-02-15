package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcab<T> {
    public Executor executor;
    public T zzfwk;

    public static <T> zzcab<T> zzb(T t, Executor executor2) {
        return new zzcab<>(t, executor2);
    }

    public zzcab(T t, Executor executor2) {
        this.zzfwk = t;
        this.executor = executor2;
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeox<T> implements zzeos<T>, zzeoy<T> {
    private static final zzeox<Object> zziur = new zzeox<>((Object) null);
    private final T zzedi;

    public static <T> zzeoy<T> zzba(T t) {
        return new zzeox(zzepe.zza(t, "instance cannot be null"));
    }

    public static <T> zzeoy<T> zzbb(T t) {
        if (t == null) {
            return zziur;
        }
        return new zzeox(t);
    }

    private zzeox(T t) {
        this.zzedi = t;
    }

    public final T get() {
        return this.zzedi;
    }
}

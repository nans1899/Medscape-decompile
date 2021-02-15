package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaj<T> {
    public final T result;
    public final zzn zzbs;
    public final zzao zzbt;
    public boolean zzbu;

    public static <T> zzaj<T> zza(T t, zzn zzn) {
        return new zzaj<>(t, zzn);
    }

    public static <T> zzaj<T> zzd(zzao zzao) {
        return new zzaj<>(zzao);
    }

    public final boolean isSuccess() {
        return this.zzbt == null;
    }

    private zzaj(T t, zzn zzn) {
        this.zzbu = false;
        this.result = t;
        this.zzbs = zzn;
        this.zzbt = null;
    }

    private zzaj(zzao zzao) {
        this.zzbu = false;
        this.result = null;
        this.zzbs = null;
        this.zzbt = zzao;
    }
}

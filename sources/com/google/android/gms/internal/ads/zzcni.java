package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcni {
    public static Set<zzcab<zzbuy>> zza(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<AppEventListener>> zzb(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<zzbvs>> zzc(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<zzbui>> zzd(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<zzbuh>> zze(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<zzbuz>> zzf(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<zzux>> zzg(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<zzdso>> zzh(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    public static Set<zzcab<zzbws>> zzi(zzcns zzcns, Executor executor) {
        return zzc(zzcns, executor);
    }

    private static <T> Set<zzcab<T>> zzc(T t, Executor executor) {
        if (zzacv.zzdbq.get().booleanValue()) {
            return Collections.singleton(new zzcab(t, executor));
        }
        return Collections.emptySet();
    }
}

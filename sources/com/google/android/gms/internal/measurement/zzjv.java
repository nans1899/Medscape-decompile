package com.google.android.gms.internal.measurement;

import com.google.firebase.messaging.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzjv {
    private static final zzjv zza = new zzjv();
    private final zzkc zzb = new zziy();
    private final ConcurrentMap<Class<?>, zzjz<?>> zzc = new ConcurrentHashMap();

    public static zzjv zza() {
        return zza;
    }

    public final <T> zzjz<T> zza(Class<T> cls) {
        zzic.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzjz<T> zzjz = (zzjz) this.zzc.get(cls);
        if (zzjz != null) {
            return zzjz;
        }
        zzjz<T> zza2 = this.zzb.zza(cls);
        zzic.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzic.zza(zza2, "schema");
        zzjz<T> putIfAbsent = this.zzc.putIfAbsent(cls, zza2);
        return putIfAbsent != null ? putIfAbsent : zza2;
    }

    public final <T> zzjz<T> zza(T t) {
        return zza(t.getClass());
    }

    private zzjv() {
    }
}

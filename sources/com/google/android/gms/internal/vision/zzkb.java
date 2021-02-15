package com.google.android.gms.internal.vision;

import com.google.firebase.messaging.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzkb {
    private static final zzkb zzabh = new zzkb();
    private final zzke zzabi = new zzja();
    private final ConcurrentMap<Class<?>, zzkf<?>> zzabj = new ConcurrentHashMap();

    public static zzkb zzik() {
        return zzabh;
    }

    public final <T> zzkf<T> zzf(Class<T> cls) {
        zzie.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzkf<T> zzkf = (zzkf) this.zzabj.get(cls);
        if (zzkf != null) {
            return zzkf;
        }
        zzkf<T> zze = this.zzabi.zze(cls);
        zzie.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzie.zza(zze, "schema");
        zzkf<T> putIfAbsent = this.zzabj.putIfAbsent(cls, zze);
        return putIfAbsent != null ? putIfAbsent : zze;
    }

    public final <T> zzkf<T> zzx(T t) {
        return zzf(t.getClass());
    }

    private zzkb() {
    }
}

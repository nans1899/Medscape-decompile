package com.google.android.gms.internal.icing;

import com.google.firebase.messaging.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzft {
    private static final zzft zznk = new zzft();
    private final zzfx zznl = new zzeu();
    private final ConcurrentMap<Class<?>, zzfu<?>> zznm = new ConcurrentHashMap();

    public static zzft zzcv() {
        return zznk;
    }

    public final <T> zzfu<T> zze(Class<T> cls) {
        zzeb.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzfu<T> zzfu = (zzfu) this.zznm.get(cls);
        if (zzfu != null) {
            return zzfu;
        }
        zzfu<T> zzd = this.zznl.zzd(cls);
        zzeb.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzeb.zza(zzd, "schema");
        zzfu<T> putIfAbsent = this.zznm.putIfAbsent(cls, zzd);
        return putIfAbsent != null ? putIfAbsent : zzd;
    }

    public final <T> zzfu<T> zzo(T t) {
        return zze(t.getClass());
    }

    private zzft() {
    }
}

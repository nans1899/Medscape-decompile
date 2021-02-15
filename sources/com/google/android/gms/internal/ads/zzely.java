package com.google.android.gms.internal.ads;

import com.google.firebase.messaging.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzely {
    private static final zzely zzimi = new zzely();
    private final zzemi zzimj = new zzela();
    private final ConcurrentMap<Class<?>, zzemf<?>> zzimk = new ConcurrentHashMap();

    public static zzely zzbic() {
        return zzimi;
    }

    public final <T> zzemf<T> zzh(Class<T> cls) {
        zzekb.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzemf<T> zzemf = (zzemf) this.zzimk.get(cls);
        if (zzemf != null) {
            return zzemf;
        }
        zzemf<T> zzg = this.zzimj.zzg(cls);
        zzekb.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzekb.zza(zzg, "schema");
        zzemf<T> putIfAbsent = this.zzimk.putIfAbsent(cls, zzg);
        return putIfAbsent != null ? putIfAbsent : zzg;
    }

    public final <T> zzemf<T> zzaw(T t) {
        return zzh(t.getClass());
    }

    private zzely() {
    }
}

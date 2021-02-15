package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzepf<T> {
    private final List<zzeph<T>> zziuu;
    private final List<zzeph<Collection<T>>> zziuv;

    private zzepf(int i, int i2) {
        this.zziuu = zzeot.zzhy(i);
        this.zziuv = zzeot.zzhy(i2);
    }

    public final zzepf<T> zzau(zzeph<? extends T> zzeph) {
        this.zziuu.add(zzeph);
        return this;
    }

    public final zzepf<T> zzav(zzeph<? extends Collection<? extends T>> zzeph) {
        this.zziuv.add(zzeph);
        return this;
    }

    public final zzepd<T> zzbkk() {
        return new zzepd<>(this.zziuu, this.zziuv);
    }
}

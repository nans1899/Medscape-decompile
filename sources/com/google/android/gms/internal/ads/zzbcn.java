package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicInteger;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbcn<T> {
    private final zzbcg<T> zzefc = new zzbcg<>();
    /* access modifiers changed from: private */
    public final AtomicInteger zzefd = new AtomicInteger(0);

    public zzbcn() {
        zzdyq.zza(this.zzefc, new zzbcm(this), zzbbz.zzeeu);
    }

    @Deprecated
    public final void zza(zzbck<T> zzbck, zzbci zzbci) {
        zzdyq.zza(this.zzefc, new zzbcp(this, zzbck, zzbci), zzbbz.zzeeu);
    }

    @Deprecated
    public final void zzm(T t) {
        this.zzefc.set(t);
    }

    @Deprecated
    public final void reject() {
        this.zzefc.setException(new Exception());
    }

    @Deprecated
    public final int getStatus() {
        return this.zzefd.get();
    }
}

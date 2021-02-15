package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbts implements zzeoy<Context> {
    private final zzeph<Context> zzfth;
    private final zzbtp zzfuk;

    private zzbts(zzbtp zzbtp, zzeph<Context> zzeph) {
        this.zzfuk = zzbtp;
        this.zzfth = zzeph;
    }

    public static zzbts zza(zzbtp zzbtp, zzeph<Context> zzeph) {
        return new zzbts(zzbtp, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (Context) zzepe.zza(this.zzfuk.zzcd(this.zzfth.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbtp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbtz implements zzeoy<zzbtp.zza> {
    private final zzbtp zzfuk;

    private zzbtz(zzbtp zzbtp) {
        this.zzfuk = zzbtp;
    }

    public static zzbtz zzo(zzbtp zzbtp) {
        return new zzbtz(zzbtp);
    }

    public final /* synthetic */ Object get() {
        return (zzbtp.zza) zzepe.zza(this.zzfuk.zzajq(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

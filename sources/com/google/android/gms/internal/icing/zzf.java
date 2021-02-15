package com.google.android.gms.internal.icing;

import com.google.android.gms.common.api.Api;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzf {
    private static final Api.ClientKey<zzah> zze = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzah, Api.ApiOptions.NoOptions> zzf;
    public static final Api<Api.ApiOptions.NoOptions> zzg;
    @Deprecated
    private static final zzab zzh = new zzaj();

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.gms.internal.icing.zzaj, com.google.android.gms.internal.icing.zzab] */
    static {
        zze zze2 = new zze();
        zzf = zze2;
        zzg = new Api<>("AppDataSearch.LIGHTWEIGHT_API", zze2, zze);
    }
}

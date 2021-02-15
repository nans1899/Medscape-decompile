package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqr<AdT> implements zzeoy<zzbqs<AdT>> {
    private final zzeph<Map<String, zzcta<AdT>>> zzfrv;

    private zzbqr(zzeph<Map<String, zzcta<AdT>>> zzeph) {
        this.zzfrv = zzeph;
    }

    public static <AdT> zzbqr<AdT> zzd(zzeph<Map<String, zzcta<AdT>>> zzeph) {
        return new zzbqr<>(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbqs(this.zzfrv.get());
    }
}

package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcir implements zzdyr<zzbgj> {
    private final /* synthetic */ String zzgen;
    private final /* synthetic */ Map zzgeo;

    zzcir(zzcil zzcil, String str, Map map) {
        this.zzgen = str;
        this.zzgeo = map;
    }

    public final void zzb(Throwable th) {
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        ((zzbgj) obj).zza(this.zzgen, (Map<String, ?>) this.zzgeo);
    }
}

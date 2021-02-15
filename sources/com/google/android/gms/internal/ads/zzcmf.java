package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmf implements zzeoy<zzcme> {
    private final zzeph<zzts> zzggl;
    private final zzeph<Map<zzdsf, zzcmg>> zzggt;

    private zzcmf(zzeph<zzts> zzeph, zzeph<Map<zzdsf, zzcmg>> zzeph2) {
        this.zzggl = zzeph;
        this.zzggt = zzeph2;
    }

    public static zzcmf zzah(zzeph<zzts> zzeph, zzeph<Map<zzdsf, zzcmg>> zzeph2) {
        return new zzcmf(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzcme(this.zzggl.get(), this.zzggt.get());
    }
}

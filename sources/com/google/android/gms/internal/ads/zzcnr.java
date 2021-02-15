package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcnr implements zzeoy<zzcns> {
    private final zzeph<zzbix> zzesp;
    private final zzeph<zzcng> zzggl;

    private zzcnr(zzeph<zzcng> zzeph, zzeph<zzbix> zzeph2) {
        this.zzggl = zzeph;
        this.zzesp = zzeph2;
    }

    public static zzcnr zzan(zzeph<zzcng> zzeph, zzeph<zzbix> zzeph2) {
        return new zzcnr(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzcns(this.zzggl.get(), this.zzesp.get());
    }
}

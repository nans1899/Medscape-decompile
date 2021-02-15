package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzckn implements zzeoy<zzcab<zzbuy>> {
    private final zzeph<zzckm> zzfpj;
    private final zzcko zzgft;

    private zzckn(zzcko zzcko, zzeph<zzckm> zzeph) {
        this.zzgft = zzcko;
        this.zzfpj = zzeph;
    }

    public static zzckn zza(zzcko zzcko, zzeph<zzckm> zzeph) {
        return new zzckn(zzcko, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), zzbbz.zzeet), "Cannot return null from a non-@Nullable @Provides method");
    }
}

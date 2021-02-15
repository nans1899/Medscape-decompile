package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbn implements zzeoy<zzcab<zzbvs>> {
    private final zzeph<zzcco> zzfpj;
    private final zzcbf zzfwx;

    private zzcbn(zzcbf zzcbf, zzeph<zzcco> zzeph) {
        this.zzfwx = zzcbf;
        this.zzfpj = zzeph;
    }

    public static zzcbn zzc(zzcbf zzcbf, zzeph<zzcco> zzeph) {
        return new zzcbn(zzcbf, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), zzbbz.zzeet), "Cannot return null from a non-@Nullable @Provides method");
    }
}

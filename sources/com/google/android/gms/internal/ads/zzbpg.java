package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpg implements zzeoy<zzcab<zzbvs>> {
    private final zzeph<zzbqd> zzfpj;
    private final zzbot zzfqp;

    public zzbpg(zzbot zzbot, zzeph<zzbqd> zzeph) {
        this.zzfqp = zzbot;
        this.zzfpj = zzeph;
    }

    public static zzcab<zzbvs> zza(zzbot zzbot, zzbqd zzbqd) {
        return (zzcab) zzepe.zza(new zzcab(zzbqd, zzbbz.zzeet), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfqp, this.zzfpj.get());
    }
}

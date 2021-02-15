package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbtw implements zzeoy<zzdok> {
    private final zzbtp zzfuk;

    private zzbtw(zzbtp zzbtp) {
        this.zzfuk = zzbtp;
    }

    public static zzbtw zzl(zzbtp zzbtp) {
        return new zzbtw(zzbtp);
    }

    public static zzdok zzm(zzbtp zzbtp) {
        return (zzdok) zzepe.zza(zzbtp.zzajr(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzm(this.zzfuk);
    }
}

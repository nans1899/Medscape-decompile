package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbtu implements zzeoy<String> {
    private final zzeph<zzbsa> zzevm;
    private final zzbtp zzfuk;

    private zzbtu(zzbtp zzbtp, zzeph<zzbsa> zzeph) {
        this.zzfuk = zzbtp;
        this.zzevm = zzeph;
    }

    public static zzbtu zzb(zzbtp zzbtp, zzeph<zzbsa> zzeph) {
        return new zzbtu(zzbtp, zzeph);
    }

    public static String zza(zzbtp zzbtp, zzbsa zzbsa) {
        return (String) zzepe.zza(zzbsa.zzwd(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfuk, this.zzevm.get());
    }
}

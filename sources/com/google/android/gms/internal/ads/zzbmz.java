package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmz implements zzeoy<zzamk> {
    private final zzeph<zzamb> zzfot;

    private zzbmz(zzeph<zzamb> zzeph) {
        this.zzfot = zzeph;
    }

    public static zzbmz zzb(zzeph<zzamb> zzeph) {
        return new zzbmz(zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzamk) zzepe.zza(this.zzfot.get().zztp(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpc implements zzeoy<zzboq> {
    private final zzeph<zzbos> zzfjd;
    private final zzbot zzfqp;

    public zzbpc(zzbot zzbot, zzeph<zzbos> zzeph) {
        this.zzfqp = zzbot;
        this.zzfjd = zzeph;
    }

    public static zzboq zza(zzbot zzbot, Object obj) {
        return (zzboq) zzepe.zza((zzbos) obj, "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfqp, this.zzfjd.get());
    }
}

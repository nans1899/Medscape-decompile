package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccx implements zzeoy<zzcep> {
    private final zzccw zzfxk;

    private zzccx(zzccw zzccw) {
        this.zzfxk = zzccw;
    }

    public static zzccx zzc(zzccw zzccw) {
        return new zzccx(zzccw);
    }

    public static zzcep zzd(zzccw zzccw) {
        return (zzcep) zzepe.zza(zzccw.zzalh(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzd(this.zzfxk);
    }
}

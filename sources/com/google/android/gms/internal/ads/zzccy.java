package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccy implements zzeoy<zzccw> {
    private final zzccw zzfxk;

    private zzccy(zzccw zzccw) {
        this.zzfxk = zzccw;
    }

    public static zzccy zze(zzccw zzccw) {
        return new zzccy(zzccw);
    }

    public final /* synthetic */ Object get() {
        zzccw zzccw = this.zzfxk;
        if (zzccw != null) {
            return (zzccw) zzepe.zza(zzccw, "Cannot return null from a non-@Nullable @Provides method");
        }
        throw null;
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbjl implements zzeoy<zzbbx> {
    private final zzbja zzesn;

    public zzbjl(zzbja zzbja) {
        this.zzesn = zzbja;
    }

    public static zzbbx zzb(zzbja zzbja) {
        return (zzbbx) zzepe.zza(zzbja.zzaed(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzb(this.zzesn);
    }
}

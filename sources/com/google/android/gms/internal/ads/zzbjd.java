package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbjd implements zzeoy<Context> {
    private final zzbja zzesn;

    public zzbjd(zzbja zzbja) {
        this.zzesn = zzbja;
    }

    public static Context zza(zzbja zzbja) {
        return (Context) zzepe.zza(zzbja.zzaeb(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzesn);
    }
}

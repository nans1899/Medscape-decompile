package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbjc implements zzeoy<WeakReference<Context>> {
    private final zzbja zzesn;

    public zzbjc(zzbja zzbja) {
        this.zzesn = zzbja;
    }

    public final /* synthetic */ Object get() {
        return (WeakReference) zzepe.zza(this.zzesn.zzaec(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

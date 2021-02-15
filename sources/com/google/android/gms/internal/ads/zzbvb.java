package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbvb extends zzbyr<zzbuy> {
    public zzbvb(Set<zzcab<zzbuy>> set) {
        super(set);
    }

    public final void zzca(Context context) {
        zza(new zzbva(context));
    }

    public final void zzcb(Context context) {
        zza(new zzbvd(context));
    }

    public final void zzcc(Context context) {
        zza(new zzbvc(context));
    }

    public final void zza(zzcar zzcar, Executor executor) {
        zza(zzcab.zzb(new zzbvf(this, zzcar), executor));
    }
}

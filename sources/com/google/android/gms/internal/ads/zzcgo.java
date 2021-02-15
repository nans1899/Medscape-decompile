package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcgo implements zzeoy<zzcab<VideoController.VideoLifecycleCallbacks>> {
    private final zzeph<zzcjc> zzfop;
    private final zzeph<Executor> zzfqk;
    private final zzcgf zzgcr;

    public zzcgo(zzcgf zzcgf, zzeph<zzcjc> zzeph, zzeph<Executor> zzeph2) {
        this.zzgcr = zzcgf;
        this.zzfop = zzeph;
        this.zzfqk = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), this.zzfqk.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

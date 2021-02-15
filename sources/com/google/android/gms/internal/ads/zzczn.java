package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczn implements zzbwl {
    private final AtomicReference<zzyi> zzgtc = new AtomicReference<>();

    public final void zzb(zzyi zzyi) {
        this.zzgtc.set(zzyi);
    }

    public final void zzb(zzvp zzvp) {
        zzdll.zza(this.zzgtc, new zzczq(zzvp));
    }
}

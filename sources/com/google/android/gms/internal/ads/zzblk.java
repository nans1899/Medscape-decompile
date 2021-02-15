package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzblk implements zzeoy<zzdtn> {
    private final zzeph<Context> zzesu;

    public zzblk(zzeph<Context> zzeph) {
        this.zzesu = zzeph;
    }

    public final /* synthetic */ Object get() {
        return (zzdtn) zzepe.zza(new zzdtn(this.zzesu.get(), zzp.zzlf().zzyj()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

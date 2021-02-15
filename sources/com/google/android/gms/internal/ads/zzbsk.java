package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsk implements zzeoy<zza> {
    private final zzeph<Context> zzesu;
    private final zzbsh zzfte;
    private final zzeph<zzawl> zzftf;

    private zzbsk(zzbsh zzbsh, zzeph<Context> zzeph, zzeph<zzawl> zzeph2) {
        this.zzfte = zzbsh;
        this.zzesu = zzeph;
        this.zzftf = zzeph2;
    }

    public static zzbsk zza(zzbsh zzbsh, zzeph<Context> zzeph, zzeph<zzawl> zzeph2) {
        return new zzbsk(zzbsh, zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zza) zzepe.zza(new zza(this.zzesu.get(), this.zzftf.get(), (zzasr) null), "Cannot return null from a non-@Nullable @Provides method");
    }
}

package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdr implements zzeoy<zzcae> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdnv> zzfrj;

    public zzcdr(zzeph<Context> zzeph, zzeph<zzdnv> zzeph2) {
        this.zzesu = zzeph;
        this.zzfrj = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return (zzcae) zzepe.zza(new zzcae(this.zzesu.get(), new HashSet(), this.zzfrj.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

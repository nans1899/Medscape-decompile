package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcag implements zzeoy<zzcae> {
    private final zzeph<Context> zzesu;
    private final zzeph<Set<zzcab<zzqu>>> zzfqq;
    private final zzeph<zzdnv> zzfrj;

    private zzcag(zzeph<Context> zzeph, zzeph<Set<zzcab<zzqu>>> zzeph2, zzeph<zzdnv> zzeph3) {
        this.zzesu = zzeph;
        this.zzfqq = zzeph2;
        this.zzfrj = zzeph3;
    }

    public static zzcag zzk(zzeph<Context> zzeph, zzeph<Set<zzcab<zzqu>>> zzeph2, zzeph<zzdnv> zzeph3) {
        return new zzcag(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzcae(this.zzesu.get(), this.zzfqq.get(), this.zzfrj.get());
    }
}

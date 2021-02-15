package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzn implements zzeoy<zzcwj> {
    private final zzeph<zzcwl> zzewu;
    private final zzeph<zzctd> zzewv;
    private final zzeph<Clock> zzfok;
    private final zzbys zzfwg;

    private zzbzn(zzbys zzbys, zzeph<Clock> zzeph, zzeph<zzcwl> zzeph2, zzeph<zzctd> zzeph3) {
        this.zzfwg = zzbys;
        this.zzfok = zzeph;
        this.zzewu = zzeph2;
        this.zzewv = zzeph3;
    }

    public static zzbzn zza(zzbys zzbys, zzeph<Clock> zzeph, zzeph<zzcwl> zzeph2, zzeph<zzctd> zzeph3) {
        return new zzbzn(zzbys, zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return (zzcwj) zzepe.zza(this.zzfwg.zza(this.zzfok.get(), this.zzewu.get(), this.zzewv.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

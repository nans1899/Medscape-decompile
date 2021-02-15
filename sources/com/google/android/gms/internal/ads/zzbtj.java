package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbtj implements zzeoy<zzbtk> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdnv> zzfrj;
    private final zzeph<zzarj> zzftv;

    private zzbtj(zzeph<Context> zzeph, zzeph<zzdnv> zzeph2, zzeph<zzarj> zzeph3) {
        this.zzesu = zzeph;
        this.zzfrj = zzeph2;
        this.zzftv = zzeph3;
    }

    public static zzbtj zzh(zzeph<Context> zzeph, zzeph<zzdnv> zzeph2, zzeph<zzarj> zzeph3) {
        return new zzbtj(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzbtk(this.zzesu.get(), this.zzfrj.get(), this.zzftv.get());
    }
}

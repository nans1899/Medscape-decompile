package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcih implements zzeoy<zzcif> {
    private final zzeph<zzcfe> zzfem;
    private final zzeph<Context> zzfth;
    private final zzeph<zzcei> zzfym;
    private final zzeph<zzcdx> zzged;

    private zzcih(zzeph<Context> zzeph, zzeph<zzcei> zzeph2, zzeph<zzcfe> zzeph3, zzeph<zzcdx> zzeph4) {
        this.zzfth = zzeph;
        this.zzfym = zzeph2;
        this.zzfem = zzeph3;
        this.zzged = zzeph4;
    }

    public static zzcih zzc(zzeph<Context> zzeph, zzeph<zzcei> zzeph2, zzeph<zzcfe> zzeph3, zzeph<zzcdx> zzeph4) {
        return new zzcih(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzcif(this.zzfth.get(), this.zzfym.get(), this.zzfem.get(), this.zzged.get());
    }
}

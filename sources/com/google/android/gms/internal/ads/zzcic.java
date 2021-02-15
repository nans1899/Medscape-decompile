package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcic implements zzeoy<zzchx> {
    private final zzeph<zzcei> zzfym;
    private final zzeph<zzcdx> zzged;

    private zzcic(zzeph<zzcdx> zzeph, zzeph<zzcei> zzeph2) {
        this.zzged = zzeph;
        this.zzfym = zzeph2;
    }

    public static zzcic zzy(zzeph<zzcdx> zzeph, zzeph<zzcei> zzeph2) {
        return new zzcic(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzchx(this.zzged.get(), this.zzfym.get());
    }
}

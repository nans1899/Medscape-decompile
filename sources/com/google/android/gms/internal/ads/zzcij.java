package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcij implements zzeoy<zzcik> {
    private final zzeph<String> zzfun;
    private final zzeph<zzcei> zzfym;
    private final zzeph<zzcdx> zzged;

    private zzcij(zzeph<String> zzeph, zzeph<zzcdx> zzeph2, zzeph<zzcei> zzeph3) {
        this.zzfun = zzeph;
        this.zzged = zzeph2;
        this.zzfym = zzeph3;
    }

    public static zzcij zzn(zzeph<String> zzeph, zzeph<zzcdx> zzeph2, zzeph<zzcei> zzeph3) {
        return new zzcij(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzcik(this.zzfun.get(), this.zzged.get(), this.zzfym.get());
    }
}

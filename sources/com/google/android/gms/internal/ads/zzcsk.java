package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsk implements zzeoy<zzcsf> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzcsc> zzewe;
    private final zzeph<zzcrw> zzeyv;
    private final zzeph<zzbtm> zzgmy;

    private zzcsk(zzeph<Context> zzeph, zzeph<zzbtm> zzeph2, zzeph<zzcsc> zzeph3, zzeph<zzcrw> zzeph4) {
        this.zzesu = zzeph;
        this.zzgmy = zzeph2;
        this.zzewe = zzeph3;
        this.zzeyv = zzeph4;
    }

    public static zzcsk zze(zzeph<Context> zzeph, zzeph<zzbtm> zzeph2, zzeph<zzcsc> zzeph3, zzeph<zzcrw> zzeph4) {
        return new zzcsk(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzcsf(this.zzesu.get(), this.zzgmy.get(), this.zzewe.get(), this.zzeyv.get());
    }
}

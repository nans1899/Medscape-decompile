package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdjp implements zzeoy<zzdjm> {
    private final zzeph<zzbix> zzesp;
    private final zzeph<Executor> zzfqk;
    private final zzeph<Context> zzgzy;
    private final zzeph<zzdlv<zzbnq, zzbnv>> zzgzz;
    private final zzeph<zzdkc> zzhaa;
    private final zzeph<zzdom> zzhab;

    public zzdjp(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<zzbix> zzeph3, zzeph<zzdlv<zzbnq, zzbnv>> zzeph4, zzeph<zzdkc> zzeph5, zzeph<zzdom> zzeph6) {
        this.zzgzy = zzeph;
        this.zzfqk = zzeph2;
        this.zzesp = zzeph3;
        this.zzgzz = zzeph4;
        this.zzhaa = zzeph5;
        this.zzhab = zzeph6;
    }

    public final /* synthetic */ Object get() {
        return new zzdjm(this.zzgzy.get(), this.zzfqk.get(), this.zzesp.get(), this.zzgzz.get(), this.zzhaa.get(), this.zzhab.get());
    }
}

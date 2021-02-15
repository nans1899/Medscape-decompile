package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzctu implements zzeoy<zzctq> {
    private final zzeph<Context> zzesu;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzcjt> zzgbz;
    private final zzeph<zzbnq> zzgof;

    public zzctu(zzeph<zzbnq> zzeph, zzeph<Context> zzeph2, zzeph<Executor> zzeph3, zzeph<zzcjt> zzeph4) {
        this.zzgof = zzeph;
        this.zzesu = zzeph2;
        this.zzfqk = zzeph3;
        this.zzgbz = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzctq(this.zzgof.get(), this.zzesu.get(), this.zzfqk.get(), this.zzgbz.get());
    }
}

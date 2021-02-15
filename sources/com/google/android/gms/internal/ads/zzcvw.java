package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvw implements zzeoy<zzcvt> {
    private final zzeph<Context> zzesu;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzcda> zzgof;

    public zzcvw(zzeph<Context> zzeph, zzeph<zzcda> zzeph2, zzeph<Executor> zzeph3) {
        this.zzesu = zzeph;
        this.zzgof = zzeph2;
        this.zzfqk = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzcvt(this.zzesu.get(), this.zzgof.get(), this.zzfqk.get());
    }
}

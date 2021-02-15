package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcuo implements zzeoy<zzcuj> {
    private final zzeph<Context> zzesu;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzcce> zzgof;
    private final zzeph<zzdnt> zzgox;

    public zzcuo(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<zzcce> zzeph3, zzeph<zzdnt> zzeph4) {
        this.zzesu = zzeph;
        this.zzfqk = zzeph2;
        this.zzgof = zzeph3;
        this.zzgox = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzcuj(this.zzesu.get(), this.zzfqk.get(), this.zzgof.get(), this.zzgox.get());
    }
}

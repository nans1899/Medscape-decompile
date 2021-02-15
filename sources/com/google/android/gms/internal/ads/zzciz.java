package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzciz implements zzeoy<zzciy> {
    private final zzeph<Context> zzesu;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzbbx> zzfup;
    private final zzeph<zzeg> zzfyn;
    private final zzeph<zzb> zzgds;
    private final zzeph<zzbgr> zzgey;

    public zzciz(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<zzeg> zzeph3, zzeph<zzbbx> zzeph4, zzeph<zzb> zzeph5, zzeph<zzbgr> zzeph6) {
        this.zzesu = zzeph;
        this.zzfqk = zzeph2;
        this.zzfyn = zzeph3;
        this.zzfup = zzeph4;
        this.zzgds = zzeph5;
        this.zzgey = zzeph6;
    }

    public final /* synthetic */ Object get() {
        return new zzciy(this.zzesu.get(), this.zzfqk.get(), this.zzfyn.get(), this.zzfup.get(), this.zzgds.get(), this.zzgey.get());
    }
}

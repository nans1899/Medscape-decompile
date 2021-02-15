package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcrl implements zzeoy<zzcqv> {
    private final zzeph<Context> zzesu;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzatt> zzgln;
    private final zzeph<zzbly> zzglo;
    private final zzeph<zzatq> zzglp;
    private final zzeph<HashMap<String, zzcrk>> zzglq;

    private zzcrl(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<zzatt> zzeph3, zzeph<zzbly> zzeph4, zzeph<zzatq> zzeph5, zzeph<HashMap<String, zzcrk>> zzeph6) {
        this.zzesu = zzeph;
        this.zzfqk = zzeph2;
        this.zzgln = zzeph3;
        this.zzglo = zzeph4;
        this.zzglp = zzeph5;
        this.zzglq = zzeph6;
    }

    public static zzcrl zzb(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<zzatt> zzeph3, zzeph<zzbly> zzeph4, zzeph<zzatq> zzeph5, zzeph<HashMap<String, zzcrk>> zzeph6) {
        return new zzcrl(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6);
    }

    public final /* synthetic */ Object get() {
        return new zzcqv(this.zzesu.get(), this.zzfqk.get(), this.zzgln.get(), this.zzglo.get(), this.zzglp.get(), this.zzglq.get());
    }
}

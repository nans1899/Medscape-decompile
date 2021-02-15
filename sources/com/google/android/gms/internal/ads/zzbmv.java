package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmv implements zzeoy<zzbmu> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzamk> zzfoh;
    private final zzeph<zzbms> zzfoi;
    private final zzeph<zzbml> zzfoj;
    private final zzeph<Clock> zzfok;

    private zzbmv(zzeph<zzamk> zzeph, zzeph<zzbms> zzeph2, zzeph<Executor> zzeph3, zzeph<zzbml> zzeph4, zzeph<Clock> zzeph5) {
        this.zzfoh = zzeph;
        this.zzfoi = zzeph2;
        this.zzesr = zzeph3;
        this.zzfoj = zzeph4;
        this.zzfok = zzeph5;
    }

    public static zzbmv zza(zzeph<zzamk> zzeph, zzeph<zzbms> zzeph2, zzeph<Executor> zzeph3, zzeph<zzbml> zzeph4, zzeph<Clock> zzeph5) {
        return new zzbmv(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        return new zzbmu(this.zzfoh.get(), this.zzfoi.get(), this.zzesr.get(), this.zzfoj.get(), this.zzfok.get());
    }
}

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbl implements zzdya<zzatc, zzdbm> {
    private Executor executor;
    private zzcpc zzguu;

    public zzdbl(Executor executor2, zzcpc zzcpc) {
        this.executor = executor2;
        this.zzguu = zzcpc;
    }

    public final /* synthetic */ zzdzc zzf(Object obj) throws Exception {
        zzatc zzatc = (zzatc) obj;
        return zzdyq.zzb(this.zzguu.zzg(zzatc), new zzdbk(zzatc), this.executor);
    }
}

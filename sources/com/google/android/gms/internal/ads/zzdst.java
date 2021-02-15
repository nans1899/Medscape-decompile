package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdst {
    private final Executor executor;
    private final zzbbu zzhln;

    public zzdst(Executor executor2, zzbbu zzbbu) {
        this.executor = executor2;
        this.zzhln = zzbbu;
    }

    public final void zzj(List<String> list) {
        for (String zzes : list) {
            zzes(zzes);
        }
    }

    public final void zzes(String str) {
        this.executor.execute(new zzdsw(this, str));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzgz(String str) {
        this.zzhln.zzes(str);
    }
}

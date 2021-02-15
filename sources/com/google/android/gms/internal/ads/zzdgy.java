package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdgy implements Callable {
    private final Object zzdjf;
    private final List zzgxm;

    zzdgy(List list, Object obj) {
        this.zzgxm = list;
        this.zzdjf = obj;
    }

    public final Object call() {
        List<zzdzc> list = this.zzgxm;
        Object obj = this.zzdjf;
        for (zzdzc zzdzc : list) {
            zzdgu zzdgu = (zzdgu) zzdzc.get();
            if (zzdgu != null) {
                zzdgu.zzs(obj);
            }
        }
        return obj;
    }
}

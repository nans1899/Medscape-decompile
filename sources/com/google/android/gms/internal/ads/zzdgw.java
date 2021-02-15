package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgw<T> {
    private final Executor executor;
    private final Set<zzdgx<? extends zzdgu<T>>> zzgyf;

    public zzdgw(Executor executor2, Set<zzdgx<? extends zzdgu<T>>> set) {
        this.executor = executor2;
        this.zzgyf = set;
    }

    public final zzdzc<T> zzt(T t) {
        ArrayList arrayList = new ArrayList(this.zzgyf.size());
        for (zzdgx next : this.zzgyf) {
            zzdzc zzarj = next.zzarj();
            if (zzacv.zzdbq.get().booleanValue()) {
                zzarj.addListener(new zzdgz(next, zzp.zzky().elapsedRealtime()), zzbbz.zzeeu);
            }
            arrayList.add(zzarj);
        }
        return zzdyq.zzk(arrayList).zza(new zzdgy(arrayList, t), this.executor);
    }
}

package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzblx implements zzdya<zzdog, zzdog> {
    private Map<String, zzbma> zzfms;

    public zzblx(Map<String, zzbma> map) {
        this.zzfms = map;
    }

    public final /* synthetic */ zzdzc zzf(Object obj) throws Exception {
        zzdog zzdog = (zzdog) obj;
        for (zzdoh next : zzdog.zzhew.zzheu) {
            if (this.zzfms.containsKey(next.name)) {
                this.zzfms.get(next.name).zzl(next.zzhex);
            }
        }
        return zzdyq.zzaf(zzdog);
    }
}

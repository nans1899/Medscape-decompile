package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzabp {
    private final Map<String, zzabq> zzczf = new HashMap();
    private final zzabs zzczg;

    public zzabp(zzabs zzabs) {
        this.zzczg = zzabs;
    }

    public final void zza(String str, zzabq zzabq) {
        this.zzczf.put(str, zzabq);
    }

    public final void zzb(String str, String str2, long j) {
        zzabq zzabq;
        zzabs zzabs = this.zzczg;
        zzabq zzabq2 = this.zzczf.get(str2);
        String[] strArr = {str};
        if (!(zzabs == null || zzabq2 == null)) {
            zzabs.zza(zzabq2, j, strArr);
        }
        Map<String, zzabq> map = this.zzczf;
        zzabs zzabs2 = this.zzczg;
        if (zzabs2 == null) {
            zzabq = null;
        } else {
            zzabq = zzabs2.zzex(j);
        }
        map.put(str, zzabq);
    }

    public final zzabs zzrp() {
        return this.zzczg;
    }
}

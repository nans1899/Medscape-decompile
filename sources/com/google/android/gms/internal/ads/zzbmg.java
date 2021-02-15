package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmg implements zzbma {
    private final zzayr zzdzr;

    public zzbmg(zzayr zzayr) {
        this.zzdzr = zzayr;
    }

    public final void zzl(Map<String, String> map) {
        String str = map.get("value");
        if ("auto_collect_location".equals(map.get("key"))) {
            this.zzdzr.zzaq(Boolean.parseBoolean(str));
        }
    }
}

package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmb implements zzbma {
    private zzayr zzdzr;

    public zzbmb(zzayr zzayr) {
        this.zzdzr = zzayr;
    }

    public final void zzl(Map<String, String> map) {
        this.zzdzr.zzap(Boolean.parseBoolean(map.get("content_vertical_opted_out")));
    }
}

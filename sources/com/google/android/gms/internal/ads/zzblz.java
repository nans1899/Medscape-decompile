package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzblz implements zzbma {
    private zzayr zzdzr;

    public zzblz(zzayr zzayr) {
        this.zzdzr = zzayr;
    }

    public final void zzl(Map<String, String> map) {
        this.zzdzr.zzao(Boolean.parseBoolean(map.get("content_url_opted_out")));
    }
}

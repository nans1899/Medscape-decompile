package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaqf {
    private final zzbgj zzdgy;
    private final boolean zzdnp;
    private final String zzdnq;

    public zzaqf(zzbgj zzbgj, Map<String, String> map) {
        this.zzdgy = zzbgj;
        this.zzdnq = map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzdnp = Boolean.parseBoolean(map.get("allowOrientationChange"));
        } else {
            this.zzdnp = true;
        }
    }

    public final void execute() {
        int i;
        if (this.zzdgy == null) {
            zzayp.zzfe("AdWebView is null");
            return;
        }
        if ("portrait".equalsIgnoreCase(this.zzdnq)) {
            zzp.zzkt();
            i = 7;
        } else if ("landscape".equalsIgnoreCase(this.zzdnq)) {
            zzp.zzkt();
            i = 6;
        } else if (this.zzdnp) {
            i = -1;
        } else {
            i = zzp.zzkt().zzxw();
        }
        this.zzdgy.setRequestedOrientation(i);
    }
}

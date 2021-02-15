package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdev implements zzdgu<Bundle> {
    private final zzdnt zzfmt;

    public zzdev(zzdnt zzdnt) {
        this.zzfmt = zzdnt;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        zzdnt zzdnt = this.zzfmt;
        if (zzdnt != null) {
            bundle.putBoolean("render_in_browser", zzdnt.zzate());
            bundle.putBoolean("disable_ml", this.zzfmt.zzatf());
        }
    }
}

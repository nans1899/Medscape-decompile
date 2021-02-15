package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaxz {
    private long zzdyw = -1;
    private long zzdyx = -1;
    private final /* synthetic */ zzaxw zzdyy;

    public zzaxz(zzaxw zzaxw) {
        this.zzdyy = zzaxw;
    }

    public final long zzwk() {
        return this.zzdyx;
    }

    public final void zzwl() {
        this.zzdyx = this.zzdyy.zzbqa.elapsedRealtime();
    }

    public final void zzwm() {
        this.zzdyw = this.zzdyy.zzbqa.elapsedRealtime();
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong("topen", this.zzdyw);
        bundle.putLong("tclose", this.zzdyx);
        return bundle;
    }
}

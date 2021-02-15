package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzawt implements zzaxe {
    private final String zzdgt;
    private final Bundle zzdyd;

    zzawt(String str, Bundle bundle) {
        this.zzdgt = str;
        this.zzdyd = bundle;
    }

    public final void zzb(zzbiq zzbiq) {
        zzbiq.logEvent("am", this.zzdgt, this.zzdyd);
    }
}

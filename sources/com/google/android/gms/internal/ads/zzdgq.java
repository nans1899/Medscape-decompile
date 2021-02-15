package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdgq implements zzdgu {
    private final Bundle zzgxy;

    zzdgq(Bundle bundle) {
        this.zzgxy = bundle;
    }

    public final void zzs(Object obj) {
        ((Bundle) obj).putBundle("shared_pref", this.zzgxy);
    }
}

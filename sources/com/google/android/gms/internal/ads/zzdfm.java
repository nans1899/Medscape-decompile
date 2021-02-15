package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfm implements zzdgu<Bundle> {
    private final Bundle zzfuh;

    public zzdfm(Bundle bundle) {
        this.zzfuh = bundle;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        Bundle bundle2 = this.zzfuh;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }
}

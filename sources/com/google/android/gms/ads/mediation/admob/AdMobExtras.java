package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class AdMobExtras implements NetworkExtras {
    private final Bundle extras;

    public AdMobExtras(Bundle bundle) {
        this.extras = bundle != null ? new Bundle(bundle) : null;
    }

    public final Bundle getExtras() {
        return this.extras;
    }
}

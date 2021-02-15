package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.AdFormat;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class MediationConfiguration {
    private final Bundle zzerb;
    private final AdFormat zzerf;

    public MediationConfiguration(AdFormat adFormat, Bundle bundle) {
        this.zzerf = adFormat;
        this.zzerb = bundle;
    }

    public AdFormat getFormat() {
        return this.zzerf;
    }

    public Bundle getServerParameters() {
        return this.zzerb;
    }
}

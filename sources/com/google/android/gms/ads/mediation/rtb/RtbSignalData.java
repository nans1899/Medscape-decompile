package com.google.android.gms.ads.mediation.rtb;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class RtbSignalData {
    private final Bundle zzchi;
    private final AdSize zzdt;
    private final List<MediationConfiguration> zzesk;
    private final Context zzvr;

    public RtbSignalData(Context context, List<MediationConfiguration> list, Bundle bundle, AdSize adSize) {
        this.zzvr = context;
        this.zzesk = list;
        this.zzchi = bundle;
        this.zzdt = adSize;
    }

    public Context getContext() {
        return this.zzvr;
    }

    @Deprecated
    public MediationConfiguration getConfiguration() {
        List<MediationConfiguration> list = this.zzesk;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return this.zzesk.get(0);
    }

    public List<MediationConfiguration> getConfigurations() {
        return this.zzesk;
    }

    public Bundle getNetworkExtras() {
        return this.zzchi;
    }

    public AdSize getAdSize() {
        return this.zzdt;
    }
}

package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcg implements zzdgu<Bundle> {
    private final Bundle zzgvn;

    private zzdcg(Bundle bundle) {
        this.zzgvn = bundle;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        if (!this.zzgvn.isEmpty()) {
            bundle.putBundle("installed_adapter_data", this.zzgvn);
        }
    }
}

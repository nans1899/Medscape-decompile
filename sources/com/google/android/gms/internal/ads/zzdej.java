package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.medscape.android.analytics.FirebaseEventsConstants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdej implements zzdgu<Bundle> {
    private final Bundle zzgwu;

    public zzdej(Bundle bundle) {
        this.zzgwu = bundle;
    }

    public final /* synthetic */ void zzs(Object obj) {
        ((Bundle) obj).putBundle(FirebaseEventsConstants.CONTENT_INFO, this.zzgwu);
    }
}

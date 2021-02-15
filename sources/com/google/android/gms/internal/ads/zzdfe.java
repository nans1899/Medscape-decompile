package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import java.util.Set;
import net.media.android.bidder.base.common.Constants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfe implements zzdgu<Bundle> {
    private final String zzgxb;

    /* access modifiers changed from: private */
    public static boolean zze(Set<String> set) {
        return set.contains("rewarded") || set.contains("interstitial") || set.contains(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE) || set.contains(Constants.Capabilities.BANNER);
    }

    public zzdfe(String str) {
        this.zzgxb = str;
    }

    public final /* synthetic */ void zzs(Object obj) {
        zzdot.zza((Bundle) obj, "omid_v", this.zzgxb);
    }
}

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.media.android.bidder.base.common.Constants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcms {
    private ConcurrentHashMap<String, String> zzghg;

    public zzcms(zzcmz zzcmz) {
        this.zzghg = zzcmz.zzaph();
    }

    public final void zzc(zzdog zzdog) {
        if (zzdog.zzhew.zzhet.size() > 0) {
            int i = zzdog.zzhew.zzhet.get(0).zzhdj;
            if (i == 1) {
                this.zzghg.put(FirebaseAnalytics.Param.AD_FORMAT, Constants.Capabilities.BANNER);
            } else if (i == 2) {
                this.zzghg.put(FirebaseAnalytics.Param.AD_FORMAT, "interstitial");
            } else if (i == 3) {
                this.zzghg.put(FirebaseAnalytics.Param.AD_FORMAT, "native_express");
            } else if (i == 4) {
                this.zzghg.put(FirebaseAnalytics.Param.AD_FORMAT, "native_advanced");
            } else if (i != 5) {
                this.zzghg.put(FirebaseAnalytics.Param.AD_FORMAT, "unknown");
            } else {
                this.zzghg.put(FirebaseAnalytics.Param.AD_FORMAT, "rewarded");
            }
        }
        if (!TextUtils.isEmpty(zzdog.zzhew.zzeou.zzdtb)) {
            this.zzghg.put("gqi", zzdog.zzhew.zzeou.zzdtb);
        }
    }

    public final void zzj(Bundle bundle) {
        if (bundle.containsKey("cnt")) {
            this.zzghg.put("network_coarse", Integer.toString(bundle.getInt("cnt")));
        }
        if (bundle.containsKey("gnt")) {
            this.zzghg.put("network_fine", Integer.toString(bundle.getInt("gnt")));
        }
    }

    public final Map<String, String> zzru() {
        return this.zzghg;
    }
}

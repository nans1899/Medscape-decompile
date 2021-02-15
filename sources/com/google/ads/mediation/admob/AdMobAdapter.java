package com.google.ads.mediation.admob;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class AdMobAdapter extends AbstractAdViewAdapter {
    public static final String NEW_BUNDLE = "_newBundle";

    /* access modifiers changed from: protected */
    public final Bundle zza(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (bundle.getBoolean(NEW_BUNDLE)) {
            bundle = new Bundle(bundle);
        }
        bundle.putInt("gw", 1);
        bundle.putString("mad_hac", bundle2.getString("mad_hac"));
        if (!TextUtils.isEmpty(bundle2.getString("adJson"))) {
            bundle.putString("_ad", bundle2.getString("adJson"));
        }
        bundle.putBoolean("_noRefresh", true);
        return bundle;
    }
}

package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface MediationNativeAdapter extends MediationAdapter {
    void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2);
}

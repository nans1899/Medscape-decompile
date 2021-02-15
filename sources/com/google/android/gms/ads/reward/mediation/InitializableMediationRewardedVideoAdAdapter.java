package com.google.android.gms.ads.reward.mediation;

import android.content.Context;
import android.os.Bundle;
import java.util.List;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface InitializableMediationRewardedVideoAdAdapter extends MediationRewardedVideoAdAdapter {
    void initialize(Context context, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, List<Bundle> list);
}

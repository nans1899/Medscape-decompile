package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class CampaignCacheClient$$Lambda$3 implements Consumer {
    private final CampaignCacheClient arg$1;

    private CampaignCacheClient$$Lambda$3(CampaignCacheClient campaignCacheClient) {
        this.arg$1 = campaignCacheClient;
    }

    public static Consumer lambdaFactory$(CampaignCacheClient campaignCacheClient) {
        return new CampaignCacheClient$$Lambda$3(campaignCacheClient);
    }

    public void accept(Object obj) {
        this.arg$1.cachedResponse = (FetchEligibleCampaignsResponse) obj;
    }
}

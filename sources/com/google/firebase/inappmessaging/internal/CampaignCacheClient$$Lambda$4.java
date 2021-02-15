package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Predicate;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class CampaignCacheClient$$Lambda$4 implements Predicate {
    private final CampaignCacheClient arg$1;

    private CampaignCacheClient$$Lambda$4(CampaignCacheClient campaignCacheClient) {
        this.arg$1 = campaignCacheClient;
    }

    public static Predicate lambdaFactory$(CampaignCacheClient campaignCacheClient) {
        return new CampaignCacheClient$$Lambda$4(campaignCacheClient);
    }

    public boolean test(Object obj) {
        return this.arg$1.isResponseValid((FetchEligibleCampaignsResponse) obj);
    }
}

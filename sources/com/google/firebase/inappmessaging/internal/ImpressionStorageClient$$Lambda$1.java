package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpression;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ImpressionStorageClient$$Lambda$1 implements Function {
    private final ImpressionStorageClient arg$1;
    private final CampaignImpression arg$2;

    private ImpressionStorageClient$$Lambda$1(ImpressionStorageClient impressionStorageClient, CampaignImpression campaignImpression) {
        this.arg$1 = impressionStorageClient;
        this.arg$2 = campaignImpression;
    }

    public static Function lambdaFactory$(ImpressionStorageClient impressionStorageClient, CampaignImpression campaignImpression) {
        return new ImpressionStorageClient$$Lambda$1(impressionStorageClient, campaignImpression);
    }

    public Object apply(Object obj) {
        return ImpressionStorageClient.appendImpression((CampaignImpressionList) obj, this.arg$2);
    }
}

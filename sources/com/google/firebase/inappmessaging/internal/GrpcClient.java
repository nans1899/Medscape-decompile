package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsRequest;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.InAppMessagingSdkServingGrpc;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class GrpcClient {
    private final InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub stub;

    @Inject
    GrpcClient(InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub inAppMessagingSdkServingBlockingStub) {
        this.stub = inAppMessagingSdkServingBlockingStub;
    }

    public FetchEligibleCampaignsResponse fetchEligibleCampaigns(FetchEligibleCampaignsRequest fetchEligibleCampaignsRequest) {
        return this.stub.fetchEligibleCampaigns(fetchEligibleCampaignsRequest);
    }
}

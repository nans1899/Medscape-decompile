package com.google.firebase.inappmessaging.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ApiClient$$Lambda$1 implements Continuation {
    private final ApiClient arg$1;
    private final CampaignImpressionList arg$2;

    private ApiClient$$Lambda$1(ApiClient apiClient, CampaignImpressionList campaignImpressionList) {
        this.arg$1 = apiClient;
        this.arg$2 = campaignImpressionList;
    }

    public static Continuation lambdaFactory$(ApiClient apiClient, CampaignImpressionList campaignImpressionList) {
        return new ApiClient$$Lambda$1(apiClient, campaignImpressionList);
    }

    public Object then(Task task) {
        return ApiClient.lambda$getFiams$0(this.arg$1, this.arg$2, task);
    }
}

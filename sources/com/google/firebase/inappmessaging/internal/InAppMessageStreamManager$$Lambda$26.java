package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$26 implements Consumer {
    private final AnalyticsEventsManager arg$1;

    private InAppMessageStreamManager$$Lambda$26(AnalyticsEventsManager analyticsEventsManager) {
        this.arg$1 = analyticsEventsManager;
    }

    public static Consumer lambdaFactory$(AnalyticsEventsManager analyticsEventsManager) {
        return new InAppMessageStreamManager$$Lambda$26(analyticsEventsManager);
    }

    public void accept(Object obj) {
        this.arg$1.updateContextualTriggers((FetchEligibleCampaignsResponse) obj);
    }
}

package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$27 implements Consumer {
    private final TestDeviceHelper arg$1;

    private InAppMessageStreamManager$$Lambda$27(TestDeviceHelper testDeviceHelper) {
        this.arg$1 = testDeviceHelper;
    }

    public static Consumer lambdaFactory$(TestDeviceHelper testDeviceHelper) {
        return new InAppMessageStreamManager$$Lambda$27(testDeviceHelper);
    }

    public void accept(Object obj) {
        this.arg$1.processCampaignFetch((FetchEligibleCampaignsResponse) obj);
    }
}

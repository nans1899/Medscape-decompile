package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$17 implements Consumer {
    private final InAppMessageStreamManager arg$1;

    private InAppMessageStreamManager$$Lambda$17(InAppMessageStreamManager inAppMessageStreamManager) {
        this.arg$1 = inAppMessageStreamManager;
    }

    public static Consumer lambdaFactory$(InAppMessageStreamManager inAppMessageStreamManager) {
        return new InAppMessageStreamManager$$Lambda$17(inAppMessageStreamManager);
    }

    public void accept(Object obj) {
        this.arg$1.campaignCacheClient.put((FetchEligibleCampaignsResponse) obj).doOnComplete(InAppMessageStreamManager$$Lambda$33.lambdaFactory$()).doOnError(InAppMessageStreamManager$$Lambda$34.lambdaFactory$()).onErrorResumeNext(InAppMessageStreamManager$$Lambda$35.lambdaFactory$()).subscribe();
    }
}

package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$25 implements Consumer {
    private final InAppMessageStreamManager arg$1;

    private InAppMessageStreamManager$$Lambda$25(InAppMessageStreamManager inAppMessageStreamManager) {
        this.arg$1 = inAppMessageStreamManager;
    }

    public static Consumer lambdaFactory$(InAppMessageStreamManager inAppMessageStreamManager) {
        return new InAppMessageStreamManager$$Lambda$25(inAppMessageStreamManager);
    }

    public void accept(Object obj) {
        this.arg$1.impressionStorageClient.clearImpressions((FetchEligibleCampaignsResponse) obj).subscribe();
    }
}

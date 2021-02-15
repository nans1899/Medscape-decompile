package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$23 implements Function {
    private final InAppMessageStreamManager arg$1;

    private InAppMessageStreamManager$$Lambda$23(InAppMessageStreamManager inAppMessageStreamManager) {
        this.arg$1 = inAppMessageStreamManager;
    }

    public static Function lambdaFactory$(InAppMessageStreamManager inAppMessageStreamManager) {
        return new InAppMessageStreamManager$$Lambda$23(inAppMessageStreamManager);
    }

    public Object apply(Object obj) {
        return InAppMessageStreamManager.lambda$createFirebaseInAppMessageStream$19(this.arg$1, (CampaignImpressionList) obj);
    }
}

package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import io.reactivex.functions.Predicate;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$8 implements Predicate {
    private final InAppMessageStreamManager arg$1;

    private InAppMessageStreamManager$$Lambda$8(InAppMessageStreamManager inAppMessageStreamManager) {
        this.arg$1 = inAppMessageStreamManager;
    }

    public static Predicate lambdaFactory$(InAppMessageStreamManager inAppMessageStreamManager) {
        return new InAppMessageStreamManager$$Lambda$8(inAppMessageStreamManager);
    }

    public boolean test(Object obj) {
        return InAppMessageStreamManager.lambda$getTriggeredInAppMessageMaybe$24(this.arg$1, (CampaignProto.ThickContent) obj);
    }
}

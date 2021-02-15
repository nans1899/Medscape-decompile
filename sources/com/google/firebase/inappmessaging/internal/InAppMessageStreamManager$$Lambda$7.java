package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$7 implements Function {
    private final CampaignProto.ThickContent arg$1;

    private InAppMessageStreamManager$$Lambda$7(CampaignProto.ThickContent thickContent) {
        this.arg$1 = thickContent;
    }

    public static Function lambdaFactory$(CampaignProto.ThickContent thickContent) {
        return new InAppMessageStreamManager$$Lambda$7(thickContent);
    }

    public Object apply(Object obj) {
        return InAppMessageStreamManager.lambda$getContentIfNotRateLimited$23(this.arg$1, (Boolean) obj);
    }
}

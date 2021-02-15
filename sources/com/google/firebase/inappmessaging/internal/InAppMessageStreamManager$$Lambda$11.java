package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$11 implements Function {
    private final InAppMessageStreamManager arg$1;
    private final String arg$2;

    private InAppMessageStreamManager$$Lambda$11(InAppMessageStreamManager inAppMessageStreamManager, String str) {
        this.arg$1 = inAppMessageStreamManager;
        this.arg$2 = str;
    }

    public static Function lambdaFactory$(InAppMessageStreamManager inAppMessageStreamManager, String str) {
        return new InAppMessageStreamManager$$Lambda$11(inAppMessageStreamManager, str);
    }

    public Object apply(Object obj) {
        return this.arg$1.triggeredInAppMessage((CampaignProto.ThickContent) obj, this.arg$2);
    }
}

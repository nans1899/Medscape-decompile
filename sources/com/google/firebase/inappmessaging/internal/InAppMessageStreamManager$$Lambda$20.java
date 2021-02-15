package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$20 implements Function {
    private static final InAppMessageStreamManager$$Lambda$20 instance = new InAppMessageStreamManager$$Lambda$20();

    private InAppMessageStreamManager$$Lambda$20() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return InAppMessageStreamManager.lambda$createFirebaseInAppMessageStream$13((CampaignProto.ThickContent) obj);
    }
}

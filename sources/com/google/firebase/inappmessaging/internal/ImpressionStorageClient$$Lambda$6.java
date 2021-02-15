package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpression;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ImpressionStorageClient$$Lambda$6 implements Function {
    private static final ImpressionStorageClient$$Lambda$6 instance = new ImpressionStorageClient$$Lambda$6();

    private ImpressionStorageClient$$Lambda$6() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((CampaignImpression) obj).getCampaignId();
    }
}

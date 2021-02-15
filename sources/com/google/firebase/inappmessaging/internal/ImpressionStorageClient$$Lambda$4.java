package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ImpressionStorageClient$$Lambda$4 implements Function {
    private static final ImpressionStorageClient$$Lambda$4 instance = new ImpressionStorageClient$$Lambda$4();

    private ImpressionStorageClient$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((CampaignImpressionList) obj).getAlreadySeenCampaignsList();
    }
}

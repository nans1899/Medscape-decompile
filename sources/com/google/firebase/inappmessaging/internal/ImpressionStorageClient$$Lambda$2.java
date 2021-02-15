package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ImpressionStorageClient$$Lambda$2 implements Consumer {
    private final ImpressionStorageClient arg$1;

    private ImpressionStorageClient$$Lambda$2(ImpressionStorageClient impressionStorageClient) {
        this.arg$1 = impressionStorageClient;
    }

    public static Consumer lambdaFactory$(ImpressionStorageClient impressionStorageClient) {
        return new ImpressionStorageClient$$Lambda$2(impressionStorageClient);
    }

    public void accept(Object obj) {
        this.arg$1.initInMemCache((CampaignImpressionList) obj);
    }
}

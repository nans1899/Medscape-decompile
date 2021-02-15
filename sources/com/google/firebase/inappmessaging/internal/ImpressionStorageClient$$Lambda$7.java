package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;
import io.reactivex.functions.Function;
import java.util.HashSet;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ImpressionStorageClient$$Lambda$7 implements Function {
    private final ImpressionStorageClient arg$1;
    private final HashSet arg$2;

    private ImpressionStorageClient$$Lambda$7(ImpressionStorageClient impressionStorageClient, HashSet hashSet) {
        this.arg$1 = impressionStorageClient;
        this.arg$2 = hashSet;
    }

    public static Function lambdaFactory$(ImpressionStorageClient impressionStorageClient, HashSet hashSet) {
        return new ImpressionStorageClient$$Lambda$7(impressionStorageClient, hashSet);
    }

    public Object apply(Object obj) {
        return ImpressionStorageClient.lambda$clearImpressions$4(this.arg$1, this.arg$2, (CampaignImpressionList) obj);
    }
}

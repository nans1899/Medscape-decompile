package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$21 implements Function {
    private final InAppMessageStreamManager arg$1;
    private final String arg$2;
    private final Function arg$3;
    private final Function arg$4;
    private final Function arg$5;

    private InAppMessageStreamManager$$Lambda$21(InAppMessageStreamManager inAppMessageStreamManager, String str, Function function, Function function2, Function function3) {
        this.arg$1 = inAppMessageStreamManager;
        this.arg$2 = str;
        this.arg$3 = function;
        this.arg$4 = function2;
        this.arg$5 = function3;
    }

    public static Function lambdaFactory$(InAppMessageStreamManager inAppMessageStreamManager, String str, Function function, Function function2, Function function3) {
        return new InAppMessageStreamManager$$Lambda$21(inAppMessageStreamManager, str, function, function2, function3);
    }

    public Object apply(Object obj) {
        return this.arg$1.getTriggeredInAppMessageMaybe(this.arg$2, this.arg$3, this.arg$4, this.arg$5, (FetchEligibleCampaignsResponse) obj);
    }
}

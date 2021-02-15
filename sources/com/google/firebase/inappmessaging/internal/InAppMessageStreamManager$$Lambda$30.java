package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$30 implements Consumer {
    private final CampaignProto.ThickContent arg$1;

    private InAppMessageStreamManager$$Lambda$30(CampaignProto.ThickContent thickContent) {
        this.arg$1 = thickContent;
    }

    public static Consumer lambdaFactory$(CampaignProto.ThickContent thickContent) {
        return new InAppMessageStreamManager$$Lambda$30(thickContent);
    }

    public void accept(Object obj) {
        InAppMessageStreamManager.logImpressionStatus(this.arg$1, (Boolean) obj);
    }
}

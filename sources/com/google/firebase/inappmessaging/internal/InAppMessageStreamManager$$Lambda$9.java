package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import io.reactivex.functions.Predicate;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$9 implements Predicate {
    private final String arg$1;

    private InAppMessageStreamManager$$Lambda$9(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new InAppMessageStreamManager$$Lambda$9(str);
    }

    public boolean test(Object obj) {
        return InAppMessageStreamManager.containsTriggeringCondition(this.arg$1, (CampaignProto.ThickContent) obj);
    }
}

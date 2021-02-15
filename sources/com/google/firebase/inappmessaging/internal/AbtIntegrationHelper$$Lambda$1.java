package com.google.firebase.inappmessaging.internal;

import java.util.ArrayList;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class AbtIntegrationHelper$$Lambda$1 implements Runnable {
    private final AbtIntegrationHelper arg$1;
    private final ArrayList arg$2;

    private AbtIntegrationHelper$$Lambda$1(AbtIntegrationHelper abtIntegrationHelper, ArrayList arrayList) {
        this.arg$1 = abtIntegrationHelper;
        this.arg$2 = arrayList;
    }

    public static Runnable lambdaFactory$(AbtIntegrationHelper abtIntegrationHelper, ArrayList arrayList) {
        return new AbtIntegrationHelper$$Lambda$1(abtIntegrationHelper, arrayList);
    }

    public void run() {
        AbtIntegrationHelper.lambda$updateRunningExperiments$0(this.arg$1, this.arg$2);
    }
}

package com.google.firebase.inappmessaging.internal;

import developers.mobile.abt.FirebaseAbt;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class AbtIntegrationHelper$$Lambda$2 implements Runnable {
    private final AbtIntegrationHelper arg$1;
    private final FirebaseAbt.ExperimentPayload arg$2;

    private AbtIntegrationHelper$$Lambda$2(AbtIntegrationHelper abtIntegrationHelper, FirebaseAbt.ExperimentPayload experimentPayload) {
        this.arg$1 = abtIntegrationHelper;
        this.arg$2 = experimentPayload;
    }

    public static Runnable lambdaFactory$(AbtIntegrationHelper abtIntegrationHelper, FirebaseAbt.ExperimentPayload experimentPayload) {
        return new AbtIntegrationHelper$$Lambda$2(abtIntegrationHelper, experimentPayload);
    }

    public void run() {
        AbtIntegrationHelper.lambda$setExperimentActive$1(this.arg$1, this.arg$2);
    }
}

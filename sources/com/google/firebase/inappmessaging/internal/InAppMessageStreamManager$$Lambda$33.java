package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$33 implements Action {
    private static final InAppMessageStreamManager$$Lambda$33 instance = new InAppMessageStreamManager$$Lambda$33();

    private InAppMessageStreamManager$$Lambda$33() {
    }

    public static Action lambdaFactory$() {
        return instance;
    }

    public void run() {
        Logging.logd("Wrote to cache");
    }
}

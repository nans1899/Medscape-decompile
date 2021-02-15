package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DisplayCallbacksImpl$$Lambda$1 implements Action {
    private final DisplayCallbacksImpl arg$1;

    private DisplayCallbacksImpl$$Lambda$1(DisplayCallbacksImpl displayCallbacksImpl) {
        this.arg$1 = displayCallbacksImpl;
    }

    public static Action lambdaFactory$(DisplayCallbacksImpl displayCallbacksImpl) {
        return new DisplayCallbacksImpl$$Lambda$1(displayCallbacksImpl);
    }

    public void run() {
        this.arg$1.metricsLoggerClient.logImpression(this.arg$1.inAppMessage);
    }
}

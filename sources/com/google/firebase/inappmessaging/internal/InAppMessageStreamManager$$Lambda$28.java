package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$28 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$28 instance = new InAppMessageStreamManager$$Lambda$28();

    private InAppMessageStreamManager$$Lambda$28() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logw("Service fetch error: " + ((Throwable) obj).getMessage());
    }
}

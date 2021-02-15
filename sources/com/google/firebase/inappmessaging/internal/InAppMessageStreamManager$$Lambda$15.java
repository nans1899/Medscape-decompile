package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$15 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$15 instance = new InAppMessageStreamManager$$Lambda$15();

    private InAppMessageStreamManager$$Lambda$15() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logd("Fetched from cache");
    }
}

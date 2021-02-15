package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$1 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$1 instance = new InAppMessageStreamManager$$Lambda$1();

    private InAppMessageStreamManager$$Lambda$1() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logd("Event Triggered: " + ((String) obj));
    }
}

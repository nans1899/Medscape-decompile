package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$29 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$29 instance = new InAppMessageStreamManager$$Lambda$29();

    private InAppMessageStreamManager$$Lambda$29() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logw("Impression store read fail: " + ((Throwable) obj).getMessage());
    }
}

package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$34 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$34 instance = new InAppMessageStreamManager$$Lambda$34();

    private InAppMessageStreamManager$$Lambda$34() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logw("Cache write error: " + ((Throwable) obj).getMessage());
    }
}

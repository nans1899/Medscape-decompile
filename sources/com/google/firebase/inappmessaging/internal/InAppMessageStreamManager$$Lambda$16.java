package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$16 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$16 instance = new InAppMessageStreamManager$$Lambda$16();

    private InAppMessageStreamManager$$Lambda$16() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logw("Cache read error: " + ((Throwable) obj).getMessage());
    }
}

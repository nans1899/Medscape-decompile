package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$22 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$22 instance = new InAppMessageStreamManager$$Lambda$22();

    private InAppMessageStreamManager$$Lambda$22() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logw("Impressions store read fail: " + ((Throwable) obj).getMessage());
    }
}

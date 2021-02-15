package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DisplayCallbacksImpl$$Lambda$6 implements Consumer {
    private static final DisplayCallbacksImpl$$Lambda$6 instance = new DisplayCallbacksImpl$$Lambda$6();

    private DisplayCallbacksImpl$$Lambda$6() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.loge("Impression store write failure");
    }
}

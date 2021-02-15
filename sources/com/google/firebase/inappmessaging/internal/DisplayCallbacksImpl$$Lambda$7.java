package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DisplayCallbacksImpl$$Lambda$7 implements Action {
    private static final DisplayCallbacksImpl$$Lambda$7 instance = new DisplayCallbacksImpl$$Lambda$7();

    private DisplayCallbacksImpl$$Lambda$7() {
    }

    public static Action lambdaFactory$() {
        return instance;
    }

    public void run() {
        Logging.logd("Impression store write success");
    }
}

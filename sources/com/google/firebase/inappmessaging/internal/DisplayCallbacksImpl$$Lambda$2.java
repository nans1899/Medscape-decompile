package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DisplayCallbacksImpl$$Lambda$2 implements Action {
    private static final DisplayCallbacksImpl$$Lambda$2 instance = new DisplayCallbacksImpl$$Lambda$2();

    private DisplayCallbacksImpl$$Lambda$2() {
    }

    public static Action lambdaFactory$() {
        return instance;
    }

    public void run() {
        DisplayCallbacksImpl.wasImpressed = true;
    }
}

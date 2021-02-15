package com.google.firebase.inappmessaging.internal;

import io.reactivex.Completable;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$35 implements Function {
    private static final InAppMessageStreamManager$$Lambda$35 instance = new InAppMessageStreamManager$$Lambda$35();

    private InAppMessageStreamManager$$Lambda$35() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Completable.complete();
    }
}

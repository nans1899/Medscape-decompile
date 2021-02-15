package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Predicate;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$31 implements Predicate {
    private static final InAppMessageStreamManager$$Lambda$31 instance = new InAppMessageStreamManager$$Lambda$31();

    private InAppMessageStreamManager$$Lambda$31() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean test(Object obj) {
        return InAppMessageStreamManager.lambda$createFirebaseInAppMessageStream$9((Boolean) obj);
    }
}

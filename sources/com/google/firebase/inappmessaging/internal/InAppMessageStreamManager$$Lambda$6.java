package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Predicate;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$6 implements Predicate {
    private static final InAppMessageStreamManager$$Lambda$6 instance = new InAppMessageStreamManager$$Lambda$6();

    private InAppMessageStreamManager$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean test(Object obj) {
        return InAppMessageStreamManager.lambda$getContentIfNotRateLimited$22((Boolean) obj);
    }
}

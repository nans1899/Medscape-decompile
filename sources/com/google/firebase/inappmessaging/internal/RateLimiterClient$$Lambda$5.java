package com.google.firebase.inappmessaging.internal;

import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class RateLimiterClient$$Lambda$5 implements Consumer {
    private final RateLimiterClient arg$1;

    private RateLimiterClient$$Lambda$5(RateLimiterClient rateLimiterClient) {
        this.arg$1 = rateLimiterClient;
    }

    public static Consumer lambdaFactory$(RateLimiterClient rateLimiterClient) {
        return new RateLimiterClient$$Lambda$5(rateLimiterClient);
    }

    public void accept(Object obj) {
        this.arg$1.clearInMemCache();
    }
}
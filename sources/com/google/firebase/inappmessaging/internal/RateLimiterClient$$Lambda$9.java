package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.RateLimitProto;
import io.reactivex.functions.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class RateLimiterClient$$Lambda$9 implements Action {
    private final RateLimiterClient arg$1;
    private final RateLimitProto.RateLimit arg$2;

    private RateLimiterClient$$Lambda$9(RateLimiterClient rateLimiterClient, RateLimitProto.RateLimit rateLimit) {
        this.arg$1 = rateLimiterClient;
        this.arg$2 = rateLimit;
    }

    public static Action lambdaFactory$(RateLimiterClient rateLimiterClient, RateLimitProto.RateLimit rateLimit) {
        return new RateLimiterClient$$Lambda$9(rateLimiterClient, rateLimit);
    }

    public void run() {
        this.arg$1.initInMemCache(this.arg$2);
    }
}

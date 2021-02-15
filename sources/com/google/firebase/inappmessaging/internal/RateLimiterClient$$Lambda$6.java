package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.RateLimitProto;
import com.google.firebase.inappmessaging.model.RateLimit;
import io.reactivex.functions.Predicate;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class RateLimiterClient$$Lambda$6 implements Predicate {
    private final RateLimiterClient arg$1;
    private final RateLimit arg$2;

    private RateLimiterClient$$Lambda$6(RateLimiterClient rateLimiterClient, RateLimit rateLimit) {
        this.arg$1 = rateLimiterClient;
        this.arg$2 = rateLimit;
    }

    public static Predicate lambdaFactory$(RateLimiterClient rateLimiterClient, RateLimit rateLimit) {
        return new RateLimiterClient$$Lambda$6(rateLimiterClient, rateLimit);
    }

    public boolean test(Object obj) {
        return RateLimiterClient.lambda$increment$0(this.arg$1, this.arg$2, (RateLimitProto.Counter) obj);
    }
}

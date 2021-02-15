package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.RateLimitProto;
import com.google.firebase.inappmessaging.model.RateLimit;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class RateLimiterClient$$Lambda$7 implements Function {
    private final RateLimitProto.RateLimit arg$1;
    private final RateLimit arg$2;

    private RateLimiterClient$$Lambda$7(RateLimitProto.RateLimit rateLimit, RateLimit rateLimit2) {
        this.arg$1 = rateLimit;
        this.arg$2 = rateLimit2;
    }

    public static Function lambdaFactory$(RateLimitProto.RateLimit rateLimit, RateLimit rateLimit2) {
        return new RateLimiterClient$$Lambda$7(rateLimit, rateLimit2);
    }

    public Object apply(Object obj) {
        return RateLimiterClient.lambda$increment$1(this.arg$1, this.arg$2, (RateLimitProto.Counter) obj);
    }
}

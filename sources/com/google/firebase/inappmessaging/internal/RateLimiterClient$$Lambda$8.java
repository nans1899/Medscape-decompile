package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.RateLimitProto;
import io.reactivex.functions.Function;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class RateLimiterClient$$Lambda$8 implements Function {
    private final RateLimiterClient arg$1;

    private RateLimiterClient$$Lambda$8(RateLimiterClient rateLimiterClient) {
        this.arg$1 = rateLimiterClient;
    }

    public static Function lambdaFactory$(RateLimiterClient rateLimiterClient) {
        return new RateLimiterClient$$Lambda$8(rateLimiterClient);
    }

    public Object apply(Object obj) {
        return this.arg$1.storageClient.write((RateLimitProto.RateLimit) obj).doOnComplete(RateLimiterClient$$Lambda$9.lambdaFactory$(this.arg$1, (RateLimitProto.RateLimit) obj));
    }
}

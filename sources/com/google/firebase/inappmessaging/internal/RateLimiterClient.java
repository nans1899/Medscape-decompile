package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.RateLimitProto;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.RateLimit;
import com.google.firebase.inappmessaging.internal.time.Clock;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class RateLimiterClient {
    private static final RateLimitProto.RateLimit EMPTY_RATE_LIMITS = RateLimitProto.RateLimit.getDefaultInstance();
    private Maybe<RateLimitProto.RateLimit> cachedRateLimts = Maybe.empty();
    private final Clock clock;
    private final ProtoStorageClient storageClient;

    @Inject
    RateLimiterClient(@RateLimit ProtoStorageClient protoStorageClient, Clock clock2) {
        this.storageClient = protoStorageClient;
        this.clock = clock2;
    }

    private static RateLimitProto.Counter increment(RateLimitProto.Counter counter) {
        return (RateLimitProto.Counter) RateLimitProto.Counter.newBuilder(counter).clearValue().setValue(counter.getValue() + 1).build();
    }

    public Completable increment(com.google.firebase.inappmessaging.model.RateLimit rateLimit) {
        return getRateLimits().defaultIfEmpty(EMPTY_RATE_LIMITS).flatMapCompletable(RateLimiterClient$$Lambda$1.lambdaFactory$(this, rateLimit));
    }

    static /* synthetic */ boolean lambda$increment$0(RateLimiterClient rateLimiterClient, com.google.firebase.inappmessaging.model.RateLimit rateLimit, RateLimitProto.Counter counter) throws Exception {
        return !rateLimiterClient.isLimitExpired(counter, rateLimit);
    }

    static /* synthetic */ RateLimitProto.RateLimit lambda$increment$1(RateLimitProto.RateLimit rateLimit, com.google.firebase.inappmessaging.model.RateLimit rateLimit2, RateLimitProto.Counter counter) throws Exception {
        return (RateLimitProto.RateLimit) RateLimitProto.RateLimit.newBuilder(rateLimit).putLimits(rateLimit2.limiterKey(), increment(counter)).build();
    }

    public Single<Boolean> isRateLimited(com.google.firebase.inappmessaging.model.RateLimit rateLimit) {
        return getRateLimits().switchIfEmpty(Maybe.just(RateLimitProto.RateLimit.getDefaultInstance())).map(RateLimiterClient$$Lambda$2.lambdaFactory$(this, rateLimit)).filter(RateLimiterClient$$Lambda$3.lambdaFactory$(this, rateLimit)).isEmpty();
    }

    static /* synthetic */ boolean lambda$isRateLimited$6(RateLimiterClient rateLimiterClient, com.google.firebase.inappmessaging.model.RateLimit rateLimit, RateLimitProto.Counter counter) throws Exception {
        return rateLimiterClient.isLimitExpired(counter, rateLimit) || counter.getValue() < rateLimit.limit();
    }

    private boolean isLimitExpired(RateLimitProto.Counter counter, com.google.firebase.inappmessaging.model.RateLimit rateLimit) {
        return this.clock.now() - counter.getStartTimeEpoch() > rateLimit.timeToLiveMillis();
    }

    private Maybe<RateLimitProto.RateLimit> getRateLimits() {
        return this.cachedRateLimts.switchIfEmpty(this.storageClient.read(RateLimitProto.RateLimit.parser()).doOnSuccess(RateLimiterClient$$Lambda$4.lambdaFactory$(this))).doOnError(RateLimiterClient$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void initInMemCache(RateLimitProto.RateLimit rateLimit) {
        this.cachedRateLimts = Maybe.just(rateLimit);
    }

    /* access modifiers changed from: private */
    public void clearInMemCache() {
        this.cachedRateLimts = Maybe.empty();
    }

    private RateLimitProto.Counter newCounter() {
        return (RateLimitProto.Counter) RateLimitProto.Counter.newBuilder().setValue(0).setStartTimeEpoch(this.clock.now()).build();
    }
}

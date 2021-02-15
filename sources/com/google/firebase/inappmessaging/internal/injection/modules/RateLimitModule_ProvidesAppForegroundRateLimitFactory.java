package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.model.RateLimit;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class RateLimitModule_ProvidesAppForegroundRateLimitFactory implements Factory<RateLimit> {
    private final RateLimitModule module;

    public RateLimitModule_ProvidesAppForegroundRateLimitFactory(RateLimitModule rateLimitModule) {
        this.module = rateLimitModule;
    }

    public RateLimit get() {
        return providesAppForegroundRateLimit(this.module);
    }

    public static RateLimitModule_ProvidesAppForegroundRateLimitFactory create(RateLimitModule rateLimitModule) {
        return new RateLimitModule_ProvidesAppForegroundRateLimitFactory(rateLimitModule);
    }

    public static RateLimit providesAppForegroundRateLimit(RateLimitModule rateLimitModule) {
        return (RateLimit) Preconditions.checkNotNull(rateLimitModule.providesAppForegroundRateLimit(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

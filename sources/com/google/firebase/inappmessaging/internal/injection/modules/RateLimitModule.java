package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.injection.qualifiers.AppForeground;
import com.google.firebase.inappmessaging.model.RateLimit;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class RateLimitModule {
    private static final String APP_FOREGROUND_ONE_PER_DAY_LIMITER_KEY = "APP_FOREGROUND_ONE_PER_DAY_LIMITER_KEY";

    @AppForeground
    @Provides
    public RateLimit providesAppForegroundRateLimit() {
        return RateLimit.builder().setLimit(1).setLimiterKey(APP_FOREGROUND_ONE_PER_DAY_LIMITER_KEY).setTimeToLiveMillis(TimeUnit.DAYS.toMillis(1)).build();
    }
}
